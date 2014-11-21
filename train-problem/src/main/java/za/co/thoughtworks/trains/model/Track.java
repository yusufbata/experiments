package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.application.TrackDescriptor;



public class Track extends TrackDescriptor {

	private Location fromLocation;
	private Location toLocation;

	public Track(Location fromLocation, Location toLocation, Distance trackDistance) {
		super(fromLocation.getId(), toLocation.getId(), trackDistance);
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
	}

	public Location getFromLocation() {
		return this.fromLocation;
	}

	public boolean endLocationHasId(String locationId) {
		return this.toLocation.hasId(locationId);
	}

	public Location getToLocation() {
		return this.toLocation;
	}
}
