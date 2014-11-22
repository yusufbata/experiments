package za.co.thoughtworks.trains.model.path.matchers;

import java.util.List;

import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.Track;

public class RouteMatcherInput {
	public Route route;
	public List<Location> completedLocationList;
	public List<Route> allCompletedRoutes;

	private RouteMatcherInput(Route route,
			List<Location> completedLocationList, List<Route> allCompletedRoutes) {
		this.route = route;
		this.completedLocationList = completedLocationList;
		this.allCompletedRoutes = allCompletedRoutes;
	}

	public Route getRoute() {
		return route;
	}

	public List<Location> getCompletedLocationList() {
		return completedLocationList;
	}

	public List<Route> getAllCompletedRoutes() {
		return allCompletedRoutes;
	}

	public static RouteMatcherInput construct(
			Route route, List<Location> completedLocationList, List<Route> allCompletedRoutes) {
		return new RouteMatcherInput(route, completedLocationList, allCompletedRoutes);
	}
	
	
}