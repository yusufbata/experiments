package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Track;

public class MaximumStopsRouteMatcher  extends AbstractRouteMatcher  
	implements RouteMatcher<MaximumStopsRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<MaximumStopsRouteMatcher> {

	private final int maximumStops;

	public MaximumStopsRouteMatcher(List<String> targetPath, int maximumStops) {
		super(targetPath);
		this.maximumStops = maximumStops;
	}

	@Override
	public boolean isRouteValid(RouteMatcherInput routeMatcherInput) {
		return currentNumberOfStops(routeMatcherInput.getCompletedLocationList()) <= maximumStops;
	}

	@Override
	public boolean isRouteComplete(RouteMatcherInput routeMatcherInput) {
		// TODO: Perhaps add isValid check here as well
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (routeMatcherInput.getTrackList().size() > 0) {
			Track lastTrack = ListUtils.getLastItemFromList(routeMatcherInput.getTrackList());
			return lastTrack.endLocationHasId(lastLocationId);
		}
		return false;
	}

	@Override
	public MaximumStopsRouteMatcher clone() {		
		return new MaximumStopsRouteMatcher(new ArrayList<String>(this.targetPath), this.maximumStops);
	}
}
