package eu.wodrobina.homeenvironment.repostitory;

import eu.wodrobina.homeenvironment.model.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface SensorRepository extends CrudRepository<Sensor, String> {
}
