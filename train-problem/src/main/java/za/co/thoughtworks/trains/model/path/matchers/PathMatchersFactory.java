package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.services.RouteSpec;

public class PathMatchersFactory {

	private PathMatchersFactory(){}
	
	public static PathMatchers constructRouteMatchers(RouteSpec routeSpec) {
		List<PathMatcher<?>> routeMatcherItemList = new ArrayList<PathMatcher<?>>();

		if (routeSpec.getMaximumStops() > 0) {
			MaximumStopsPathMatcher maximumStopsPathMatcher = 
					new MaximumStopsPathMatcher(routeSpec.getTargetPath(), routeSpec.getMaximumStops());
			routeMatcherItemList.add(maximumStopsPathMatcher);
		}
		if (routeSpec.getExactNumberOfStops() > 0) {
			ExactNumberOfStopsPathMatcher exactNumberOfStopsPathMatcher = 
					new ExactNumberOfStopsPathMatcher(routeSpec.getTargetPath(), routeSpec.getExactNumberOfStops());
			routeMatcherItemList.add(exactNumberOfStopsPathMatcher);
		}
		if (routeSpec.getMaximumDistance() > 0) {
			MaximumDistancePathMatcher maximumDistancePathMatcher = 
					new MaximumDistancePathMatcher(routeSpec.getTargetPath(), routeSpec.getMaximumDistance());
			routeMatcherItemList.add(maximumDistancePathMatcher);
		}
		
		// NOTE: Order is important for this matcher
		if (routeSpec.shouldFindRouteWithShortestDistance()) {
			ShortestDistancePathMatcher shortestDistancePathMatcher = 
					new ShortestDistancePathMatcher(routeSpec.getTargetPath());
			routeMatcherItemList.add(shortestDistancePathMatcher);
		}
		
		if (routeMatcherItemList.isEmpty()) {
			ExactPathMatcher exactPathMatcher = new ExactPathMatcher(routeSpec.getTargetPath());
			routeMatcherItemList.add(exactPathMatcher);
		}
		
		PathMatchers pathMatchers = new PathMatchers(routeMatcherItemList);
		return pathMatchers;
	}

}
