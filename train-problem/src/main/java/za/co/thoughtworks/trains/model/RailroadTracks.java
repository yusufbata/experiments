package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;


public class RailroadTracks {

	private List<Track> trackList;
//	private Map<Location, List<Track>> locationList;
	
	public RailroadTracks() {
		trackList = new ArrayList<Track>();
//		locationList = new HashMap<Location, List<Track>>();
	}

	public void add(Track aTrack) {
		this.trackList.add(aTrack);
		/*Location fromLocation = aTrack.getFromLocation();
		if(locationList.containsKey(fromLocation)) {
			locationList.get(fromLocation).add(aTrack);
		}
		else {
			List<Track> trackListForLocation = new ArrayList<Track>();
			trackListForLocation.add(aTrack);
			locationList.put(fromLocation, trackListForLocation);
		}*/
	}

	public List<Track> getTrackList() {
		return trackList;
	}
}
