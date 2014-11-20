package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.RouteSpec;

public class RoutingEngineFactory {

	public RoutingEngine constructRoutingEngine(RouteSpec routeSpec) {
		return new RoutingEngine();
	}

}
