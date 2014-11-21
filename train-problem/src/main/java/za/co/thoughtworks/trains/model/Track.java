package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.application.TrackDescriptor;
import za.co.thoughtworks.trains.infrastructure.utils.Cloneable;



public class Track extends TrackDescriptor implements Cloneable<Track> {

	private final Location fromLocation;
	private final Location toLocation;

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
	
	@Override
	public Track clone() {
		Track clone = new Track(fromLocation.clone(), toLocation.clone(), this.getDistance());
		return clone;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Track fromLocation = ").append(this.fromLocation)
			.append(", toLocation=").append(this.toLocation)
		.append("]");
		return sb.toString();
	}
}
