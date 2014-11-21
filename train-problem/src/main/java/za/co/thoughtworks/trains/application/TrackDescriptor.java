package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.model.Location;

public class TrackDescriptor {

	private Location fromLocation;
	private Location toLocation;
	private Distance distance;

	public TrackDescriptor(String fromLocationId, String toLocationId, int trackDistance) {
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[TrackDescriptor fromLocation = ").append(this.fromLocation)
			.append(", toLocation=").append(this.toLocation)
		.append("]");
		return sb.toString();
	}
	
	@Override
	public Object clone() {
		TrackDescriptor clone = new TrackDescriptor(this.fromLocation.getId(), this.toLocation.getId(), this.distance.value());
		return clone;
	}

}
