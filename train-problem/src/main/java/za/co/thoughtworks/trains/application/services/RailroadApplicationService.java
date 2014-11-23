/**
 * 
 */
package za.co.thoughtworks.trains.application.services;

import java.util.List;

import za.co.thoughtworks.trains.application.RouteSpec;
import za.co.thoughtworks.trains.model.path.MatchingPaths;
import za.co.thoughtworks.trains.model.path.Path;
import za.co.thoughtworks.trains.model.path.finders.PathFinder;
import za.co.thoughtworks.trains.model.path.matchers.PathMatchers;
import za.co.thoughtworks.trains.model.path.matchers.PathMatchersFactory;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.LocationFactory;
import za.co.thoughtworks.trains.model.trackmaps.LocationRepository;
import za.co.thoughtworks.trains.model.trackmaps.Route;
import za.co.thoughtworks.trains.model.trackmaps.TrackDescriptorList;

/**
 * Used for interacting with the application via adapters.
 * Primary entry point for core application.
 * 
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
		try {
			PathMatchers pathMatchers = PathMatchersFactory.constructRouteMatchers(routeSpec);
			PathFinder pathFinder = ApplicationRegistry.getPathFinderFactory().constructPathFinder(routeSpec);
			Path startingPath = constructInitialPathWithStartNode(routeSpec, pathMatchers);
			
			MatchingPaths matchingPaths = pathFinder.findPath(startingPath, pathMatchers);
			return matchingPaths;
		} catch (Exception e) {
			System.err.println("Returning NoPathFound. Ignoring error: " + e);
			return MatchingPaths.noPathFound();
		}
	}

	private Path constructInitialPathWithStartNode(RouteSpec routeSpec,
			PathMatchers pathMatchers) {
		Location startLocation = findStartLocation(routeSpec);
		Location endLocation = findEndLocation(routeSpec);
		Path startingPath = new Route(pathMatchers, startLocation);
		return startingPath;
	}

	private Location findEndLocation(RouteSpec routeSpec) {
		Location endLocation = locationRepository.findLocationWithId(routeSpec.getEndLocationId());
		validateLocationExists(endLocation, routeSpec.getEndLocationId());
		return endLocation;
	}

	private Location findStartLocation(RouteSpec routeSpec) {
		Location startLocation = locationRepository.findLocationWithId(routeSpec.getStartLocationId());
		validateLocationExists(startLocation, routeSpec.getStartLocationId());
		return startLocation;
	}

	private void validateLocationExists(Location locationToVerify, String locationId) {
		if (locationToVerify == null) {
			throw new IllegalArgumentException("Location specified does not exist [" + locationId + "]. Routing not possible");
		}
	}

}
