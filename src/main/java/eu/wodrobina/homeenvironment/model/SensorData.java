package eu.wodrobina.homeenvironment.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
public class SensorData {

    @Enumerated(EnumType.STRING)
    private DataType dataType;
    private Double recordedValue;
    private LocalDateTime timestamp;

    protected SensorData() {
    }

    public SensorData(DataType dataType, Double recordedValue, LocalDateTime timestamp) {
        this.dataType = dataType;
        this.recordedValue = recordedValue;
        this.timestamp = timestamp;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Double getRecordedValue() {
        return recordedValue;
    }

    public void setRecordedValue(Double recordedValue) {
        this.recordedValue = recordedValue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
