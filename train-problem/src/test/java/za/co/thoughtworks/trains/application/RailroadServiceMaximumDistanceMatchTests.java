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
import za.co.thoughtworks.trains.application.services.NoPathFound;
import za.co.thoughtworks.trains.application.services.Path;
import za.co.thoughtworks.trains.application.services.RailroadApplicationService;
import za.co.thoughtworks.trains.application.services.TrackDescriptorList;
import za.co.thoughtworks.trains.test.TestUtils;

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
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withMaximumDistance(20)
				.build());
		
		TestUtils.containsASingleValidRoute(matchingPaths);
		assertThat(matchingPaths.findTheOnlyPath().getTotalDistance()).isEqualTo(Distance.valueOf(15));
	}
	
	@Test
	public void returnsTwoRouteForMaximumDistanceMatcher() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("A").toTown("C").withADistanceOf(30))
		);
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("C").withMaximumDistance(30)
				.build());
		
		// System.out.println(matchingPaths);
		assertThat(matchingPaths).isNotNull();
		assertThat(matchingPaths.getNumberOfRoutes()).isEqualTo(2);
		assertThat(matchingPaths.findRouteWithPath("ABC").getTotalDistance()).isEqualTo(Distance.valueOf(15));
		assertThat(matchingPaths.findRouteWithPath("AC").getTotalDistance()).isEqualTo(Distance.valueOf(30));
	}
	
	@Test
	public void returnsSingleCyclicRouteUsingMaximumDistance() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				.with(aTrack().fromTown("C").toTown("A").withADistanceOf(30))
		);
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("A").toTown("A").withMaximumDistance(45)
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
			10.The number of different routes from C to C with a distance of less than 30.  
			In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
		 */
		
		// CDEBCEBC, CEBCDEBC
		
		MatchingPaths matchingPaths = railroadService.findAllRoutesUsing(
				aRouteSpec().fromTown("C").toTown("C").withMaximumDistance(29).build());
		
		// System.out.println(matchingPaths);
		assertThat(matchingPaths).isNotNull();
		assertThat(matchingPaths.getNumberOfRoutes()).isEqualTo(7);
		assertThat(matchingPaths.findRouteWithPath("CDC").getTotalDistance()).isEqualTo(Distance.valueOf(16));
		assertThat(matchingPaths.findRouteWithPath("CEBC").getTotalDistance()).isEqualTo(Distance.valueOf(9));
		assertThat(matchingPaths.findRouteWithPath("CEBCDC").getTotalDistance()).isEqualTo(Distance.valueOf(25));
		assertThat(matchingPaths.findRouteWithPath("CDCEBC").getTotalDistance()).isEqualTo(Distance.valueOf(25));
		assertThat(matchingPaths.findRouteWithPath("CDEBC").getTotalDistance()).isEqualTo(Distance.valueOf(21));
		assertThat(matchingPaths.findRouteWithPath("CEBCEBC").getTotalDistance()).isEqualTo(Distance.valueOf(18));
		assertThat(matchingPaths.findRouteWithPath("CEBCEBCEBC").getTotalDistance()).isEqualTo(Distance.valueOf(27));
	}
	
	private void havingConfigured(TrackDescriptorListBuilder aTrackListBuilder) {
		TrackDescriptorList trackDescriptorList = aTrackListBuilder.build();
		this.railroadService = new RailroadApplicationService(trackDescriptorList);
	}
}
