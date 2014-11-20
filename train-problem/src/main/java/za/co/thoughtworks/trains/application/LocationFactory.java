package za.co.thoughtworks.trains.application;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.RailroadTracks;
import za.co.thoughtworks.trains.model.Track;

public class LocationFactory {
	
	public List<Location> constructLocationsFrom(RailroadTracks railroadTracks) {
		List<Location> locationList = new ArrayList<>();
		for (Track track : railroadTracks.getTrackList()) {
			addLocationToListIfDoesntExist(locationList, track.getFromLocation());
			addLocationToListIfDoesntExist(locationList, track.getToLocation());
			track.getFromLocation().addOutgoingTrack(track);
		}
		return locationList;
	}

	private void addLocationToListIfDoesntExist(List<Location> locationList, Location fromLocation) {
		if(!locationList.contains(fromLocation)) {
			locationList.add(fromLocation);
		}
	}
}
