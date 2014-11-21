package za.co.thoughtworks.trains.application;

import java.util.ArrayList;
import java.util.List;


public class TrackDescriptorList {

	private List<TrackDescriptor> trackList;
//	private Map<Location, List<TrackDescriptor>> locationList;
	
	public TrackDescriptorList() {
		trackList = new ArrayList<TrackDescriptor>();
//		locationList = new HashMap<Location, List<TrackDescriptor>>();
	}

	public void add(TrackDescriptor aTrack) {
		this.trackList.add(aTrack);
		/*Location fromLocation = aTrack.getFromLocation();
		if(locationList.containsKey(fromLocation)) {
			locationList.get(fromLocation).add(aTrack);
		}
		else {
			List<TrackDescriptor> trackListForLocation = new ArrayList<TrackDescriptor>();
			trackListForLocation.add(aTrack);
			locationList.put(fromLocation, trackListForLocation);
		}*/
	}

	public List<TrackDescriptor> getTrackDescriptorList() {
		return trackList;
	}
}
