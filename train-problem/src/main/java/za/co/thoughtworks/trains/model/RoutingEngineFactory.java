package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.RouteSpec;

public class RoutingEngineFactory {

	public RoutingEngine constructRoutingEngine(RouteSpec routeSpec, Location startLocation, Location endLocation) {
		return new RoutingEngine(startLocation, endLocation, routeSpec.getToTownList());
	}

}
