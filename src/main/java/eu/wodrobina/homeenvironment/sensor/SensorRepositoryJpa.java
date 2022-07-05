package eu.wodrobina.homeenvironment.sensor;

import org.springframework.data.jpa.repository.JpaRepository;

interface SensorRepositoryJpa extends SensorRepository, JpaRepository<Sensor, String> {
}
