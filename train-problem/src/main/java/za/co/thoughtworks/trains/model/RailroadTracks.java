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
		if(locationList.contains(fromLocation)) {
//			fromLocation
		}
		else {
			locationList.add(fromLocation);
		}
	}

}
