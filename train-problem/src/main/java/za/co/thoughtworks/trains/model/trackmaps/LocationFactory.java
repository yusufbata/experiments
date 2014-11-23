package za.co.thoughtworks.trains.model.trackmaps;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.services.TrackDescriptor;
import za.co.thoughtworks.trains.application.services.TrackDescriptorList;

public class LocationFactory {
	
	private List<Location> locationList = new ArrayList<>();
	
	public List<Location> constructLocationsFrom(TrackDescriptorList trackDescriptorList) {
		
		addAllLocationsToList(trackDescriptorList);
		
		List<Track> trackList = constructTrackListFrom(trackDescriptorList);
		for (Track track : trackList) {
			Location oldLocation = track.getFromLocation();
			oldLocation.addOutgoingTrack(track);
		}
		return locationList;
	}

	private void addAllLocationsToList(TrackDescriptorList trackDescriptorList) {
		for (TrackDescriptor trackDescriptor : trackDescriptorList.getTrackDescriptorList()) {
			addLocationToListIfDoesntExist(locationList, trackDescriptor.getFromLocationId());
			addLocationToListIfDoesntExist(locationList, trackDescriptor.getToLocationId());
		}
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
		throw new IllegalArgumentException("Location not found for locationId=" + locationId);
	}

	private void addLocationToListIfDoesntExist(List<Location> locationList, String locationId) {
		Location newLocation = new Location(locationId);
		if(!locationList.contains(newLocation)) {
			locationList.add(newLocation);
		}
	}
}
