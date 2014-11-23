package za.co.thoughtworks.trains.application.services;

import za.co.thoughtworks.trains.adapters.InvalidConfigurationException;


public class TrackDescriptor {

	private final String fromLocationId;
	private final String toLocationId;
	private final Distance distance;

	public TrackDescriptor(String fromLocationId, String toLocationId, Distance trackDistance) {
		if (fromLocationId.compareTo(toLocationId) == 0) {
			throw new InvalidConfigurationException("Track cannot have same start and end locations. location: " + fromLocationId);
		}
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
		sb.append("[TrackDescriptor ").append(this.fromLocationId)
			.append("-").append(this.toLocationId).append("-").append(this.distance.value())
		.append("]");
		return sb.toString();
	}
	
	@Override
	public TrackDescriptor clone() {
		TrackDescriptor clone = new TrackDescriptor(this.fromLocationId, this.toLocationId, this.distance);
		return clone;
	}

	public String getFromLocationId() {
		return fromLocationId;
	}

	public String getToLocationId() {
		return toLocationId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof TrackDescriptor) {
			TrackDescriptor other = (TrackDescriptor)obj;
			if (this.fromLocationId.compareTo(other.fromLocationId) == 0
					&& this.toLocationId.compareTo(other.toLocationId) == 0
					&& this.distance.equals(other.distance)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int prime = 13;
		int hash = prime * (prime + this.fromLocationId.hashCode());
		hash = prime * (hash + this.fromLocationId.hashCode());
		hash =  prime * (hash + this.fromLocationId.hashCode());
		return hash;
	}

	public boolean hasSameEndpoints(TrackDescriptor anotherTrack) {
		if (this.fromLocationId.compareTo(anotherTrack.fromLocationId) == 0
				&& this.toLocationId.compareTo(anotherTrack.toLocationId) == 0) {
			return true;
		}
		return false;
	}

}
