package eu.wodrobina.homeenvironment.sensor;

import eu.wodrobina.homeenvironment.sensor.dto.SensorReading;
import javax.transaction.Transactional;
import java.util.Optional;

class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    Optional<SensorOperationFailure> register(String sensorName) {
        Optional<Sensor> existingSensor = sensorRepository.findById(sensorName);
        if (existingSensor.isPresent()) {
            return Optional.of(new SensorOperationFailure("Sensor exist"));
        }

        from(sensorName);
        return Optional.empty();
    }

    Optional<SensorOperationFailure> newReadingFromSensor(SensorReading sensorReading) {
        Optional<Sensor> existingSensor = sensorRepository.findById(sensorReading.sensorName());
        if (existingSensor.isPresent()) {
            addReadingDataAndSave(sensorReading, existingSensor.get());
            return Optional.empty();
        }

        return Optional.of(new SensorOperationFailure("Sensor exist"));
    }

    private Sensor from(String sensorName) {
        return sensorRepository.save(new Sensor(sensorName));
    }

    private void addReadingDataAndSave(SensorReading sensorReading, Sensor sensor) {
        sensor.addSensorData(getSensorData(sensorReading));
        sensorRepository.save(sensor);
    }

    private SensorData getSensorData(SensorReading sensorReading) {
        return new SensorData(sensorReading.dataType(), Double.parseDouble(sensorReading.value()), sensorReading.timestamp());
    }
}
