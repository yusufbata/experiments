package za.co.thoughtworks.trains.model.route.matchers;

import java.util.List;

import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.Track;

public class RouteMatcherInput {
	public Route route;
	public List<Location> completedLocationList;
	public List<Route> allCompletedRoutes;
	public List<Track> trackList;

	private RouteMatcherInput(Route route,
			List<Location> completedLocationList, List<Track> trackList, List<Route> allCompletedRoutes) {
		this.route = route;
		this.completedLocationList = completedLocationList;
		this.allCompletedRoutes = allCompletedRoutes;
		this.trackList = trackList;
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

	public List<Track> getTrackList() {
		return trackList;
	}

	public static RouteMatcherInput construct(
			Route route, List<Location> completedLocationList, List<Track> trackList, List<Route> allCompletedRoutes) {
		return new RouteMatcherInput(route, completedLocationList, trackList, allCompletedRoutes);
	}
	
	
}