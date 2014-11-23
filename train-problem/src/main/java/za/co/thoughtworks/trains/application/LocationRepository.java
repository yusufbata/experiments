package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.model.trackmaps.Location;

/**
 * Used to store an retrieve a collection of Location entities. 
 * 
 * @author Yusuf
 *
 */
public interface LocationRepository {

	Location findLocationWithId(String startLocationId);

}