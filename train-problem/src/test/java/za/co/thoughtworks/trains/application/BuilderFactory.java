package za.co.thoughtworks.trains.application;

import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrackList;


public class BuilderFactory {

	public static RouteSpecBuilder aRouteSpec() {
		return new RouteSpecBuilder();
	}

	public static TrackDescriptorBuilder aTrack() {
		return new TrackDescriptorBuilder();
	}

	public static TrackDescriptorListBuilder aTrackList() {
		return new TrackDescriptorListBuilder();
	}
}
