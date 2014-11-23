package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.RouteSpec;

public class PathMatchersFactory {

	private PathMatchersFactory(){}
	
	public static PathMatchers constructRouteMatchers(RouteSpec routeSpec) {
		List<PathMatcher<?>> routeMatcherItemList = new ArrayList<PathMatcher<?>>();

		if (routeSpec.getMaximumStops() > 0) {					
			routeMatcherItemList.add(
					new MaximumStopsPathMatcher(routeSpec.getTargetPath(), routeSpec.getMaximumStops()));
		}
		if (routeSpec.getExactNumberOfStops() > 0) {
			routeMatcherItemList.add(
					new ExactNumberOfStopsPathMatcher(routeSpec.getTargetPath(), routeSpec.getExactNumberOfStops()));
		}
		if (routeSpec.getMaximumDistance() > 0) {
			routeMatcherItemList.add(
					new MaximumDistancePathMatcher(routeSpec.getTargetPath(), routeSpec.getMaximumDistance()));
		}
		// NOTE: Order is important for this matcher
		if (routeSpec.shouldFindRouteWithShortestDistance()) {
			routeMatcherItemList.add(
					new ShortestDistancePathMatcher(routeSpec.getTargetPath()));
		}
		if (routeMatcherItemList.isEmpty()) {
			routeMatcherItemList.add(new ExactPathMatcher(routeSpec.getTargetPath()));
		}
		
		return new PathMatchers(routeMatcherItemList);
	}

}
