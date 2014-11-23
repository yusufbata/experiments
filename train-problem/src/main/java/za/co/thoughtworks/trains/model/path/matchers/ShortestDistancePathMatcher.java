package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.path.Path;

public class ShortestDistancePathMatcher  extends AbstractPathMatcher  
	implements PathMatcher<ShortestDistancePathMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ShortestDistancePathMatcher> {

	public ShortestDistancePathMatcher(List<String> targetPath) {
		super(targetPath);
	}

	@Override
	public boolean isRouteValid(PathMatcherInput pathMatcherInput) {
		if (pathMatcherInput.getRoute().getTotalDistance().value() > findShortestDistanceInRoutes(pathMatcherInput.getAllCompletedRoutes())) {
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
	public boolean isRouteComplete(PathMatcherInput pathMatcherInput) {
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (pathMatcherInput.getRoute().getCurrentNumberOfStops() > 0) {
			return pathMatcherInput.getRoute().hasEndLocationId(lastLocationId);
		}
		return false;
	}

	@Override
	public ShortestDistancePathMatcher clone() {		
		return new ShortestDistancePathMatcher(new ArrayList<String>(this.targetPath));
	}
}
