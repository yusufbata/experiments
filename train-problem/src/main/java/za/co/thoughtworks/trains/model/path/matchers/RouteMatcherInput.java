package za.co.thoughtworks.trains.model.path.matchers;

import java.util.List;

import za.co.thoughtworks.trains.model.Path;

public class RouteMatcherInput {
	public Path route;
	public List<? extends Path> allCompletedRoutes;

	private RouteMatcherInput(Path route, List<? extends Path> allCompletedRoutes) {
		this.route = route;
		this.allCompletedRoutes = allCompletedRoutes;
	}

	public Path getRoute() {
		return route;
	}

	public List<? extends Path> getAllCompletedRoutes() {
		return allCompletedRoutes;
	}

	public static RouteMatcherInput construct(
			Path route, List<? extends Path> allCompletedRoutes) {
		return new RouteMatcherInput(route, allCompletedRoutes);
	}
	
	
}