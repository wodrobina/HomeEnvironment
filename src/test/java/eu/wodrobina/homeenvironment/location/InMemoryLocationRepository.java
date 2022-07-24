package eu.wodrobina.homeenvironment.location;

import eu.wodrobina.homeenvironment.InMemoryRepository;

class InMemoryLocationRepository extends InMemoryRepository<Location, String> implements LocationRepository {

    public InMemoryLocationRepository() {
        super(Location::getName);
    }

    @Override
    public Location save(Location location) {
        return super.save(location);
    }
}
