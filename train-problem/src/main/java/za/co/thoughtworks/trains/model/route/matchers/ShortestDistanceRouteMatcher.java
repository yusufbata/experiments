package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.Track;

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

	private int findShortestDistanceInRoutes(List<Route> routes) {
		int shortestDistance = Integer.MAX_VALUE; 
		
		for (Route route : routes) {
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
		if (routeMatcherInput.getTrackList().size() > 0) {
			Track lastTrack = ListUtils.getLastItemFromList(routeMatcherInput.getTrackList());
			return lastTrack.endLocationHasId(lastLocationId);
		}
		return false;
	}

	@Override
	public ShortestDistanceRouteMatcher clone() {		
		return new ShortestDistanceRouteMatcher(new ArrayList<String>(this.targetPath));
	}
}
