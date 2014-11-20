/**
 * 
 */
package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.RailroadTracks;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.RoutingEngine;

/**
 * @author Yusuf
 *
 */
public class RailroadApplicationService {

	private LocationRepository locationRepository;
	
	public RailroadApplicationService(RailroadTracks railroadTracks) {
		this.locationRepository = ApplicationRegistry.getLocationRepository(railroadTracks);
	}

	public Route findRouteWith(RouteSpec routeSpec) {
		Location startLocation = locationRepository.findLocationWithId(routeSpec.getStartLocationId());
		Location endLocation = locationRepository.findLocationWithId(routeSpec.getEndLocationId());
		RoutingEngine routingEngine = ApplicationRegistry.getRoutingEngineFactory().constructRoutingEngine(routeSpec);
		
		Route route = routingEngine.findRouteBetweenTwoLocations(startLocation, endLocation);
		return route;
	}

}
