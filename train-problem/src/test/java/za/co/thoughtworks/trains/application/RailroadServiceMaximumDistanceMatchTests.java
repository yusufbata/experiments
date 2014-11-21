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
public class RailroadServiceMaximumDistanceMatchTests {

	private RailroadApplicationService railroadService;
	
	@Test
	public void returnsOneRouteForMaximumDistanceMatcher() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
		);
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withMaximumDistance(20)
				.build());
		
		containsASingleValidRoute(matchingRoutes);
		assertThat(matchingRoutes.getTheOnlyRoute().getTotalDistance()).isEqualTo(Distance.valueOf(15));
	}

	private void containsASingleValidRoute(MatchingRoutes matchingRoutes) {
		assertThat(matchingRoutes).isNotNull();
		assertThat(matchingRoutes.getTheOnlyRoute()).isNotNull().isNotEqualTo(new NoRoute());
	}
	
	@Test
	public void returnsTwoRouteForMaximumDistanceMatcher() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("A").toTown("C").withADistanceOf(30))
		);
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withMaximumDistance(30)
				.build());
		
		System.out.println(matchingRoutes);
		assertThat(matchingRoutes).isNotNull();
		assertThat(matchingRoutes.getNumberOfRoutes()).isEqualTo(2);
		assertThat(matchingRoutes.get(0).getTotalDistance()).isEqualTo(Distance.valueOf(30));
		assertThat(matchingRoutes.get(1).getTotalDistance()).isEqualTo(Distance.valueOf(15));
	}
	
	@Test
	public void returnsSingleCyclicRouteUsingMaximumDistance() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("C").toTown("A").withADistanceOf(30))
		);
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("A").withMaximumDistance(45)
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
			10.The number of different routes from C to C with a distance of less than 30.  
			In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
		 */
		
		// CDEBCEBC, CEBCDEBC
		
		MatchingRoutes matchingRoutes = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("C").toTown("C").withMaximumDistance(29).build());
		
		System.out.println(matchingRoutes);
		assertThat(matchingRoutes).isNotNull();
		assertThat(matchingRoutes.getNumberOfRoutes()).isEqualTo(7);
		assertThat(matchingRoutes.findRouteWithPath("CDC").getTotalDistance()).isEqualTo(Distance.valueOf(16));
		assertThat(matchingRoutes.findRouteWithPath("CEBC").getTotalDistance()).isEqualTo(Distance.valueOf(9));
		assertThat(matchingRoutes.findRouteWithPath("CEBCDC").getTotalDistance()).isEqualTo(Distance.valueOf(25));
		assertThat(matchingRoutes.findRouteWithPath("CDCEBC").getTotalDistance()).isEqualTo(Distance.valueOf(25));
		assertThat(matchingRoutes.findRouteWithPath("CDEBC").getTotalDistance()).isEqualTo(Distance.valueOf(21));
		assertThat(matchingRoutes.findRouteWithPath("CEBCEBC").getTotalDistance()).isEqualTo(Distance.valueOf(18));
		assertThat(matchingRoutes.findRouteWithPath("CEBCEBCEBC").getTotalDistance()).isEqualTo(Distance.valueOf(27));
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
