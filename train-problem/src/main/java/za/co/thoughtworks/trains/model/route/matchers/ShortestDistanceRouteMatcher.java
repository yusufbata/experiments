package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;

public class ShortestDistanceRouteMatcher  extends AbstractRouteMatcher  
	implements RouteMatcher<ShortestDistanceRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ShortestDistanceRouteMatcher> {

	public ShortestDistanceRouteMatcher(List<String> targetPath) {
		super(targetPath);
	}

	@Override
	public boolean isRouteValid(RouteMatcherInput routeMatcherInput) {
		return true;
	}

	@Override
	public boolean isRouteComplete(RouteMatcherInput routeMatcherInput) {
		return false;
	}

	@Override
	public ShortestDistanceRouteMatcher clone() {		
		return new ShortestDistanceRouteMatcher(new ArrayList<String>(this.targetPath));
	}
}
