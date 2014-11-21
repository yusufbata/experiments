package za.co.thoughtworks.trains.model.route.matchers;

import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.Track;

public class RouteMatchers implements RouteMatcher<RouteMatchers>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<RouteMatchers> {

	private final List<RouteMatcher<?>> routeMatcherList;

	public RouteMatchers(List<RouteMatcher<?>> routeMatcherList) {
		/*if (this.routeMatcherList == null || this.routeMatcherList.size() == 0) {
			throw new IllegalArgumentException("At least one route matcher has to be present");
		}*/
		this.routeMatcherList = routeMatcherList;
	}

	@Override
	public boolean isRouteValid(Route route, List<Location> completedLocationList) {
		for (RouteMatcher<?> routeMatcher : routeMatcherList) {
			if (!routeMatcher.isRouteValid(route, completedLocationList)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isRouteComplete(Route route, List<Track> trackList) {
		for (RouteMatcher<?> routeMatcher : routeMatcherList) {
			if (!routeMatcher.isRouteComplete(route, trackList)) {
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
