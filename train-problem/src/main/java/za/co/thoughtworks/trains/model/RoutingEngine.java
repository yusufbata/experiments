/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.model.path.matchers.RouteMatchers;


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

	public MatchingRoutes findRoute(RouteMatchers routeMatchers) {
		if (startLocation == null || endLocation == null) {
			throw new IllegalArgumentException("Start location and End location cannot be null");
		}
		
		// Get all tracks from start location
//		startLocation.getOutgoingTracks();
		
		// Create new Route for each track, using RouteMatchers copy for each one -> RouteList created
		
		// For 'incomplete' routes
		// Remove routes that do not match route spec
		// Get next location from each valid track
		// repeat above steps for each location
		
		List<Route> completedRoutes = new ArrayList<Route>();
		List<Route> incompleteMatchingRoutes = new ArrayList<>();
		
		Route startingRoute = new Route(routeMatchers, startLocation);
		incompleteMatchingRoutes.add(startingRoute);
		
		findAllValidRoutes(completedRoutes, incompleteMatchingRoutes);
		
		if (completedRoutes.size() > 0) {
			// required for shortest distance matcher
			// perhaps only run required matchers - will need flag to identify global matchers
			completedRoutes = validateAllCompletedRoutes(completedRoutes);
			return MatchingRoutes.construct(completedRoutes);
		}
		
		return new NoRoute();
	}

	private List<Route> validateAllCompletedRoutes(List<Route> allCompletedRoutes) {
		List<Route> validatedCompletedRoutes = new ArrayList<Route>();
		for (Route route : allCompletedRoutes) {
			if(route.isValid(allCompletedRoutes))
				validatedCompletedRoutes.add(route);
		}
		return validatedCompletedRoutes;
	}

	private void findAllValidRoutes(List<Route> completedRoutes,
			List<Route> previousIncompleteMatchingRoutes) {

		List<Route> newIncompleteMatchingRoutes = new ArrayList<Route>();
		for (Route potentialRoute : previousIncompleteMatchingRoutes) {
			if (potentialRoute.isValid(completedRoutes)) {
				if (potentialRoute.isComplete(completedRoutes)) {
					completedRoutes.add(potentialRoute);
				}

				List<Route> morePotentialRoutes = potentialRoute.findNextPossibleRoutes();
				newIncompleteMatchingRoutes.addAll(morePotentialRoutes);
			}
		}
		if (newIncompleteMatchingRoutes.size() > 0) {
			findAllValidRoutes(completedRoutes, newIncompleteMatchingRoutes);
		}
	}

}
