package eu.wodrobina.homeenvironment.service;

import eu.wodrobina.homeenvironment.dto.NewSensor;
import eu.wodrobina.homeenvironment.model.Sensor;
import eu.wodrobina.homeenvironment.repostitory.SensorRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor from(NewSensor newSensor) {
        return sensorRepository.save(new Sensor(newSensor.sensorName()));
    }
}
