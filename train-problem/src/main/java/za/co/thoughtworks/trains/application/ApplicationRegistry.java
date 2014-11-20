package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.infrastructure.persistence.InMemoryLocationRepository;
import za.co.thoughtworks.trains.model.RailroadTracks;
import za.co.thoughtworks.trains.model.RoutingEngineFactory;

public class ApplicationRegistry {

	public static LocationRepository getLocationRepository(RailroadTracks railroadTracks) {
		return new InMemoryLocationRepository(railroadTracks);
	}

	public static RoutingEngineFactory getRoutingEngineFactory() {
		return new RoutingEngineFactory();
	}

}
