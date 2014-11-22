/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatcherInput;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchers;

/**
 * @author Yusuf
 *
 */
class Route implements IRoute, Path {
	
	private final Distance totalDistance;
	private final List<Track> trackList;
	private final List<Location> completedLocationList;
	private final RouteMatchers routeMatchers;

	public Route(RouteMatchers routeMatchers, Location startLocation) {
		this(routeMatchers, startLocation, new ArrayList<Track>());
	}
	
	public Route(RouteMatchers routeMatchers, Location startLocation, List<Track> trackList) {
		if (routeMatchers == null) throw new IllegalArgumentException("routeMatchers cannot be null");
		if (startLocation == null) throw new IllegalArgumentException("startLocation cannot be null");
		if (trackList == null) throw new IllegalArgumentException("Route cannot be initialised with null track list");
		
		this.routeMatchers = routeMatchers;
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

	private Route addTrack(Track track)  {
		List<Track> newTrackList = new ArrayList<Track>(this.trackList);
		newTrackList.add(track);
		Route newRoute = new Route(this.routeMatchers.clone(), this.getStartLocation(), newTrackList);
		return newRoute;
	}

	@Override
	public Distance getTotalDistance() {
		return this.totalDistance;
	}

	public boolean isValid(List<Path> allCompletedRoutes) {
		return this.routeMatchers.isRouteValid(RouteMatcherInput.construct(this, allCompletedRoutes));
	}
	
	public boolean isComplete(List<Path> allCompletedRoutes) {
		return this.routeMatchers.isRouteComplete(RouteMatcherInput.construct(this, allCompletedRoutes));
	}

	/*private boolean hasRepeatingLocation() {
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
	}*/
	
	/*@Override
	public Route clone() {
		Route newRoute = new Route(this.getStartLocation().clone(), this.toTownList, ListUtils.cloneListWithContents(this.trackList));
		return newRoute;
	}*/

	private Location getStartLocation() {
		return this.completedLocationList.get(0);
	}

	/* (non-Javadoc)
	 * @see za.co.thoughtworks.trains.model.Path#findNextPossibleRoutes()
	 */
	@Override
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
		if (this.trackList.size() == 0) {
			return "No Tracks";
		}
		StringBuilder sb = new StringBuilder(this.trackList.get(0).getFromLocationId());
		for (Track track : this.trackList) {
			sb.append(track.getToLocationId());
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see za.co.thoughtworks.trains.model.Path#hasPath(java.lang.String)
	 */
	@Override
	public boolean hasPath(String routePath) {
		return this.createRoutePath().compareTo(routePath) == 0;
	}
	
	public int getCurrentNumberOfStopsUsingTracks() {
		return trackList.size();
	}
	
	/* (non-Javadoc)
	 * @see za.co.thoughtworks.trains.model.Path#getCurrentNumberOfStops()
	 */
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

}
