package eu.wodrobina.homeenvironment.location;

import eu.wodrobina.homeenvironment.location.dto.LocationName;
import eu.wodrobina.homeenvironment.sensor.dto.SensorNameDto;

import java.util.Optional;

class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    void createLocation(LocationName locationName) {
        Optional<Location> locationById = locationRepository.findById(locationName.value());
        if (locationById.isPresent()) {
            throw LocationException.locationWithSameNameExist();
        }
        locationRepository.save(Location.from(locationName));
    }

    void removeLocation(LocationName locationName) {
        Optional<Location> locationById = locationRepository.findById(locationName.value());
        if (locationById.isPresent()) {
            locationRepository.delete(Location.from(locationName));
            return;
        }
        throw LocationException.locationDoesNotExist();
    }

    void linkSensorToLocation(LocationName locationName, SensorNameDto sensorNameDto) {
        Optional<Location> locationById = locationRepository.findById(locationName.value());
        Location location = locationById
                .orElseThrow(LocationException::linkSensorToNonExistingLocation);

        location.attachSensorToLocation(SensorName.namedAs(sensorNameDto.value()));
    }

    void removeSensorFromLocation(LocationName locationName, SensorNameDto sensorNameDto) {
        Optional<Location> locationById = locationRepository.findById(locationName.value());
        Location location = locationById
                .orElseThrow(LocationException::removeSensorFromLocationThaNotExist);

        location.detachSensorFromLocation(SensorName.namedAs(sensorNameDto.value()));
    }
}


