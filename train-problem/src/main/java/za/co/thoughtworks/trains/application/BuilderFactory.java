package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.model.RailroadTracksBuilder;
import za.co.thoughtworks.trains.model.TrackBuilder;

public class BuilderFactory {

	public static RouteSpecBuilder aRouteSpec() {
		return new RouteSpecBuilder();
	}

	public static TrackBuilder aTrack() {
		return new TrackBuilder();
	}

	public static RailroadTracksBuilder railRoadTracks() {
		return new RailroadTracksBuilder();
	}

}
