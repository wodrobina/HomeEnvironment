package eu.wodrobina.homeenvironment.sensor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryTestSensorRepository implements SensorRepository {

    private Map<String, Sensor> values = new ConcurrentHashMap<>();

    @Override
    public Sensor save(Sensor sensor) {
        return values.put(sensor.getName(), sensor);
    }

    @Override
    public Optional<Sensor> findById(String s) {
        return Optional.ofNullable(values.get(s));
    }
}
