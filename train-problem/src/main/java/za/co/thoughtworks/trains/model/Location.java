package za.co.thoughtworks.trains.model;

public class Location {

	private String locationId;
	
	public Location(String locationId) {
		this.locationId = locationId;
	}

	public static Location create(String locationId) {
		return new Location(locationId);
	}

}
