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
	public boolean isRouteValid(List<Location> completedLocationList) {
		return currentNumberOfStops(completedLocationList) <= exactNumberOfStops;
	}

	@Override
	public boolean isRouteComplete(List<Track> trackList) {
		// TODO: Perhaps add isValid check here as well
		if (currentNumberOfStopsUsingTracks(trackList) == exactNumberOfStops) {
			String lastLocationId = ListUtils.getLastItemFromList(targetPath);
			if (trackList.size() > 0) {
				Track lastTrack = ListUtils.getLastItemFromList(trackList);
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
