package eu.wodrobina.homeenvironment.location;

import eu.wodrobina.homeenvironment.location.dto.LocationName;
import eu.wodrobina.homeenvironment.sensor.dto.SensorNameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationServiceTest {

    public static final String LOCATION_NAME = "Test Location";
    public static final String SENSOR_NAME = "Sensor Name";
    private LocationRepository locationRepository;
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        InMemoryLocationRepository repository = new InMemoryLocationRepository();
        locationRepository = repository;
        LocationConfiguration locationConfiguration = new LocationConfiguration(repository);
        locationService = locationConfiguration.locationService();
    }

    @Test
    @DisplayName("Should register new location with empty list of sensors")
    void test01() {
        locationService.createLocation(new LocationName(LOCATION_NAME));

        assertThat(locationRepository.findById(LOCATION_NAME))
                .isPresent()
                .satisfies(loc -> assertThat(loc.get().getName()).isEqualTo(LOCATION_NAME))
                .satisfies(loc -> assertThat(loc.get().listSensorNames()).isEmpty());
    }

    @Test
    @DisplayName("Should delete existing location")
    void test02() {
        locationService.createLocation(new LocationName(LOCATION_NAME));

        locationService.removeLocation(new LocationName(LOCATION_NAME));

        assertThat(locationRepository.findById(LOCATION_NAME))
                .isEmpty();
    }

    @Test
    @DisplayName("Should link sensor with location")
    void test03() {
        locationService.createLocation(new LocationName(LOCATION_NAME));

        locationService.linkSensorToLocation(new LocationName(LOCATION_NAME), new SensorNameDto(SENSOR_NAME));
        assertThat(locationRepository.findById(LOCATION_NAME))
                .isPresent()
                .satisfies(loc -> assertThat(loc.get().getName()).isEqualTo(LOCATION_NAME))
                .satisfies(loc -> assertThat(loc.get().listSensorNames())
                        .hasSize(1)
                        .allSatisfy(sensor -> assertThat(sensor.getName()).isEqualTo(SENSOR_NAME))
                );

    }

    @Test
    @DisplayName("Should detach sensor from location")
    void test04() {
        locationService.createLocation(new LocationName(LOCATION_NAME));
        locationService.linkSensorToLocation(new LocationName(LOCATION_NAME), new SensorNameDto(SENSOR_NAME));

        locationService.removeSensorFromLocation(new LocationName(LOCATION_NAME), new SensorNameDto(SENSOR_NAME));

        assertThat(locationRepository.findById(LOCATION_NAME))
                .isPresent()
                .satisfies(loc -> assertThat(loc.get().getName()).isEqualTo(LOCATION_NAME))
                .satisfies(loc -> assertThat(loc.get().listSensorNames()).isEmpty());
    }


}