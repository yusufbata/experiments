package za.co.thoughtworks.trains.model.path.finders;

import za.co.thoughtworks.trains.application.services.RouteSpec;
import za.co.thoughtworks.trains.model.trackmaps.Location;

public class PathFinderFactory {

	public PathFinder constructPathFinder(RouteSpec routeSpec, Location startLocation, Location endLocation) {
		return new RoutingEngine();
	}

}
