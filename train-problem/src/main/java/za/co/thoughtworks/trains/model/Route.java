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

	private List<Track> trackList;
	private Distance totalDistance;

	protected Route(){}
	
	public Route(Track track) {
		this.trackList = new ArrayList<Track>();
		this.totalDistance = Distance.valueOf(0);
		addTrack(track);
	}

	private void addTrack(Track track) {
		this.trackList.add(track);
		this.totalDistance = this.totalDistance.add(track.getDistance());
	}

	public Distance getTotalDistance() {
		return this.totalDistance;
	}

}
