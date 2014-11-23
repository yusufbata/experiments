package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.application.services.RouteSpecBuilder;
import za.co.thoughtworks.trains.application.services.TrackDescriptorBuilder;
import za.co.thoughtworks.trains.application.services.TrackDescriptorListBuilder;



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
