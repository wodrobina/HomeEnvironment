package eu.wodrobina.homeenvironment.resource;

import static org.assertj.core.api.Assertions.assertThat;

import eu.wodrobina.homeenvironment.dto.SensorReading;
import eu.wodrobina.homeenvironment.model.DataType;
import eu.wodrobina.homeenvironment.model.Sensor;
import eu.wodrobina.homeenvironment.repostitory.SensorRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SensorResourceIntegrationTest {

    public static final String SENSOR_NAME = "TestSensor";
    @Autowired
    private SensorRepository sensorRepository;

    @Test
    void name() {
        Sensor testSensor = new Sensor(SENSOR_NAME);
        Sensor savedSensor = sensorRepository.save(testSensor);

        SensorResource sensorResource = new SensorResource(sensorRepository, null);
        SensorReading sensorReading = new SensorReading(SENSOR_NAME, DataType.TEMPERATURE, "12.33", LocalDateTime.MIN);

        sensorResource.registerSensorReading(sensorReading);

        Optional<Sensor> sensorWithNewRecordedData = sensorRepository.findById(SENSOR_NAME);

        assertThat(sensorWithNewRecordedData)
                .isPresent()
                .satisfies(sensor -> assertThat(sensor.get().getSensorDataSet()).hasSize(1));

    }
}