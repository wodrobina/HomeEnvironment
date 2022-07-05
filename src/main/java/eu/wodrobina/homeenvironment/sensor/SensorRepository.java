package eu.wodrobina.homeenvironment.sensor;

import java.util.Optional;

interface SensorRepository {

    Sensor save(Sensor sensor);

    Optional<Sensor> findById(String s);
}
