package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.RouteSpec;

public class RouteMatchersFactory {

	public static RouteMatchers constructRouteMatchers(RouteSpec routeSpec) {
		List<RouteMatcher<?>> routeMatcherItemList = new ArrayList<RouteMatcher<?>>();
		
		ExactRouteMatcher exactRouteMatcher = new ExactRouteMatcher(routeSpec.getTargetPath());
		routeMatcherItemList.add(exactRouteMatcher);
		
		RouteMatchers routeMatchers = new RouteMatchers(routeMatcherItemList);
		return routeMatchers;
	}

}
