package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.RouteSpec;

public class RouteMatchersFactory {

	public static RouteMatchers constructRouteMatchers(RouteSpec routeSpec) {
		List<RouteMatcher<?>> routeMatcherItemList = new ArrayList<RouteMatcher<?>>();
		
		if (routeSpec.getMaximumStops() == 0) {
			ExactRouteMatcher exactRouteMatcher = new ExactRouteMatcher(routeSpec.getTargetPath());
			routeMatcherItemList.add(exactRouteMatcher);
		}
		else {
			MaximumStopsRouteMatcher maximumStopsRouteMatcher = 
					new MaximumStopsRouteMatcher(routeSpec.getTargetPath(), routeSpec.getMaximumStops());
			routeMatcherItemList.add(maximumStopsRouteMatcher);
		}
		
		RouteMatchers routeMatchers = new RouteMatchers(routeMatcherItemList);
		return routeMatchers;
	}

}
