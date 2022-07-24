package eu.wodrobina.homeenvironment.location;

import java.util.Optional;

interface LocationRepository {

    Location save(Location location);

    Optional<Location> findById(String s);

    void delete(Location location);
}
