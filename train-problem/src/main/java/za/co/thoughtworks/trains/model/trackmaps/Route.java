/**
 * 
 */
package za.co.thoughtworks.trains.model.trackmaps;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.path.Path;
import za.co.thoughtworks.trains.model.path.matchers.PathMatcherInput;
import za.co.thoughtworks.trains.model.path.matchers.PathMatchers;

/**
 * A specific route from a start location via a list of tracks.
 * An implementation of a directed graph.
 * 
 * @author Yusuf
 *
 */
public class Route implements Path {
	
	private final Distance totalDistance;
	private final List<Track> trackList;
	private final List<Location> completedLocationList;
	private final PathMatchers pathMatchers;

	public Route(PathMatchers pathMatchers, Location startLocation) {
		this(pathMatchers, startLocation, new ArrayList<Track>());
	}
	
	public Route(PathMatchers pathMatchers, Location startLocation, List<Track> trackList) {
		if (pathMatchers == null) throw new IllegalArgumentException("pathMatchers cannot be null");
		if (startLocation == null) throw new IllegalArgumentException("startLocation cannot be null");
		if (trackList == null) throw new IllegalArgumentException("Route cannot be initialised with null track list");
		
		this.pathMatchers = pathMatchers;
		this.completedLocationList = new ArrayList<Location>();
		this.completedLocationList.add(startLocation);
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

	/**
	 * Constructs a new Route with the additional track.
	 * 
	 * @param track
	 * @return
	 */
	private Route addTrack(Track track)  {
		List<Track> newTrackList = new ArrayList<Track>(this.trackList);
		newTrackList.add(track);
		Route newRoute = new Route(this.pathMatchers.clone(), this.getStartLocation(), newTrackList);
		return newRoute;
	}

	@Override
	public Distance getTotalDistance() {
		return this.totalDistance;
	}

	public boolean isValid(List<Path> allCompletedRoutes) {
		return this.pathMatchers.isRouteValid(PathMatcherInput.construct(this, allCompletedRoutes));
	}
	
	public boolean isComplete(List<Path> allCompletedRoutes) {
		return this.pathMatchers.isRouteComplete(PathMatcherInput.construct(this, allCompletedRoutes));
	}

	private Location getStartLocation() {
		return this.completedLocationList.get(0);
	}

	@Override
	public List<Path> findNextPossiblePaths() {
		Location currentLocation = getCurrentLocation();
		
		List<Path> nextPossibleRoutes = new ArrayList<Path>();
		List<Track> outgoingTracks = currentLocation.getOutgoingTracks();
		for (Track track : outgoingTracks) {
			nextPossibleRoutes.add(addTrack(track));
		}

		return nextPossibleRoutes;
	}

	private Location getCurrentLocation() {
		Location currentLocation = completedLocationList
				.get(completedLocationList.size() - 1);
		return currentLocation;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Route ")
				.append(this.createRoutePath()).append(" ")
				.append(this.totalDistance)
				.append("]");
		return sb.toString();
	}

	private String createRoutePath() {
		if (this.trackList.isEmpty()) {
			return "No Tracks";
		}
		StringBuilder sb = new StringBuilder(this.trackList.get(0).getFromLocationId());
		for (Track track : this.trackList) {
			sb.append(track.getToLocationId());
		}
		return sb.toString();
	}

	@Override
	public boolean hasPath(String routePath) {
		return this.createRoutePath().compareTo(routePath) == 0;
	}
	
	public int getCurrentNumberOfStopsUsingTracks() {
		return trackList.size();
	}

	@Override
	public int getCurrentNumberOfStops() {
		return completedLocationList.size() - 1;
	}

	@Override
	public boolean hasEndLocationId(String lastLocationId) {
		Track lastTrack = ListUtils.getLastItemFromList(this.trackList);
		return lastTrack.endLocationHasId(lastLocationId);
	}

	@Override
	public boolean completedLocationListMatchesTargetPath(List<String> targetPath) {
		int completedLocationCount = completedLocationList.size();
		for (int i = 0; i < completedLocationCount; i++) {
			Location completedLocation = completedLocationList.get(i);
			String targetPathItem = targetPath.get(i);
			if (!completedLocation.hasId(targetPathItem)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getPrintString() {
		return this.getTotalDistance().value() + "";
	}

}
