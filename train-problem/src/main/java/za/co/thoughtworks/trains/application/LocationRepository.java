package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.model.Location;

public interface LocationRepository {

	public abstract Location findLocationWithId(String startLocationId);

}