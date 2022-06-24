package eu.wodrobina.homeenvironment.sensor.dto;

import eu.wodrobina.homeenvironment.common.DataType;
import java.time.LocalDateTime;

public record SensorReading(String sensorName,
                            DataType dataType,
                            String value,
                            LocalDateTime timestamp) {

}
