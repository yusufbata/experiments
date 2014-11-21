package za.co.thoughtworks.trains.model.route.matchers;

import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Location;

public class RouteMatchers implements RouteMatcher<RouteMatchers>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<RouteMatchers> {

	private final List<RouteMatcher<?>> routeMatcherList;

	public RouteMatchers(List<RouteMatcher<?>> routeMatcherList) {
		/*if (this.routeMatcherList == null || this.routeMatcherList.size() == 0) {
			throw new IllegalArgumentException("At least one route matcher has to be present");
		}*/
		this.routeMatcherList = routeMatcherList;
	}

	@Override
	public boolean isRouteValid(RouteMatcherInput routeMatcherInput) {
		for (RouteMatcher<?> routeMatcher : routeMatcherList) {
			if (!routeMatcher.isRouteValid(routeMatcherInput)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isRouteComplete(RouteMatcherInput routeMatcherInput) {
		for (RouteMatcher<?> routeMatcher : routeMatcherList) {
			if (!routeMatcher.isRouteComplete(routeMatcherInput)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public RouteMatchers clone() {
		List<RouteMatcher<?>> routeMatcherListClone = ListUtils.cloneListWithContents(this.routeMatcherList);
		return new RouteMatchers(routeMatcherListClone);
	}
}
