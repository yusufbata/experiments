package za.co.thoughtworks.trains.application.services;

import za.co.thoughtworks.trains.model.trackmaps.Location;

public class TrackDescriptor {

	private String fromLocationId;
	private String toLocationId;
	private Distance distance;

	public TrackDescriptor(String fromLocationId, String toLocationId, Distance trackDistance) {
		this.fromLocationId = fromLocationId;
		this.toLocationId = toLocationId;
		this.distance = trackDistance;
	}

	public Distance getDistance() {
		return this.distance;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[TrackDescriptor fromLocationId = ").append(this.fromLocationId)
			.append(", toLocationId=").append(this.toLocationId)
		.append("]");
		return sb.toString();
	}
	
	@Override
	public Object clone() {
		TrackDescriptor clone = new TrackDescriptor(this.fromLocationId, this.toLocationId, this.distance);
		return clone;
	}

	public String getFromLocationId() {
		return fromLocationId;
	}

	public String getToLocationId() {
		return toLocationId;
	}

	

}
