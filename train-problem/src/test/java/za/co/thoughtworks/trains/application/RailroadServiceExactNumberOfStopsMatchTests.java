/**
 * 
 */
package za.co.thoughtworks.trains.application;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aRouteSpec;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrackList;

import org.junit.Ignore;
import org.junit.Test;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.application.RailroadApplicationService;
import za.co.thoughtworks.trains.application.TrackDescriptorList;
import za.co.thoughtworks.trains.model.IRoute;
import za.co.thoughtworks.trains.model.MatchingRoutes;
import za.co.thoughtworks.trains.model.NoRoute;

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
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withExactNumberOfStops(2)
				.build());
		
		System.out.println(matchingRoutes);
		containsASingleValidRoute(matchingRoutes);
		assertThat(matchingRoutes.getTheOnlyRoute().getTotalDistance()).isEqualTo(Distance.valueOf(15));
	}

	private void containsASingleValidRoute(MatchingRoutes matchingRoutes) {
		assertThat(matchingRoutes).isNotNull();
		assertThat(matchingRoutes.getTheOnlyRoute()).isNotNull().isNotEqualTo(new NoRoute());
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
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("A").withExactNumberOfStops(3)
				.build());
		
		System.out.println(matchingRoutes);
		assertThat(matchingRoutes).isNotNull();
		assertThat(matchingRoutes.getNumberOfRoutes()).isEqualTo(2);
		assertThat(matchingRoutes.get(0).getTotalDistance()).isEqualTo(Distance.valueOf(45));
		assertThat(matchingRoutes.get(1).getTotalDistance()).isEqualTo(Distance.valueOf(10));
	}
	
	@Test
	public void returnsSingleCyclicRouteUsingMaximumStops() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("C").toTown("A").withADistanceOf(30))
		);
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("A").withExactNumberOfStops(3)
				.build());
		
		System.out.println(matchingRoutes);
		containsASingleValidRoute(matchingRoutes);
		assertThat(matchingRoutes.get(0).getTotalDistance()).isEqualTo(Distance.valueOf(45));
	}
	
	@Test
	public void verifyProblemSampleDistanceCalculations() {
		// AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(4))
				.with(aTrack().fromTown("C").toTown("D").withADistanceOf(8))
				.with(aTrack().fromTown("D").toTown("C").withADistanceOf(8))
				.with(aTrack().fromTown("D").toTown("E").withADistanceOf(6))
				.with(aTrack().fromTown("A").toTown("D").withADistanceOf(5))
				.with(aTrack().fromTown("C").toTown("E").withADistanceOf(2))
				.with(aTrack().fromTown("E").toTown("B").withADistanceOf(3))
				.with(aTrack().fromTown("A").toTown("E").withADistanceOf(7))
		);
		
		/*
			7. The number of trips starting at A and ending at C with exactly 4 stops.  
			In the sample data below, there are three such trips: 
			A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
		 */
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withExactNumberOfStops(4)
				.build());
		
		System.out.println(matchingRoutes);
		assertThat(matchingRoutes).isNotNull();
		assertThat(matchingRoutes.getNumberOfRoutes()).isEqualTo(3);
		assertThat(matchingRoutes.get(0).getTotalDistance()).isEqualTo(Distance.valueOf(25));
		assertThat(matchingRoutes.get(1).getTotalDistance()).isEqualTo(Distance.valueOf(29));
		assertThat(matchingRoutes.get(2).getTotalDistance()).isEqualTo(Distance.valueOf(18));
	}
	
	
	
	
	private void havingConfigured(TrackDescriptorListBuilder aTrackListBuilder) {
		TrackDescriptorList trackDescriptorList = aTrackListBuilder.build();
		this.railroadService = new RailroadApplicationService(trackDescriptorList);
	}

	private void theTotalDistanceOfTheRouteIs(int distance, IRoute resultRoute) {
		assertThat(resultRoute).isNotNull().isNotEqualTo(new NoRoute());
		assertThat(resultRoute.getTotalDistance()).isEqualTo(Distance.valueOf(distance));
	}

}
