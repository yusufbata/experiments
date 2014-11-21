/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.infrastructure.utils.Cloneable;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;

/**
 * @author Yusuf
 *
 */
public class Route /*implements Cloneable<Route>*/ implements IRoute {

	public static final IRoute NO_ROUTE_EXISTS = null;

	private final List<Track> trackList;
	private final Distance totalDistance;
	private final List<String> toTownList;
	private final List<Location> completedLocationList;

	public Route(Location startLocation, List<String> toTownList) {
		this(startLocation, toTownList, new ArrayList<Track>());
	}
	
	public Route(Location startLocation, List<String> toTownList, List<Track> trackList) {
		if (startLocation == null) throw new IllegalArgumentException("startLocation cannot be null");
		if (toTownList == null) throw new IllegalArgumentException("toTownList cannot be null");
		if (trackList == null) throw new IllegalArgumentException("Route cannot be initialised with null track list");
		
		this.completedLocationList = new ArrayList<Location>();
		this.completedLocationList.add(startLocation);
		this.toTownList = toTownList;
		this.trackList = trackList;
		this.totalDistance = computeTotalDistanceAcrossTracks();
	}

	private Distance computeTotalDistanceAcrossTracks() {
		Distance totalDistanceRunningTotal = Distance.valueOf(0);
		for (Track currentTrack : this.trackList) {
			totalDistanceRunningTotal = totalDistanceRunningTotal.add(currentTrack.getDistance());
			this.completedLocationList.add(currentTrack.getToLocation());
		}
		return totalDistanceRunningTotal;
	}

	private Route addTrack(Track track)  {
		List<Track> newTrackList = new ArrayList<Track>(this.trackList);
		newTrackList.add(track);
		Route newRoute = new Route(this.getStartLocation(), this.toTownList, newTrackList);
		return newRoute;
	}

	@Override
	public Distance getTotalDistance() {
		return this.totalDistance;
	}

	public boolean isValid() {
		// Track lastTrack = getLastTrack();
		// return lastTrack.endLocationEquals(targetEndLocation);
		if (/* hasRepeatingLocation() && */completedLocationListMatchesTargetPath()) {
			return true;
		}
		return false;
	}

	private boolean hasRepeatingLocation() {
		for (int currentLocationIndex = 0; currentLocationIndex < this.completedLocationList
				.size(); currentLocationIndex++) {
			for (int currentListIndex = currentLocationIndex + 1; currentListIndex < this.completedLocationList
					.size() - currentListIndex; currentListIndex++) {
				Location currentLocation = this.completedLocationList
						.get(currentLocationIndex);
				Location otherLocation = this.completedLocationList
						.get(currentListIndex);
				if (currentLocation.equals(otherLocation)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean completedLocationListMatchesTargetPath() {
		int completedLocationCount = this.completedLocationList.size();
		for (int i = 0; i < completedLocationCount; i++) {
			Location completedLocation = this.completedLocationList.get(i);
			String targetPathItem = this.toTownList.get(i);
			if (!completedLocation.hasId(targetPathItem)) {
				return false;
			}
		}
		return true;
	}

	private Track getLastTrack() {
		return this.trackList.get(this.trackList.size() - 1);
	}

	public boolean isComplete() {
		String lastLocationId = toTownList.get(toTownList.size() - 1);
		if (this.trackList.size() > 0) {
			Track lastTrack = getLastTrack();
			return lastTrack.endLocationHasId(lastLocationId);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Route trackList = ").append(this.trackList).append("]");
		return sb.toString();
	}
	
	/*@Override
	public Route clone() {
		Route newRoute = new Route(this.getStartLocation().clone(), this.toTownList, ListUtils.cloneListWithContents(this.trackList));
		return newRoute;
	}*/

	private Location getStartLocation() {
		return this.completedLocationList.get(0);
	}

	public List<Route> findNextPossibleRoutes() {
		Location currentLocation = getCurrentLocation();
		
		List<Route> nextPossibleRoutes = new ArrayList<Route>();
		List<Track> outgoingTracks = currentLocation.getOutgoingTracks();
		for (Track track : outgoingTracks) {
			Route newPotentialRoute = this.addTrack(track);
			nextPossibleRoutes.add(newPotentialRoute);
		}

		return nextPossibleRoutes;
	}

	private Location getCurrentLocation() {
		Location currentLocation = completedLocationList
				.get(completedLocationList.size() - 1);
		return currentLocation;
	}
	
	/*List<Track> outgoingTracks = startLocation.getOutgoingTracks();
	for (Track track : outgoingTracks) {
		List<Track> trackList = new ArrayList<Track>();
		trackList.add(track);
		Route potentialRoute = new Route(trackList, toTownList);
		incompleteMatchingRoutes.add(potentialRoute);
	}*/
}
