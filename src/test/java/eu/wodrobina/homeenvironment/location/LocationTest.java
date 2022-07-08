package eu.wodrobina.homeenvironment.location;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationTest {


    public static final String LOCATION_NAME = "AnyName";
    public static final String SENSOR_NAME = "AnyName";

    @Test
    @DisplayName("Sensor can be attached to Location")
    void test01() {
        Location location = new Location(LOCATION_NAME);
        SensorName sensorName = new SensorName(SENSOR_NAME);

        location.attachSensorToLocation(sensorName);

        assertThat(location.listSensorNames())
                .hasSize(1)
                .allSatisfy(sensor -> assertThat(sensor.getName()).isEqualTo(SENSOR_NAME));
    }

    @Test
    @DisplayName("Sensor can be removed from Location")
    void test02() {
        Location location = new Location(LOCATION_NAME);

        location.attachSensorToLocation(new SensorName(SENSOR_NAME));
        assertThat(location.listSensorNames())
                .hasSize(1);

        location.detachSensorFromLocation(new SensorName(SENSOR_NAME));
        assertThat(location.listSensorNames())
                .isEmpty();
    }
}