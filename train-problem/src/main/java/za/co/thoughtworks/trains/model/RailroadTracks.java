package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;


public class RailroadTracks {

//	private List<Track> trackList;
	private List<Location> locationList;
	
	public RailroadTracks() {
		locationList = new ArrayList<Location>();
	}

	public void add(Track aTrack) {
		Location fromLocation = aTrack.getFromLocation();
//		this.trackList.add(aTrack);
		if(locationList.contains(fromLocation)) {
			
		}
		else {
			
		}
	}

}
