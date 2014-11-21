package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

public class Location {

	private String id;
	private List<Track> outgoingTracks;
	
	public Location(String locationId) {
		this.id = locationId;
		this.outgoingTracks = new ArrayList<Track>();
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

	public void addOutgoingTrack(Track track) {
		this.outgoingTracks.add(track);
	}

	public List<Track> getOutgoingTracks() {
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
