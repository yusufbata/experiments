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
public class RailroadServiceMaximumStopsMatchTests {

	private RailroadApplicationService railroadService;
	
	@Test
	public void returnsOneRouteForMaximumStopsMatcher() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
		);
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withMaximumStops(2)
				.build());
		
		assertThat(matchingRoutes).isNotNull();
		assertThat(matchingRoutes.getTheOnlyRoute()).isNotNull().isNotEqualTo(new NoRoute());
		assertThat(matchingRoutes.getTheOnlyRoute().getTotalDistance()).isEqualTo(Distance.valueOf(15));
	}
	
	@Test
	public void returnsTwoRouteForMaximumStopsMatcher() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("A").toTown("C").withADistanceOf(30))
		);
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withMaximumStops(2)
				.build());
		
		System.out.println("Matching routes=" + matchingRoutes);
		assertThat(matchingRoutes).isNotNull();
		assertThat(matchingRoutes.getNumberOfRoutes()).isEqualTo(2);
		assertThat(matchingRoutes.get(0).getTotalDistance()).isEqualTo(Distance.valueOf(30));
		assertThat(matchingRoutes.get(1).getTotalDistance()).isEqualTo(Distance.valueOf(15));
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
			1. The distance of the route A-B-C.
			2. The distance of the route A-D.
			3. The distance of the route A-D-C.
			4. The distance of the route A-E-B-C-D.
			5. The distance of the route A-E-D.
		 */
		
		IRoute resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("B").toTown("C").build());
		theTotalDistanceOfTheRouteIs(9, resultRoute);
		
		resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("D").build());
		theTotalDistanceOfTheRouteIs(5, resultRoute);
		
		resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("D").toTown("C").build());
		theTotalDistanceOfTheRouteIs(13, resultRoute);
		
		resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("E").toTown("B").toTown("C").toTown("D").build());
		theTotalDistanceOfTheRouteIs(22, resultRoute);
		
		resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("E").toTown("D").build());
		assertThat(resultRoute).isNotNull().isEqualTo(new NoRoute());
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
