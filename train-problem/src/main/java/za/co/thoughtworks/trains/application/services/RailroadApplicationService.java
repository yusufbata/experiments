/**
 * 
 */
package za.co.thoughtworks.trains.application.services;

import java.util.List;

import za.co.thoughtworks.trains.application.ApplicationRegistry;
import za.co.thoughtworks.trains.application.LocationRepository;
import za.co.thoughtworks.trains.model.RoutingEngine;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchers;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchersFactory;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.LocationFactory;

/**
 * @author Yusuf
 *
 */
public class RailroadApplicationService {

	private final LocationRepository locationRepository;
	
	public RailroadApplicationService(TrackDescriptorList trackDescriptorList) {
		LocationFactory locationFactory = ApplicationRegistry.getLocationFactory();
		List<Location> locationList = locationFactory.constructLocationsFrom(trackDescriptorList);
		this.locationRepository = ApplicationRegistry.getLocationRepository(locationList);
	}

	public Path findSingleRouteUsing(RouteSpec routeSpec) {
		MatchingPaths matchingPaths = findAllRoutesUsing(routeSpec);
		return matchingPaths.findTheOnlyPath();
	}

	public MatchingPaths findAllRoutesUsing(RouteSpec routeSpec) {
		// System.out.println("finding all routes using: " + routeSpec);
		RouteMatchers routeMatchers = RouteMatchersFactory.constructRouteMatchers(routeSpec);
		Location startLocation = locationRepository.findLocationWithId(routeSpec.getStartLocationId());
		Location endLocation = locationRepository.findLocationWithId(routeSpec.getEndLocationId());
		RoutingEngine routingEngine = ApplicationRegistry.getRoutingEngineFactory().constructRoutingEngine(routeSpec, startLocation, endLocation);
		
		MatchingPaths matchingPaths = routingEngine.findRoute(routeMatchers);
		return matchingPaths;
	}

}
