package eu.wodrobina.homeenvironment.repostitory;

import eu.wodrobina.homeenvironment.model.Location;
import eu.wodrobina.homeenvironment.model.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, String> {
}
