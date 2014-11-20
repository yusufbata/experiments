package za.co.thoughtworks.trains.application;

import java.util.List;

import za.co.thoughtworks.trains.infrastructure.persistence.InMemoryLocationRepository;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.RailroadTracks;
import za.co.thoughtworks.trains.model.RoutingEngineFactory;

public class ApplicationRegistry {

	public static LocationRepository getLocationRepository(List<Location> locationList) {
		return new InMemoryLocationRepository(locationList);
	}

	public static RoutingEngineFactory getRoutingEngineFactory() {
		return new RoutingEngineFactory();
	}

	public static LocationFactory getLocationFactory() {
		return new LocationFactory();
	}

}
