package eu.wodrobina.homeenvironment.location;

import java.util.Optional;

public interface LocationRepository {

    Location save(Location location);

    Optional<Location> findById(String s);

    void remove(Location location);

    void delete(Location location);
}
