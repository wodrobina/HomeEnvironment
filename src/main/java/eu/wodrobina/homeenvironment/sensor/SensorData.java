package eu.wodrobina.homeenvironment.sensor;

import eu.wodrobina.homeenvironment.common.DataType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
class SensorData {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorData that = (SensorData) o;
        return dataType == that.dataType && recordedValue.equals(that.recordedValue) && timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataType, recordedValue, timestamp);
    }
}
