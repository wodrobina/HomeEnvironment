package eu.wodrobina.homeenvironment.location;

import eu.wodrobina.homeenvironment.location.dto.LocationName;

public class LocationFactory {

    private LocationFactory() {
    }

    public static Location from(LocationName locationName) {
        return new Location(locationName.value());
    }
}
