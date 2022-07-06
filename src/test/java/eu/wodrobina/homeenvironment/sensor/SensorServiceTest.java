package eu.wodrobina.homeenvironment.sensor;

import static org.assertj.core.api.Assertions.assertThat;

import eu.wodrobina.homeenvironment.common.DataType;
import eu.wodrobina.homeenvironment.sensor.dto.SensorReading;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SensorServiceTest {

    public static final String SENSOR_NAME = "TestSensor";
    private SensorService sensorService;
    private SensorRepository sensorRepository;

    @BeforeEach
    void setUp() {
        sensorRepository = new InMemoryTestSensorRepository();
        sensorService = new SensorService(sensorRepository);
    }

    @Test
    @DisplayName("Should register sensor and return no failure")
    void register01() {
        Optional<SensorOperationFailure> register = sensorService.register(SENSOR_NAME);

        assertThat(register).isEmpty();
    }

    @Test
    @DisplayName("Should not register sensor when sensor with the same name exist")
    void register02() {
        sensorService.register(SENSOR_NAME);
        Optional<SensorOperationFailure> failure = sensorService.register(SENSOR_NAME);

        assertThat(failure)
                .isNotEmpty()
                .satisfies(failMessage -> assertThat(failMessage.get().message()).isEqualTo("Sensor exist"));
    }

    @Test
    @DisplayName("Should register sensor data when sensor exist")
    void sensorDataRegister01() {
        //given
        sensorService.register(SENSOR_NAME);

        //when
        Optional<SensorOperationFailure> sensorOperationFailure = sensorService
                .newReadingFromSensor(new SensorReading(SENSOR_NAME, DataType.TEMPERATURE, "2.3",
                        LocalDateTime.MIN));

        //then
        Optional<Sensor> byId = sensorRepository.findById(SENSOR_NAME);
        assertThat(byId)
                .isPresent()
                .satisfies(sensorOpt -> {
                    final Sensor sensor = sensorOpt.get();
                    Set<SensorData> sensorDataSet = sensor.getSensorDataSet();
                    assertThat(sensorDataSet)
                            .hasSize(1)
                            .allSatisfy(sensorData -> {
                                assertThat(sensorData.getDataType()).isEqualTo(DataType.TEMPERATURE);
                                assertThat(sensorData.getRecordedValue()).isEqualTo(Double.valueOf("2.3"));
                                assertThat(sensorData.getTimestamp()).isEqualTo(LocalDateTime.MIN);
                            });
                });
    }

    @Test
    @DisplayName("Should not register sensor data when sensor doesn't exist")
    void sensorDataRegister02() {
        //when
        Optional<SensorOperationFailure> sensorOperationFailure = sensorService
                .newReadingFromSensor(new SensorReading(SENSOR_NAME, DataType.TEMPERATURE, "2.3",
                        LocalDateTime.MIN));

        assertThat(sensorOperationFailure)
                .isNotEmpty()
                .satisfies(failMessage -> assertThat(failMessage
                        .get().message())
                        .isEqualTo("Sensor doesn't exist"));

    }
}