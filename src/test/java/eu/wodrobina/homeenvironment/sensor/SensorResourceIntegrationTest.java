package eu.wodrobina.homeenvironment.sensor;

import static org.assertj.core.api.Assertions.assertThat;

import eu.wodrobina.homeenvironment.common.DataType;
import eu.wodrobina.homeenvironment.sensor.dto.SensorReading;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SensorResourceIntegrationTest {
    public static final String SENSOR_NAME = "TestSensor";
    @Autowired
    private SensorRepository sensorRepository;

    @Test
    @DisplayName("shg")
    void tes01() {
        Sensor testSensor = new Sensor(SENSOR_NAME);
        sensorRepository.save(testSensor);


        SensorResource sensorResource = new SensorResource(new SensorService(sensorRepository));
        SensorReading sensorReading = new SensorReading(SENSOR_NAME, DataType.TEMPERATURE, "12.33", LocalDateTime.MIN);

        sensorResource.registerSensorReading(sensorReading);

        Optional<Sensor> sensorWithNewRecordedData = sensorRepository.findById(SENSOR_NAME);

        assertThat(sensorWithNewRecordedData)
                .isPresent()
                .satisfies(sensor -> assertThat(sensor.get().getSensorDataSet()).hasSize(1));

    }
}