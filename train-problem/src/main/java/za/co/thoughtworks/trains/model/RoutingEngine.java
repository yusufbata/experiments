/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Yusuf
 *
 */
public class RoutingEngine {

	private final Location startLocation;
	private final Location endLocation;
	private final List<String> toTownList;
	
	private int maxIterations = 20;
	private int currentIterations = 0;

	public RoutingEngine(Location startLocation, Location endLocation,
			List<String> toTownList) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.toTownList = toTownList;
	}

	public IRoute findRoute() {
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
		
		Route startingRoute = new Route(startLocation, toTownList);
		incompleteMatchingRoutes.add(startingRoute);
		
		findAllValidRoutes(completedRoutes, incompleteMatchingRoutes);
		
		if (completedRoutes.size() > 0) {
			return completedRoutes.get(0);
		}
		
		return new NoRoute();
	}

	private void findAllValidRoutes(List<Route> completedRoutes,
			List<Route> incompleteMatchingRoutes) {
		System.out.println("completedRoutes=" + completedRoutes);
		System.out.println("incompleteMatchingRoutes=" + incompleteMatchingRoutes);
		
		currentIterations++;
		if (currentIterations == maxIterations) {
			System.err.println("Not looking further. Recursions max limit reached: " + currentIterations);
			return;
		}
		List<Route> newIncompleteMatchingRoutes = new ArrayList<Route>();
		for (Route potentialRoute : incompleteMatchingRoutes) {
//			Route potentialRoute = 
//					it.next();
//					incompleteMatchingRoutes.get(0);
			if (potentialRoute.isValid()) {
				if (potentialRoute.isComplete()) {
//					incompleteMatchingRoutes.remove(potentialRoute);
//					it.remove();
					completedRoutes.add(potentialRoute);
				}
				else {
					List<Route> morePotentialRoutes = potentialRoute.findNextPossibleRoutes();
					newIncompleteMatchingRoutes.addAll(morePotentialRoutes);
				}
			}
//			else {
//				it.remove();
//			}
		}
		if (newIncompleteMatchingRoutes.size() > 0) {
			findAllValidRoutes(completedRoutes, newIncompleteMatchingRoutes);
		}
	}

}
