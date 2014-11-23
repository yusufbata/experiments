package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.application.services.RouteSpecBuilder;
import za.co.thoughtworks.trains.application.services.TrackDescriptorBuilder;
import za.co.thoughtworks.trains.application.services.TrackDescriptorListBuilder;


/**
 * Util to add syntactic sugar to creation of builders. Import static used to achieve this.
 * 
 * @author Yusuf
 *
 */
public class BuilderFactory {

	private BuilderFactory(){}
	
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
