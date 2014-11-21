package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.TrackDescriptor;


public class Location {

	private String id;
	private List<TrackDescriptor> outgoingTracks;
	
	public Location(String locationId) {
		this.id = locationId;
		this.outgoingTracks = new ArrayList<TrackDescriptor>();
	}

	public static Location create(String locationId) {
		return new Location(locationId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Location) {
			Location other = (Location)obj;
			if (this.id == other.id) {
				return true;
			}
		}
		return false;
	}

	public String getId() {
		return id;
	}

	public void addOutgoingTrack(TrackDescriptor trackDescriptor) {
		this.outgoingTracks.add(trackDescriptor);
	}

	public List<TrackDescriptor> getOutgoingTracks() {
		return outgoingTracks;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Location id = ").append(this.id).append("]");
		return sb.toString();
	}

	public boolean hasId(String id) {
		return this.id == id;
	}
}
