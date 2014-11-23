/**
 * 
 */
package za.co.thoughtworks.trains.application;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.model.trackmaps.BuilderFactory.aRouteSpec;
import static za.co.thoughtworks.trains.model.trackmaps.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.model.trackmaps.BuilderFactory.aTrackList;

import org.junit.Test;

import za.co.thoughtworks.trains.application.services.RailroadApplicationService;
import za.co.thoughtworks.trains.model.path.MatchingPaths;
import za.co.thoughtworks.trains.model.path.NoPathFound;
import za.co.thoughtworks.trains.model.trackmaps.TrackDescriptorList;
import za.co.thoughtworks.trains.model.trackmaps.TrackDescriptorListBuilder;
import za.co.thoughtworks.trains.test.TestUtils;

/**
 * @author Yusuf
 *
 */
public class RailroadServiceShortestDistanceMatchTests {

	private RailroadApplicationService railroadService;
	
	@Test
	public void returnsOneRouteForShortestDistanceMatcherWithOnePossibility() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
		);
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withShortestDistance()
				.build());
		
		TestUtils.containsASingleValidRoute(matchingPaths);
		assertThat(matchingPaths.findTheOnlyPath().getTotalDistance()).isEqualTo(Distance.valueOf(15));
	}
	
	@Test
	public void returnsOneRouteForShortestDistanceMatcherWithTwoPossibilities() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("A").toTown("C").withADistanceOf(30))
		);
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withShortestDistance()
				.build());
		
		// System.out.println(matchingPaths);
		TestUtils.containsASingleValidRoute(matchingPaths);
		assertThat(matchingPaths.findRouteWithPath("ABC").getTotalDistance()).isEqualTo(Distance.valueOf(15));
	}
	
	@Test
	public void returnsSingleCyclicRouteUsingMaximumStops() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("C").toTown("A").withADistanceOf(30))
		);
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("A").withShortestDistance()
				.build());
		
		// System.out.println(matchingPaths);
		TestUtils.containsASingleValidRoute(matchingPaths);
		assertThat(matchingPaths.findRouteWithPath("ABCA").getTotalDistance()).isEqualTo(Distance.valueOf(45));
	}
	
	@Test
	public void verifyProblemSampleDistanceCalculations() {
		// AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		havingConfigured(TestUtils.buildSampleProblemTracks());
		
		/*
			8. The length of the shortest route (in terms of distance to travel) from A to C.
			9. The length of the shortest route (in terms of distance to travel) from B to B.
		 */
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withShortestDistance().build());
		
		// System.out.println(matchingPaths);
		TestUtils.containsASingleValidRoute(matchingPaths);
		assertThat(matchingPaths.findTheOnlyPath().getTotalDistance()).isEqualTo(Distance.valueOf(9));
		
		matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("B").toTown("B").withShortestDistance().build());
		
		// System.out.println(matchingPaths);
		TestUtils.containsASingleValidRoute(matchingPaths);
		assertThat(matchingPaths.findTheOnlyPath().getTotalDistance()).isEqualTo(Distance.valueOf(9));
	}
	
	private void havingConfigured(TrackDescriptorListBuilder aTrackListBuilder) {
		TrackDescriptorList trackDescriptorList = aTrackListBuilder.build();
		this.railroadService = new RailroadApplicationService(trackDescriptorList);
	}
}
