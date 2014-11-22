package za.co.thoughtworks.trains.infrastructure.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.co.thoughtworks.trains.application.LocationRepository;
import za.co.thoughtworks.trains.model.trackmaps.Location;

public class InMemoryLocationRepository implements LocationRepository {

	private Map<String, Location> locationsMap;

	public InMemoryLocationRepository(List<Location> locationList) {
		this.locationsMap = new HashMap<String, Location>();
		for (Location location : locationList) {
			locationsMap.put(location.getId(), location);
		}
	}

	@Override
	public Location findLocationWithId(String locationId) {
		return locationsMap.get(locationId);
	}

}
