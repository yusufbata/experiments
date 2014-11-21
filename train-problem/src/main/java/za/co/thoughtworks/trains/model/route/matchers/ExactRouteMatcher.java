package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.Track;

public class ExactRouteMatcher  extends AbstractRouteMatcher 
	implements RouteMatcher<ExactRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ExactRouteMatcher> {

	public ExactRouteMatcher(List<String> targetPath) {
		super(targetPath);
	}

	@Override
	public boolean isRouteValid(Route route, List<Location> completedLocationList) {
		if(completedLocationList.size() <= targetPath.size()
				&& completedLocationListMatchesTargetPath(completedLocationList))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isRouteComplete(Route route, List<Track> trackList) {
		// TODO: Perhaps add isValid check here as well
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (trackList.size() > 0
				&& numberOfTracksIsSameAsTargetPath(trackList) ) {
			Track lastTrack = ListUtils.getLastItemFromList(trackList);
			return lastTrack.endLocationHasId(lastLocationId);
		}
		return false;
	}

	private boolean numberOfTracksIsSameAsTargetPath(List<Track> trackList) {
		return this.targetPath.size() == (trackList.size() + 1);
	}

	@Override
	public ExactRouteMatcher clone() {		
		return new ExactRouteMatcher(new ArrayList<String>(this.targetPath));
	}
	
	private boolean completedLocationListMatchesTargetPath(List<Location> completedLocationList) {
		int completedLocationCount = completedLocationList.size();
		for (int i = 0; i < completedLocationCount; i++) {
			Location completedLocation = completedLocationList.get(i);
			String targetPathItem = this.targetPath.get(i);
			if (!completedLocation.hasId(targetPathItem)) {
				return false;
			}
		}
		return true;
	}
}
