package za.co.thoughtworks.trains.model.path.matchers;

import java.util.List;

import za.co.thoughtworks.trains.model.path.Path;

public class PathMatcherInput {
	public Path route;
	public List<? extends Path> allCompletedRoutes;

	private PathMatcherInput(Path route, List<? extends Path> allCompletedRoutes) {
		this.route = route;
		this.allCompletedRoutes = allCompletedRoutes;
	}

	public Path getRoute() {
		return route;
	}

	public List<? extends Path> getAllCompletedRoutes() {
		return allCompletedRoutes;
	}

	public static PathMatcherInput construct(
			Path route, List<? extends Path> allCompletedRoutes) {
		return new PathMatcherInput(route, allCompletedRoutes);
	}
	
	
}