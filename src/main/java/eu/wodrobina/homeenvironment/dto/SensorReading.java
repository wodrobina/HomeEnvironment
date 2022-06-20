package com.example.homeenvironment.dto;

import java.time.LocalDateTime;
import com.example.homeenvironment.model.DataType;

public record SensorReading(String sensorName,
                            DataType dataType,
                            String value,
                            LocalDateTime timestamp) {

}
