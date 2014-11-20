package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.Distance;

public class Track {

	private Location fromLocation;
	private Location toLocation;
	private Distance trackDistance;

	public Track(String fromLocationId, String toLocationId, int trackDistance) {
		this.fromLocation = Location.create(fromLocationId);
		this.toLocation = Location.create(toLocationId);
		this.trackDistance = Distance.valueOf(trackDistance);
	}

	public Location getFromLocation() {
		return this.fromLocation;
	}

}
