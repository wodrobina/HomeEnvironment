package eu.wodrobina.homeenvironment.resource;

import eu.wodrobina.homeenvironment.dto.NewSensor;
import eu.wodrobina.homeenvironment.dto.SensorReading;
import eu.wodrobina.homeenvironment.model.Sensor;
import eu.wodrobina.homeenvironment.model.SensorData;
import eu.wodrobina.homeenvironment.repostitory.SensorRepository;
import eu.wodrobina.homeenvironment.service.SensorService;
import javax.transaction.Transactional;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorResource {

    private final SensorRepository sensorRepository;
    private final SensorService sensorService;

    public SensorResource(SensorRepository sensorRepository, SensorService sensorService) {
        this.sensorRepository = sensorRepository;
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<Sensor> registerSensor(NewSensor newSensor) {
        Optional<Sensor> existingSensor = sensorRepository.findById(newSensor.sensorName());
        if (existingSensor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        sensorService.from(newSensor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> registerSensorReading(SensorReading sensorReading) {
        sensorRepository.findById(sensorReading.sensorName())
                .ifPresent(sensor -> addReadingDataAndSave(sensorReading, sensor));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    private void addReadingDataAndSave(SensorReading sensorReading, Sensor sensor) {
        sensor.addSensorData(getSensorData(sensorReading));
        sensorRepository.save(sensor);
    }

    private SensorData getSensorData(SensorReading sensorReading) {
        return new SensorData(sensorReading.dataType(), Double.parseDouble(sensorReading.value()), sensorReading.timestamp());
    }
}
