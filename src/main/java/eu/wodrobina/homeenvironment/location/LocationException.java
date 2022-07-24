package eu.wodrobina.homeenvironment.location;

class LocationException extends RuntimeException {

    private LocationException(String message) {
        super(message);
    }

    static LocationException locationWithSameNameExist() {
        return new LocationException("Location exist. It's forbidden o add location with same name");
    }

    static LocationException locationDoesNotExist() {
        return new LocationException("Location doesn't exist so it can't be removed");
    }

    static LocationException linkSensorToNonExistingLocation() {
        return new LocationException("Location doesn't exist. It's impossible to link any sensor");
    }

    static LocationException removeSensorFromLocationThaNotExist() {
        return new LocationException("Location doesn't exist. It's impossible to remove sensor");
    }

}
