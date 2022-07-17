package eu.wodrobina.homeenvironment.location;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
class SensorName {
    private String name;

    protected SensorName() {
    }

    public SensorName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorName that = (SensorName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    static SensorName namedAs(String name){
        return new SensorName(name);
    }
}
