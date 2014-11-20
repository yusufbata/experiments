package za.co.thoughtworks.trains.infrastructure.persistence;

import java.util.List;

import za.co.thoughtworks.trains.application.LocationRepository;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.RailroadTracks;

public class InMemoryLocationRepository implements LocationRepository {

	private RailroadTracks railroadTracks;

	public InMemoryLocationRepository(RailroadTracks railroadTracks) {
		this.railroadTracks = railroadTracks;
	}

	@Override
	public Location findLocationWithId(String startLocationId) {
		return null;
	}

}
