package eu.wodrobina.homeenvironment.dto;

import java.time.LocalDateTime;
import eu.wodrobina.homeenvironment.model.DataType;

public record SensorReading(String sensorName,
                            DataType dataType,
                            String value,
                            LocalDateTime timestamp) {

}
