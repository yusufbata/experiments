/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;

/**
 * @author Yusuf
 *
 */
public class Route {

	public static final Route NO_ROUTE_EXISTS = null;

	private List<Track> trackList;
	private Distance totalDistance;
	private List<String> toTownList;
	private List<Location> completedLocationList;

	protected Route() {
	}

	public Route(List<Track> trackList, List<String> toTownList) {
		this.toTownList = toTownList;
		this.completedLocationList = new ArrayList<Location>();
		
		this.totalDistance = Distance.valueOf(0);
//		this.trackList = new ArrayList<Track>();
		this.trackList = trackList;
		
		initialise();
	}

	private void initialise() {
		for (Track currentTrack : this.trackList) {
			this.totalDistance = this.totalDistance.add(currentTrack.getDistance());
			this.completedLocationList.add(currentTrack.getToLocation());
		}
	}

	private Route addTrack(Track track)  {
		Route newRoute = cloneRoute();
		newRoute.trackList.add(track);
		return newRoute;
	}

	private List<Track> cloneListWithContents(List<Track> aListToClone) {
		List<Track> clone = new ArrayList<Track>(aListToClone.size());
		for (Track track : aListToClone) {
			clone.add((Track)track.clone());
		}
		return clone;
	}

	private Route cloneRoute() {
		try {
			Route newRoute = (Route) this.clone();
			return newRoute;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Failed to clone Route. Take this Exception out!!!", e);
		}
	}

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
			String targetPathItem = this.toTownList.get(0);
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
		Track lastTrack = getLastTrack();
		return lastTrack.endLocationHasId(lastLocationId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Route trackList = ").append(this.trackList).append("]");
		return sb.toString();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		List<Track> newTrackList = cloneListWithContents(this.trackList);
		Route newRoute = new Route(newTrackList, this.toTownList);
		newRoute.completedLocationList = this.completedLocationList;
		return newRoute;
	}

	public List<Route> findNextPossibleRoutes() {
		List<Route> nextPossibleRoutes = new ArrayList<Route>();
		// nextPossibleRoutes.add(this); // hack

		Location currentLocation = completedLocationList
				.get(completedLocationList.size() - 1);
		List<Track> outgoingTracks = currentLocation.getOutgoingTracks();
		for (Track track : outgoingTracks) {
			Route newPotentialRoute = this.addTrack(track);
			nextPossibleRoutes.add(newPotentialRoute);
		}

		return nextPossibleRoutes;
	}
}
