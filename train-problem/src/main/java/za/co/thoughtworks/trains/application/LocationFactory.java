package za.co.thoughtworks.trains.application;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.model.Location;

public class LocationFactory {
	
	public List<Location> constructLocationsFrom(TrackDescriptorList trackDescriptorList) {
		List<Location> locationList = new ArrayList<>();
		for (TrackDescriptor trackDescriptor : trackDescriptorList.getTrackList()) {
			addLocationToListIfDoesntExist(locationList, trackDescriptor.getFromLocation());
			addLocationToListIfDoesntExist(locationList, trackDescriptor.getToLocation());
			trackDescriptor.getFromLocation().addOutgoingTrack(trackDescriptor);
		}
		return locationList;
	}

	private void addLocationToListIfDoesntExist(List<Location> locationList, Location fromLocation) {
		if(!locationList.contains(fromLocation)) {
			locationList.add(fromLocation);
		}
	}
}
