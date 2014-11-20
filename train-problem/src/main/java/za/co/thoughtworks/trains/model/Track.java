package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.Distance;

public class Track {

	private Location fromLocation;
	private Location toLocation;
	private Distance distance;

	public Track(String fromLocationId, String toLocationId, int trackDistance) {
		this.fromLocation = Location.create(fromLocationId);
		this.toLocation = Location.create(toLocationId);
		this.distance = Distance.valueOf(trackDistance);
	}

	public Location getFromLocation() {
		return this.fromLocation;
	}

	public boolean endLocationEquals(Location location) {
		return this.toLocation.equals(location);
	}

	public Location getToLocation() {
		return this.toLocation;
	}

	public Distance getDistance() {
		return this.distance;
	}

}
