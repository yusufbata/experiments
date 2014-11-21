package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Track;

public class MaximumStopsRouteMatcher implements RouteMatcher<MaximumStopsRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<MaximumStopsRouteMatcher> {

	private final List<String> targetPath;
	private final int maximumStops;

	public MaximumStopsRouteMatcher(List<String> targetPath, int maximumStops) {
		this.targetPath = targetPath;
		this.maximumStops = maximumStops;
	}

	@Override
	public boolean isRouteValid(List<Location> completedLocationList) {
		return currentNumberOfStops(completedLocationList) <= maximumStops;
	}

	private int currentNumberOfStops(List<Location> completedLocationList) {
		return completedLocationList.size() - 1;
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
