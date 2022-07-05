package eu.wodrobina.homeenvironment.sensor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
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
    @DisplayName("Should not register sensor data when sensor doesn't exist")
    void register02() {
    }

    @Test
    void newReadingFromSensor() {
    }
}