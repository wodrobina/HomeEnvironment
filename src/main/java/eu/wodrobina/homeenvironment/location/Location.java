package eu.wodrobina.homeenvironment.location;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

@Entity
class Location {

    @Id
    @NaturalId
    private String name;

    @ElementCollection
    @Fetch(value = FetchMode.JOIN)
    @CollectionTable(name = "sensorName", joinColumns = @JoinColumn(name = "sensor_name_id"))
    @Column(name = "sensorName")
    private Set<SensorName> sensorNames = new HashSet<>();

    protected Location() {
    }

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void attachSensorToLocation(SensorName sensorName) {
        this.sensorNames.add(sensorName);
    }

    public void detachSensorFromLocation(SensorName sensorName) {
        this.sensorNames.remove(sensorName);
    }

    public Set<SensorName> listSensorNames() {
        return Set.copyOf(this.sensorNames);
    }
}
