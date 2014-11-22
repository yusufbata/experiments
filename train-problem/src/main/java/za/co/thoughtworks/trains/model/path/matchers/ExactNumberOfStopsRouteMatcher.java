package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;

public class ExactNumberOfStopsRouteMatcher extends AbstractRouteMatcher 
	implements RouteMatcher<ExactNumberOfStopsRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ExactNumberOfStopsRouteMatcher> {

	private final int exactNumberOfStops;

	public ExactNumberOfStopsRouteMatcher(List<String> targetPath, int exactNumberOfStops) {
		super(targetPath);
		this.exactNumberOfStops = exactNumberOfStops;
	}

	@Override
	public boolean isRouteValid(RouteMatcherInput routeMatcherInput) {
		return routeMatcherInput.getRoute().getCurrentNumberOfStops() <= exactNumberOfStops;
	}

	@Override
	public boolean isRouteComplete(RouteMatcherInput routeMatcherInput) {
		// TODO: Perhaps add isValid check here as well
		if (routeMatcherInput.getRoute().getCurrentNumberOfStops() == exactNumberOfStops) {
			String lastLocationId = ListUtils.getLastItemFromList(targetPath);
			if (routeMatcherInput.getRoute().getCurrentNumberOfStops() > 0) {
				return routeMatcherInput.getRoute().hasEndLocationId(lastLocationId);
			}
		}
		
		return false;
	}

	@Override
	public ExactNumberOfStopsRouteMatcher clone() {		
		return new ExactNumberOfStopsRouteMatcher(new ArrayList<String>(this.targetPath), this.exactNumberOfStops);
	}
}
