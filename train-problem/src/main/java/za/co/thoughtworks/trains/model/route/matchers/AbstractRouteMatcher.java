package za.co.thoughtworks.trains.model.route.matchers;

import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.Cloneable;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Track;

public class AbstractRouteMatcher {

	protected final List<String> targetPath;

	public AbstractRouteMatcher(List<String> targetPath) {
		this.targetPath = targetPath;
	}

	protected int currentNumberOfStops(List<Location> completedLocationList) {
		return completedLocationList.size() - 1;
	}
	
	protected int currentNumberOfStopsUsingTracks(List<Track> trackList) {
		return trackList.size();
	}

}