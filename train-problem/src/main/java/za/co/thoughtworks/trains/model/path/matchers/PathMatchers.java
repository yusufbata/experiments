package za.co.thoughtworks.trains.model.path.matchers;

import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.trackmaps.Location;

public class PathMatchers implements PathMatcher<PathMatchers>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<PathMatchers> {

	private final List<PathMatcher<?>> routeMatcherList;

	public PathMatchers(List<PathMatcher<?>> routeMatcherList) {
		/*if (this.routeMatcherList == null || this.routeMatcherList.size() == 0) {
			throw new IllegalArgumentException("At least one route matcher has to be present");
		}*/
		this.routeMatcherList = routeMatcherList;
	}

	@Override
	public boolean isRouteValid(PathMatcherInput pathMatcherInput) {
		for (PathMatcher<?> routeMatcher : routeMatcherList) {
			if (!routeMatcher.isRouteValid(pathMatcherInput)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isRouteComplete(PathMatcherInput pathMatcherInput) {
		for (PathMatcher<?> routeMatcher : routeMatcherList) {
			if (!routeMatcher.isRouteComplete(pathMatcherInput)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public PathMatchers clone() {
		List<PathMatcher<?>> routeMatcherListClone = ListUtils.cloneListWithContents(this.routeMatcherList);
		return new PathMatchers(routeMatcherListClone);
	}
}
