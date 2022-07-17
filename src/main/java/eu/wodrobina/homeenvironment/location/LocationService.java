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
            throw new RuntimeException("Location exist. It's forbidden o add location with same name");
        }
        locationRepository.save(Location.from(locationName));
    }

    void removeLocation(LocationName locationName) {
        Optional<Location> locationById = locationRepository.findById(locationName.value());
        if (locationById.isPresent()) {
            locationRepository.remove(Location.from(locationName));
        }
        throw new RuntimeException("Location doesn't exist so it can't be removed");
    }

    void linkSensorToLocation(LocationName locationName, SensorNameDto sensorNameDto){
        Optional<Location> locationById = locationRepository.findById(locationName.value());
        Location location = locationById
                .orElseThrow(() -> new RuntimeException("Location doesn't exist. It's impossible to link any sensor"));

        location.attachSensorToLocation(SensorName.namedAs(sensorNameDto.value()));
    }


}


