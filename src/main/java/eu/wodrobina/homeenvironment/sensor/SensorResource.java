package eu.wodrobina.homeenvironment.sensor;

import eu.wodrobina.homeenvironment.sensor.dto.SensorName;
import eu.wodrobina.homeenvironment.sensor.dto.SensorReading;
import javax.transaction.Transactional;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SensorResource {

    private final SensorService sensorService;

    public SensorResource(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("register/{sensorName}")
    public ResponseEntity<String> registerSensor(@PathVariable String sensorName) {
        Optional<SensorOperationFailure> operationStatus = sensorService.register(sensorName);
        return operationStatus
                .map(SensorResource::sensorExistError)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CREATED));
    }

    @PostMapping("register")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerSensorReading(SensorReading sensorReading) {
        Optional<SensorOperationFailure> operationStatus = sensorService.newReadingFromSensor(sensorReading);

        return operationStatus
                .map(status -> sensorExist(sensorReading))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CREATED));
    }

    private ResponseEntity<String> sensorExist(SensorReading sensorReading) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Sensor " + sensorReading.value() + " does not exist");
    }


    private static ResponseEntity<String> sensorExistError(SensorOperationFailure sensorOperationFailure) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(sensorOperationFailure.message());
    }
}
