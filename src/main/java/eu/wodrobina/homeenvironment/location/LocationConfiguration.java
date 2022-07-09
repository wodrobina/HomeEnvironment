package eu.wodrobina.homeenvironment.location;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LocationConfiguration {

    @Bean
    LocationService locationService(LocationRepository locationRepository) {
        return new LocationService(locationRepository);
    }
}
