package eu.wodrobina.homeenvironment.location;

import org.springframework.data.jpa.repository.JpaRepository;

interface LocationRepositoryJpa extends LocationRepository, JpaRepository<Location, String> {
}
