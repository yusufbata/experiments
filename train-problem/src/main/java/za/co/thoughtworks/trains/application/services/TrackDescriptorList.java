package za.co.thoughtworks.trains.application.services;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.adapters.InvalidConfigurationException;

/**
 * A collection of TrackDescriptors.
 * 
 * @author Yusuf
 *
 */
public class TrackDescriptorList {

	private List<TrackDescriptor> trackList;
	
	public TrackDescriptorList() {
		trackList = new ArrayList<TrackDescriptor>();
	}

	public void add(TrackDescriptor aTrack) {
		validateNewTrack(aTrack);
		this.trackList.add(aTrack);
	}

	private void validateNewTrack(TrackDescriptor newTrack) {
		for (TrackDescriptor currentTrack : trackList) {
			if (currentTrack.hasSameEndpoints(newTrack)) {
				throw new InvalidConfigurationException(
						"Cannot add more than one track with same start and end locations. " + newTrack);
			}
		}
	}

	public List<TrackDescriptor> getTrackDescriptorList() {
		return trackList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[TrackDescriptorList tracks=").append(this.trackList)
		.append("]");
		return sb.toString();
	}
}
