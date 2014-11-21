package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.RouteSpec;

public class RouteMatchersFactory {

	public static RouteMatchers constructRouteMatchers(RouteSpec routeSpec) {
		List<RouteMatcher<?>> routeMatcherItemList = new ArrayList<RouteMatcher<?>>();

		if (routeSpec.getMaximumStops() > 0) {
			MaximumStopsRouteMatcher maximumStopsRouteMatcher = 
					new MaximumStopsRouteMatcher(routeSpec.getTargetPath(), routeSpec.getMaximumStops());
			routeMatcherItemList.add(maximumStopsRouteMatcher);
		}
		if (routeSpec.getExactNumberOfStops() > 0) {
			ExactNumberOfStopsRouteMatcher exactNumberOfStopsRouteMatcher = 
					new ExactNumberOfStopsRouteMatcher(routeSpec.getTargetPath(), routeSpec.getExactNumberOfStops());
			routeMatcherItemList.add(exactNumberOfStopsRouteMatcher);
		}
		if (routeSpec.getMaximumDistance() > 0) {
			MaximumDistanceRouteMatcher maximumDistanceRouteMatcher = 
					new MaximumDistanceRouteMatcher(routeSpec.getTargetPath(), routeSpec.getMaximumDistance());
			routeMatcherItemList.add(maximumDistanceRouteMatcher);
		}
		
		// NOTE: Order is important for this matcher
		if (routeSpec.shouldFindRouteWithShortestDistance()) {
			ShortestDistanceRouteMatcher shortestDistanceRouteMatcher = 
					new ShortestDistanceRouteMatcher(routeSpec.getTargetPath());
			routeMatcherItemList.add(shortestDistanceRouteMatcher);
		}
		
		if (routeMatcherItemList.size() == 0) {
			ExactRouteMatcher exactRouteMatcher = new ExactRouteMatcher(routeSpec.getTargetPath());
			routeMatcherItemList.add(exactRouteMatcher);
		}
		
		RouteMatchers routeMatchers = new RouteMatchers(routeMatcherItemList);
		return routeMatchers;
	}

}
