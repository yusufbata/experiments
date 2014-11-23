package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.path.Path;

public class ShortestDistanceRouteMatcher  extends AbstractRouteMatcher  
	implements RouteMatcher<ShortestDistanceRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ShortestDistanceRouteMatcher> {

	public ShortestDistanceRouteMatcher(List<String> targetPath) {
		super(targetPath);
	}

	@Override
	public boolean isRouteValid(RouteMatcherInput routeMatcherInput) {
		if (routeMatcherInput.getRoute().getTotalDistance().value() > findShortestDistanceInRoutes(routeMatcherInput.getAllCompletedRoutes())) {
			return false;
		}
		return true;
	}

	private int findShortestDistanceInRoutes(List<? extends Path> routes) {
		int shortestDistance = Integer.MAX_VALUE; 
		
		for (Path route : routes) {
			int routeDistance = route.getTotalDistance().value();
			if (routeDistance < shortestDistance) {
				shortestDistance = routeDistance;
			}
		}
		return shortestDistance;
	}

	@Override
	public boolean isRouteComplete(RouteMatcherInput routeMatcherInput) {
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (routeMatcherInput.getRoute().getCurrentNumberOfStops() > 0) {
			return routeMatcherInput.getRoute().hasEndLocationId(lastLocationId);
		}
		return false;
	}

	@Override
	public ShortestDistanceRouteMatcher clone() {		
		return new ShortestDistanceRouteMatcher(new ArrayList<String>(this.targetPath));
	}
}
