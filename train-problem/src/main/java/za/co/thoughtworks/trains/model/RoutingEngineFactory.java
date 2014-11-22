package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.RouteSpec;
import za.co.thoughtworks.trains.model.trackmaps.Location;

public class RoutingEngineFactory {

	public RoutingEngine constructRoutingEngine(RouteSpec routeSpec, Location startLocation, Location endLocation) {
		return new RoutingEngine(startLocation, endLocation);
	}

}
