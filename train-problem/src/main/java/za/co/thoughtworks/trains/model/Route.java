/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.application.TrackDescriptor;

/**
 * @author Yusuf
 *
 */
public class Route {

	public static final Route NO_ROUTE_EXISTS = null;

	private List<TrackDescriptor> trackList;
	private Distance totalDistance;
	private Location targetEndLocation;
	private List<String> toTownList;
	private List<Location> completedLocationList;

	protected Route() {
	}

	public Route(List<TrackDescriptor> trackList, Location targetEndLocation,
			List<String> toTownList) {
		this.targetEndLocation = targetEndLocation;
		this.toTownList = toTownList;
		this.completedLocationList = new ArrayList<Location>();
		
		this.totalDistance = Distance.valueOf(0);
//		this.trackList = new ArrayList<TrackDescriptor>();
		this.trackList = trackList;
		
		initialise();
	}

	private void initialise() {
		for (TrackDescriptor currentTrack : this.trackList) {
			this.totalDistance = this.totalDistance.add(currentTrack.getDistance());
			this.completedLocationList.add(currentTrack.getToLocation());
		}
	}

	private Route addTrack(TrackDescriptor trackDescriptor)  {
		Route newRoute = cloneRoute();
		newRoute.trackList.add(trackDescriptor);
		return newRoute;
	}

	private List<TrackDescriptor> cloneListWithContents(List<TrackDescriptor> aListToClone) {
		List<TrackDescriptor> clone = new ArrayList<TrackDescriptor>(aListToClone.size());
		for (TrackDescriptor trackDescriptor : aListToClone) {
			clone.add((TrackDescriptor)trackDescriptor.clone());
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
		// TrackDescriptor lastTrack = getLastTrack();
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

	private TrackDescriptor getLastTrack() {
		return this.trackList.get(this.trackList.size() - 1);
	}

	public boolean isComplete() {
		TrackDescriptor lastTrack = getLastTrack();
		return lastTrack.endLocationEquals(targetEndLocation);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Route trackList = ").append(this.trackList).append("]");
		return sb.toString();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		List<TrackDescriptor> newTrackList = cloneListWithContents(this.trackList);
		Route newRoute = new Route(newTrackList, this.targetEndLocation, this.toTownList);
		newRoute.completedLocationList = this.completedLocationList;
		return newRoute;
	}

	public List<Route> findNextPossibleRoutes() {
		List<Route> nextPossibleRoutes = new ArrayList<Route>();
		// nextPossibleRoutes.add(this); // hack

		Location currentLocation = completedLocationList
				.get(completedLocationList.size() - 1);
		List<TrackDescriptor> outgoingTracks = currentLocation.getOutgoingTracks();
		for (TrackDescriptor trackDescriptor : outgoingTracks) {
			Route newPotentialRoute = this.addTrack(trackDescriptor);
			nextPossibleRoutes.add(newPotentialRoute);
		}

		return nextPossibleRoutes;
	}
}
