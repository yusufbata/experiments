/**
 * 
 */
package za.co.thoughtworks.trains.application;

import java.util.List;

import za.co.thoughtworks.trains.model.IRoute;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.LocationFactory;
import za.co.thoughtworks.trains.model.MatchingRoutes;
import za.co.thoughtworks.trains.model.RoutingEngine;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchers;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchersFactory;

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

	public IRoute findSingleRouteUsing(RouteSpec routeSpec) {
		MatchingRoutes matchingRoutes = findAllRoutesUsing(routeSpec);
		return matchingRoutes.getTheOnlyRoute();
	}

	public MatchingRoutes findAllRoutesUsing(RouteSpec routeSpec) {
		RouteMatchers routeMatchers = RouteMatchersFactory.constructRouteMatchers(routeSpec);
		Location startLocation = locationRepository.findLocationWithId(routeSpec.getStartLocationId());
		Location endLocation = locationRepository.findLocationWithId(routeSpec.getEndLocationId());
		RoutingEngine routingEngine = ApplicationRegistry.getRoutingEngineFactory().constructRoutingEngine(routeSpec, startLocation, endLocation);
		
		MatchingRoutes matchingRoutes = routingEngine.findRoute(routeMatchers);
		return matchingRoutes;
	}

}
