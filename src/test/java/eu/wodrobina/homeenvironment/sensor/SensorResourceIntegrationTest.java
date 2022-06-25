package eu.wodrobina.homeenvironment.sensor;

import static org.assertj.core.api.Assertions.assertThat;

import eu.wodrobina.homeenvironment.common.DataType;
import eu.wodrobina.homeenvironment.sensor.dto.SensorReading;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("integration-test")
class SensorResourceIntegrationTest {
    public static final String SENSOR_NAME = "TestSensor";
    public static final Double RECORDED_DATA = 12.33d;
    @Autowired
    private SensorRepository sensorRepository;

    SensorResource sensorResource;

    @BeforeEach
    void setUp() {
        sensorResource = new SensorResource(new SensorService(sensorRepository));
    }

    @Test
    @DisplayName("Should record temperature data for existing sensor")
    void tes01() {
        Sensor testSensor = new Sensor(SENSOR_NAME);
        sensorRepository.save(testSensor);
        SensorReading sensorReading = new SensorReading(SENSOR_NAME, DataType.TEMPERATURE, RECORDED_DATA.toString(), LocalDateTime.MIN);

        sensorResource.registerSensorReading(sensorReading);

        Optional<Sensor> sensorWithNewRecordedData = sensorRepository.findById(SENSOR_NAME);
        assertThat(sensorWithNewRecordedData)
                .isPresent()
                .satisfies(optionalSensor -> {
                    final Sensor sensor = optionalSensor.get();
                    assertThat(sensor.getSensorDataSet())
                            .hasSize(1)
                            .allSatisfy(sensorData -> {
                                sensorData.getDataType().equals(DataType.TEMPERATURE);
                                sensorData.getRecordedValue().equals(RECORDED_DATA);
                            });
                });
    }
}