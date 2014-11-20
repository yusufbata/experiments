/**
 * 
 */
package za.co.thoughtworks.trains.application;

import java.util.List;

import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.RailroadTracks;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.RoutingEngine;

/**
 * @author Yusuf
 *
 */
public class RailroadApplicationService {

	private final LocationRepository locationRepository;
	
	public RailroadApplicationService(RailroadTracks railroadTracks) {
		LocationFactory locationFactory = ApplicationRegistry.getLocationFactory();
		List<Location> locationList = locationFactory.constructLocationsFrom(railroadTracks);
		this.locationRepository = ApplicationRegistry.getLocationRepository(locationList);
	}

	public Route findRouteWith(RouteSpec routeSpec) {
		Location startLocation = locationRepository.findLocationWithId(routeSpec.getStartLocationId());
		Location endLocation = locationRepository.findLocationWithId(routeSpec.getEndLocationId());
		RoutingEngine routingEngine = ApplicationRegistry.getRoutingEngineFactory().constructRoutingEngine(routeSpec);
		
		Route route = routingEngine.findRouteBetweenTwoLocations(startLocation, endLocation);
		return route;
	}

}
