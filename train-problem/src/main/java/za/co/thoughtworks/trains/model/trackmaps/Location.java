package za.co.thoughtworks.trains.model.trackmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import za.co.thoughtworks.trains.application.InvalidConfigurationException;
import za.co.thoughtworks.trains.infrastructure.utils.Cloneable;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;

/**
 * Entity representing a specific location on a track map.
 * Has links to the outgoing set of tracks.
 * 
 * @author Yusuf
 *
 */
public class Location {

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
			if (this.id.equals(other.id)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (37 * (37 + this.id.hashCode()));
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
		return this.id.compareTo(id) == 0;
	}

	public void addOutgoingTrack(Track track) {
		validateNewTrack(track);
		this.outgoingTracks.add(track);
	}

	private void validateNewTrack(Track newTrack) {
		for (Track track : outgoingTracks) {
			if (track.hasSameEndpoints(newTrack)) {
				throw new InvalidConfigurationException("Cannot add more than one track with same end points to location");
			}
		}
	}
}