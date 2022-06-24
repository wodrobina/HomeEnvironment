package eu.wodrobina.homeenvironment.sensor;

import org.springframework.data.repository.CrudRepository;

interface SensorRepository extends CrudRepository<Sensor, String> {
}
