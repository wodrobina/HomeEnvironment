package eu.wodrobina.homeenvironment.sensor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

public class InMemoryTestSensorRepository  implements SensorRepository{

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
