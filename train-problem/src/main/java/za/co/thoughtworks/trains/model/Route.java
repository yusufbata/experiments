/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.infrastructure.utils.Cloneable;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.route.matchers.RouteMatchers;

/**
 * @author Yusuf
 *
 */
public class Route implements IRoute {
	
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

	public boolean isValid() {
		return this.routeMatchers.isRouteValid(completedLocationList);
	}
	
	public boolean isComplete() {
		return this.routeMatchers.isRouteComplete(this.trackList);
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
		sb.append("[Route distance=").append(this.totalDistance)
				.append(" trackList = ").append(this.trackList)
				.append("]");
		return sb.toString();
	}
}
