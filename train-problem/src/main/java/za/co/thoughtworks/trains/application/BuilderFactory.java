package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.model.RailroadTracksBuilder;

public class BuilderFactory {

	public static RouteSpecBuilder aRouteSpec() {
		return new RouteSpecBuilder();
	}

	public static TrackDescriptorBuilder aTrack() {
		return new TrackDescriptorBuilder();
	}

	public static RailroadTracksBuilder railRoadTracks() {
		return new RailroadTracksBuilder();
	}

}
