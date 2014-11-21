package za.co.thoughtworks.trains.application;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Track;

public class LocationFactory {
	
	private List<Location> locationList = new ArrayList<>();
	
	public List<Location> constructLocationsFrom(TrackDescriptorList trackDescriptorList) {
		
		for (TrackDescriptor trackDescriptor : trackDescriptorList.getTrackDescriptorList()) {
			addLocationToListIfDoesntExist(locationList, trackDescriptor.getFromLocationId());
			addLocationToListIfDoesntExist(locationList, trackDescriptor.getToLocationId());
		}
		List<Track> trackList = constructTrackListFrom(trackDescriptorList);
		for (Track track : trackList) {
			track.getFromLocation().addOutgoingTrack(track);
		}
		return locationList;
	}

	private List<Track> constructTrackListFrom(
			TrackDescriptorList trackDescriptorList) {
		List<Track> result = new ArrayList<>();
		for (TrackDescriptor trackDescriptor : trackDescriptorList.getTrackDescriptorList()) {
			Location fromLocation = this.findLocationWithId(trackDescriptor.getFromLocationId());
			Location toLocation = this.findLocationWithId(trackDescriptor.getToLocationId());
			Track newTrack = new Track(fromLocation, toLocation, trackDescriptor.getDistance());
			result.add(newTrack);
		}
		return result;
	}

	private Location findLocationWithId(String locationId) {
		for (Location location : locationList) {
			if (location.hasId(locationId)) {
				return location;
			}
		}
		throw new RuntimeException("Location not found for locationId=" + locationId);
	}

	private void addLocationToListIfDoesntExist(List<Location> locationList, String locationId) {
		Location newLocation = Location.create(locationId);
		if(!locationList.contains(newLocation)) {
			locationList.add(newLocation);
		}
	}
}
