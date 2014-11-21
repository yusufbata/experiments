package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.Cloneable;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;

public class Location implements Cloneable<Location> {

	private final String id;
	private final List<Track> outgoingTracks;
	
	Location(String locationId) {
		this.id = locationId;
		this.outgoingTracks = new ArrayList<Track>();
	}
	
	protected Location(String locationId, List<Track> outgoingTracks) {
		this.id = locationId;
		this.outgoingTracks = outgoingTracks;
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

	public List<Track> getOutgoingTracks() {
		return Collections.unmodifiableList(outgoingTracks);
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

	public Location addOutgoingTrack(Track track) {
		// TODO: Validate duplicates here!!!
		
		List<Track> outgoingTracksClone = getCloneOfOutgoingTracks();
		outgoingTracksClone.add(track);
		Location clone = new Location(this.id, outgoingTracksClone);
		return clone;
	}

	private List<Track> getCloneOfOutgoingTracks() {
		List<Track> currentOutgoingTracks = ListUtils.cloneListWithContents(this.getOutgoingTracks());
		return currentOutgoingTracks;
	}
	
	public Location clone() {
		Location clone = new Location(this.id, getCloneOfOutgoingTracks());
		return clone;
	}
}