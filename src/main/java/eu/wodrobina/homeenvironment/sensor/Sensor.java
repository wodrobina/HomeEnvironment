package eu.wodrobina.homeenvironment.sensor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

@Entity
class Sensor {

    @Id
    @NaturalId
    private String name;
    @ElementCollection
    @Fetch(value = FetchMode.JOIN)
    @CollectionTable(name="SensorData", joinColumns=@JoinColumn(name="sensor_data_id"))
    @Column(name="sensorData")
    private Set<SensorData> sensorDataSet = new HashSet<>();

    protected Sensor() {
    }

    public Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<SensorData> getSensorDataSet() {
        return sensorDataSet;
    }

    public void addSensorData(SensorData sensorData) {
        this.sensorDataSet.add(sensorData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sensor)) {
            return false;
        }
        return name != null && name.equals(((Sensor) o).name);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

}
