package eu.wodrobina.homeenvironment.location;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LocationConfiguration {

    private final LocationRepository locationRepository;

    public LocationConfiguration(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Bean
    LocationService locationService() {
        return new LocationService(locationRepository);
    }

}
