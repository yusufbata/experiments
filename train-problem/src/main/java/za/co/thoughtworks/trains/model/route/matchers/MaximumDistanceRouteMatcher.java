package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.Track;

public class MaximumDistanceRouteMatcher  extends AbstractRouteMatcher  
	implements RouteMatcher<MaximumDistanceRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<MaximumDistanceRouteMatcher> {

	private final int maximumDistance;

	public MaximumDistanceRouteMatcher(List<String> targetPath, int maximumDistance) {
		super(targetPath);
		this.maximumDistance = maximumDistance;
	}

	@Override
	public boolean isRouteValid(Route route, List<Location> completedLocationList) {
		return route.getTotalDistance().value() <= maximumDistance;
	}

	@Override
	public boolean isRouteComplete(Route route, List<Track> trackList) {
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (trackList.size() > 0) {
			Track lastTrack = ListUtils.getLastItemFromList(trackList);
			return lastTrack.endLocationHasId(lastLocationId);
		}
		return false;
	}

	@Override
	public MaximumDistanceRouteMatcher clone() {		
		return new MaximumDistanceRouteMatcher(new ArrayList<String>(this.targetPath), this.maximumDistance);
	}
}
