package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.model.trackmaps.Location;

public interface LocationRepository {

	Location findLocationWithId(String startLocationId);

}