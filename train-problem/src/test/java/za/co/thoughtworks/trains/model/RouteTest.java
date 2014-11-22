package za.co.thoughtworks.trains.model;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aRouteSpec;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrackList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import za.co.thoughtworks.trains.application.ApplicationRegistry;
import za.co.thoughtworks.trains.application.LocationRepository;
import za.co.thoughtworks.trains.application.RouteSpecBuilder;
import za.co.thoughtworks.trains.application.TrackDescriptorListBuilder;
import za.co.thoughtworks.trains.application.services.Path;
import za.co.thoughtworks.trains.application.services.RouteSpec;
import za.co.thoughtworks.trains.application.services.TrackDescriptor;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchers;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchersFactory;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.LocationFactory;

public class RouteTest {

	private Path route;
	
	@Test
	public void testFindNextPossibleRoutesForSingleTrack() {
		havingConfigured(
				aTrackList()
					.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)),
				aRouteSpec().fromTown("A").toTown("B").toTown("C")
		);
		
		List<Path> nextPossibleRoutes = route.findNextPossibleRoutes();
		
		assertThat(nextPossibleRoutes).isNotNull().isNotEmpty().hasSize(1);
//		assertThat(nextPossibleRoutes.get(0)).isEqualTo(route.);
	}
	
	@Test
	public void testFindNextPossibleRoutesForDoubleTracks() {
		havingConfigured(
				aTrackList()
					.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
					.with(aTrack().fromTown("A").toTown("C").withADistanceOf(6)),
				aRouteSpec().fromTown("A").toTown("B").toTown("C")
		);
		
		List<Path> nextPossibleRoutes = route.findNextPossibleRoutes();
		
		assertThat(nextPossibleRoutes).isNotNull().isNotEmpty().hasSize(2);
//		assertThat(nextPossibleRoutes.get(0)).isEqualTo(route.);
	}

	private void havingConfigured(TrackDescriptorListBuilder trackDescriptorListBuilder,
			RouteSpecBuilder routeSpecBuilder) {
		LocationFactory locationFactory = new LocationFactory();
		List<Location> locationsList = locationFactory.constructLocationsFrom(trackDescriptorListBuilder.build());
		LocationRepository locationRepository = ApplicationRegistry.getLocationRepository(locationsList);
		RouteSpec routeSpec = routeSpecBuilder.build();
		Location startLocation = locationRepository.findLocationWithId(routeSpec.getStartLocationId());
		RouteMatchers routeMatchers = RouteMatchersFactory.constructRouteMatchers(routeSpec);
		
		this.route = new Route(routeMatchers, startLocation);
	}

}
