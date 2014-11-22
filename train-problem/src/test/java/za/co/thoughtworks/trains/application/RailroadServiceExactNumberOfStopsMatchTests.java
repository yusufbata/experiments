/**
 * 
 */
package za.co.thoughtworks.trains.application;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aRouteSpec;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrackList;

import org.junit.Test;

import za.co.thoughtworks.trains.application.services.Distance;
import za.co.thoughtworks.trains.application.services.MatchingPaths;
import za.co.thoughtworks.trains.application.services.RailroadApplicationService;
import za.co.thoughtworks.trains.application.services.TrackDescriptorList;
import za.co.thoughtworks.trains.test.TestUtils;

/**
 * @author Yusuf
 *
 */
public class RailroadServiceExactNumberOfStopsMatchTests {

	private RailroadApplicationService railroadService;
	
	@Test
	public void returnsOneRouteForExactNumberOfStopsMatcher() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
		);
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withExactNumberOfStops(2)
				.build());
		
		// System.out.println(matchingPaths);
		TestUtils.containsASingleValidRoute(matchingPaths);
		assertThat(matchingPaths.findTheOnlyPath().getTotalDistance()).isEqualTo(Distance.valueOf(15));
	}

	@Test
	public void returnsTwoRouteForExactNumberOfStopsMatcher() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("C").toTown("A").withADistanceOf(30))
				.with(aTrack().fromTown("B").toTown("D").withADistanceOf(2))
				.with(aTrack().fromTown("D").toTown("A").withADistanceOf(3))
		);
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("A").withExactNumberOfStops(3)
				.build());
		
		// System.out.println(matchingPaths);
		assertThat(matchingPaths).isNotNull();
		assertThat(matchingPaths.getNumberOfRoutes()).isEqualTo(2);
		assertThat(matchingPaths.findRouteWithPath("ABCA").getTotalDistance()).isEqualTo(Distance.valueOf(45));
		assertThat(matchingPaths.findRouteWithPath("ABDA").getTotalDistance()).isEqualTo(Distance.valueOf(10));
	}
	
	@Test
	public void returnsSingleCyclicRouteUsingMaximumStops() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("C").toTown("A").withADistanceOf(30))
		);
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("A").withExactNumberOfStops(3)
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
			7. The number of trips starting at A and ending at C with exactly 4 stops.  
			In the sample data below, there are three such trips: 
			A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
		 */
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withExactNumberOfStops(4)
				.build());
		
		// System.out.println(matchingPaths);
		assertThat(matchingPaths).isNotNull();
		assertThat(matchingPaths.getNumberOfRoutes()).isEqualTo(3);
		assertThat(matchingPaths.findRouteWithPath("ABCDC").getTotalDistance()).isEqualTo(Distance.valueOf(25));
		assertThat(matchingPaths.findRouteWithPath("ADCDC").getTotalDistance()).isEqualTo(Distance.valueOf(29));
		assertThat(matchingPaths.findRouteWithPath("ADEBC").getTotalDistance()).isEqualTo(Distance.valueOf(18));
	}
	
	private void havingConfigured(TrackDescriptorListBuilder aTrackListBuilder) {
		TrackDescriptorList trackDescriptorList = aTrackListBuilder.build();
		this.railroadService = new RailroadApplicationService(trackDescriptorList);
	}

}
