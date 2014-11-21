package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Track;

public class ExactNumberOfStopsRouteMatcher extends AbstractRouteMatcher 
	implements RouteMatcher<ExactNumberOfStopsRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ExactNumberOfStopsRouteMatcher> {

	private final int exactNumberOfStops;

	public ExactNumberOfStopsRouteMatcher(List<String> targetPath, int exactNumberOfStops) {
		super(targetPath);
		this.exactNumberOfStops = exactNumberOfStops;
	}

	@Override
	public boolean isRouteValid(RouteMatcherInput routeMatcherInput) {
		return currentNumberOfStops(routeMatcherInput.getCompletedLocationList()) <= exactNumberOfStops;
	}

	@Override
	public boolean isRouteComplete(RouteMatcherInput routeMatcherInput) {
		// TODO: Perhaps add isValid check here as well
		if (currentNumberOfStopsUsingTracks(routeMatcherInput.getTrackList()) == exactNumberOfStops) {
			String lastLocationId = ListUtils.getLastItemFromList(targetPath);
			if (routeMatcherInput.getTrackList().size() > 0) {
				Track lastTrack = ListUtils.getLastItemFromList(routeMatcherInput.getTrackList());
				return lastTrack.endLocationHasId(lastLocationId);
			}
		}
		
		return false;
	}

	@Override
	public ExactNumberOfStopsRouteMatcher clone() {		
		return new ExactNumberOfStopsRouteMatcher(new ArrayList<String>(this.targetPath), this.exactNumberOfStops);
	}
}
