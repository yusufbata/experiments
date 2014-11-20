/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.List;


/**
 * @author Yusuf
 *
 */
public class RoutingEngine {

	public Route findRouteBetweenTwoLocations(Location startLocation, Location endLocation) {
		// Get all tracks from start location
//		startLocation.getOutgoingTracks();
		
		// Create new Route for each track, using RouteMatchers copy for each one -> RouteList created
		
		// For 'incomplete' routes
		// Remove routes that do not match route spec
		// Get next location from each valid track
		// repeat above steps for each location
		
		List<Track> outgoingTracks = startLocation.getOutgoingTracks();
		for (Track track : outgoingTracks) {
			if (track.endLocationEquals(endLocation)) {
				return new Route(track);
			}
		}
		return new NoRoute();
	}

}
