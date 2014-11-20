package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.Distance;

public class Location {

	private String locationId;
	
	public Location(String locationId) {
		this.locationId = locationId;
	}

	public static Location create(String locationId) {
		return new Location(locationId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Location) {
			Location other = (Location)obj;
			if (this.locationId == other.locationId) {
				return true;
			}
		}
		return false;
	}

}
