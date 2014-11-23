package za.co.thoughtworks.trains.model.trackmaps;


/**
 * Used to store an retrieve a collection of Location entities. 
 * 
 * @author Yusuf
 *
 */
public interface LocationRepository {

	Location findLocationWithId(String startLocationId);

}