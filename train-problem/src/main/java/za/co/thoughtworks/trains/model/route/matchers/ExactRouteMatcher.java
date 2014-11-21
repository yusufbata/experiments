package za.co.thoughtworks.trains.model.route.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Track;

public class ExactRouteMatcher implements RouteMatcher<ExactRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ExactRouteMatcher> {

	private final List<String> targetPath;

	public ExactRouteMatcher(List<String> targetPath) {
		this.targetPath = targetPath;
	}

	@Override
	public boolean isRouteValid(List<Location> completedLocationList) {
		// TODO Auto-generated method stub
		return completedLocationListMatchesTargetPath(completedLocationList);
	}

	@Override
	public boolean isRouteComplete(List<Track> trackList) {
		// TODO: Perhaps add isValid check here as well
		String lastLocationId = targetPath.get(targetPath.size() - 1);
		if (trackList.size() > 0
				&& this.targetPath.size() == (trackList.size() + 1)
				) {
			Track lastTrack = getLastItemFromList(trackList);
			return lastTrack.endLocationHasId(lastLocationId);
		}
		return false;
	}

	@Override
	public ExactRouteMatcher clone() {		
		return new ExactRouteMatcher(new ArrayList<String>(this.targetPath));
	}
	
	public static <T> T getLastItemFromList(List<T> list) {
		return list.get(list.size() - 1);
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
