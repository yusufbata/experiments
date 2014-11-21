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
	public boolean isRouteValid(List<Location> completedLocationList) {
		return currentNumberOfStops(completedLocationList) <= maximumStops;
	}

	@Override
	public boolean isRouteComplete(List<Track> trackList) {
		// TODO: Perhaps add isValid check here as well
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (trackList.size() > 0) {
			Track lastTrack = ListUtils.getLastItemFromList(trackList);
			return lastTrack.endLocationHasId(lastLocationId);
		}
		return false;
	}

	@Override
	public MaximumStopsRouteMatcher clone() {		
		return new MaximumStopsRouteMatcher(new ArrayList<String>(this.targetPath), this.maximumStops);
	}
}
