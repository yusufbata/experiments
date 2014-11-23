/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.services.MatchingPaths;
import za.co.thoughtworks.trains.application.services.NoPath;
import za.co.thoughtworks.trains.application.services.Path;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchers;
import za.co.thoughtworks.trains.model.trackmaps.Location;


/**
 * @author Yusuf
 *
 */
public class RoutingEngine {

	private final Location startLocation;
	private final Location endLocation;
	
//	private int maxIterations = 20;
//	private int currentIterations = 0;

	public RoutingEngine(Location startLocation, Location endLocation) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
	}

	public MatchingPaths findRoute(RouteMatchers routeMatchers) {
		if (startLocation == null || endLocation == null) {
			throw new IllegalArgumentException("Routing failed. Start location and End location cannot be null");
		}
		
		// Get all tracks from start location
//		startLocation.getOutgoingTracks();
		
		// Create new Route for each track, using RouteMatchers copy for each one -> RouteList created
		
		// For 'incomplete' routes
		// Remove routes that do not match route spec
		// Get next location from each valid track
		// repeat above steps for each location
		
		List<Path> completedRoutes = new ArrayList<Path>();
		List<Path> incompleteMatchingRoutes = new ArrayList<>();
		
		Path startingRoute = new Route(routeMatchers, startLocation);
		incompleteMatchingRoutes.add(startingRoute);
		
		findAllValidRoutes(completedRoutes, incompleteMatchingRoutes);
		
		if (completedRoutes.size() > 0) {
			// required for shortest distance matcher
			// perhaps only run required matchers - will need flag to identify global matchers
			completedRoutes = validateAllCompletedRoutes(completedRoutes);
			return MatchingPaths.construct(completedRoutes);
		}
		
		return MatchingPaths.noPathFound();
	}

	private List<Path> validateAllCompletedRoutes(List<Path> allCompletedRoutes) {
		List<Path> validatedCompletedRoutes = new ArrayList<Path>();
		for (Path route : allCompletedRoutes) {
			if(route.isValid(allCompletedRoutes))
				validatedCompletedRoutes.add(route);
		}
		return validatedCompletedRoutes;
	}

	private void findAllValidRoutes(List<Path> completedRoutes,
			List<Path> previousIncompleteMatchingRoutes) {

		List<Path> newIncompleteMatchingRoutes = new ArrayList<Path>();
		for (Path potentialRoute : previousIncompleteMatchingRoutes) {
			if (potentialRoute.isValid(completedRoutes)) {
				if (potentialRoute.isComplete(completedRoutes)) {
					completedRoutes.add(potentialRoute);
				}

				List<Path> morePotentialRoutes = potentialRoute.findNextPossiblePaths();
				newIncompleteMatchingRoutes.addAll(morePotentialRoutes);
			}
		}
		if (newIncompleteMatchingRoutes.size() > 0) {
			findAllValidRoutes(completedRoutes, newIncompleteMatchingRoutes);
		}
	}

}
