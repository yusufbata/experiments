package za.co.thoughtworks.trains.model;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aRouteSpec;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.railRoadTracks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import za.co.thoughtworks.trains.application.ApplicationRegistry;
import za.co.thoughtworks.trains.application.LocationRepository;
import za.co.thoughtworks.trains.application.RouteSpec;
import za.co.thoughtworks.trains.application.RouteSpecBuilder;
import za.co.thoughtworks.trains.application.TrackDescriptor;

public class RouteTest {

	private Route route;
	
	@Test
	public void testFindNextPossibleRoutesForSingleTrack() {
		havingConfigured(
				railRoadTracks()
					.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)),
				aRouteSpec().fromTown("A").toTown("B").toTown("C")
		);
		
		List<Route> nextPossibleRoutes = route.findNextPossibleRoutes();
		
		assertThat(nextPossibleRoutes).isNotNull().isNotEmpty().hasSize(1);
//		assertThat(nextPossibleRoutes.get(0)).isEqualTo(route.);
	}
	
	@Test
	public void testFindNextPossibleRoutesForDoubleTracks() {
		havingConfigured(
				railRoadTracks()
					.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
					.with(aTrack().fromTown("A").toTown("C").withADistanceOf(6)),
				aRouteSpec().fromTown("A").toTown("B").toTown("C")
		);
		
		List<Route> nextPossibleRoutes = route.findNextPossibleRoutes();
		
		assertThat(nextPossibleRoutes).isNotNull().isNotEmpty().hasSize(2);
//		assertThat(nextPossibleRoutes.get(0)).isEqualTo(route.);
	}

	private void havingConfigured(RailroadTracksBuilder railroadTracksBuilder,
			RouteSpecBuilder routeSpecBuilder) {
		LocationFactory locationFactory = new LocationFactory();
		List<Location> locationsList = locationFactory.constructLocationsFrom(railroadTracksBuilder.build());
		LocationRepository locationRepository = ApplicationRegistry.getLocationRepository(locationsList);
		RouteSpec routeSpec = routeSpecBuilder.build();
		Location startLocation = locationRepository.findLocationWithId(routeSpec.getStartLocationId());
		
		this.route = new Route(startLocation, routeSpec.getToTownList());
	}

}
