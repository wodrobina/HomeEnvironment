package eu.wodrobina.homeenvironment.location;

import eu.wodrobina.homeenvironment.location.dto.LocationName;
import java.util.Optional;

class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    void createLocation(LocationName locationName) {
        locationRepository.save(LocationFactory.from(locationName));
    }

    void deleteLocation(LocationName locationName) {
        Optional<Location> byId = locationRepository.findById(locationName.value());
        byId.ifPresent(locationRepository::delete);
    }

    void attachSensorToLocation(LocationName locationName, SensorName sensorName) {
        Optional<Location> byId = locationRepository.findById(locationName.value());
        byId.ifPresent(location -> location.attachSensorToLocation(sensorName));
    }

    void detachSensorFromLocation(LocationName locationName, SensorName sensorName) {
        Optional<Location> byId = locationRepository.findById(locationName.value());
        byId.ifPresent(location -> location.detachSensorFromLocation(sensorName));
    }
}
