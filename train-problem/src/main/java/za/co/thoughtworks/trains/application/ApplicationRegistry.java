package za.co.thoughtworks.trains.application;

import java.util.List;

import za.co.thoughtworks.trains.infrastructure.persistence.InMemoryLocationRepository;
import za.co.thoughtworks.trains.model.path.finders.PathFinderFactory;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.LocationFactory;

/**
 * Used by the application to load various interface types (in absence of DI framework).
 * 
 * @author Yusuf
 *
 */
public class ApplicationRegistry {

	public static LocationRepository getLocationRepository(List<Location> locationList) {
		return new InMemoryLocationRepository(locationList);
	}

	public static PathFinderFactory getPathFinderFactory() {
		return new PathFinderFactory();
	}

	public static LocationFactory getLocationFactory() {
		return new LocationFactory();
	}

}
