/**
 * 
 */
package za.co.thoughtworks.trains.application;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aRouteSpec;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.railRoadTracks;

import org.junit.Ignore;
import org.junit.Test;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.application.RailroadApplicationService;
import za.co.thoughtworks.trains.application.TrackDescriptorList;
import za.co.thoughtworks.trains.model.IRoute;
import za.co.thoughtworks.trains.model.NoRoute;
import za.co.thoughtworks.trains.model.RailroadTracksBuilder;

/**
 * @author Yusuf
 *
 */
public class RailroadServiceTest {

	private RailroadApplicationService railroadService;
	
	@Test
	public void findsRouteBetweenTwoTowns() {
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B")));
		
		IRoute resultRoute = railroadService.findRouteUsing(aRouteSpec().fromTown("A").toTown("B")
				.build());
		
		assertThat(resultRoute).isNotNull().isNotEqualTo(new NoRoute());
//		assertThat(resultRoute);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void findsNoRouteBetweenTwoTownsWhenEndTownDoesntExist() {
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)));
		
		IRoute resultRoute = railroadService.findRouteUsing(
				aRouteSpec().fromTown("A").toTown("C")
				.build());
		
		assertThat(resultRoute).isNotNull().isEqualTo(new NoRoute());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void findsNoRouteBetweenTwoTownsWhenStartTownDoesntExist() {
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)));
		
		IRoute resultRoute = railroadService.findRouteUsing(
				aRouteSpec().fromTown("C").toTown("A")
				.build());
		
		assertThat(resultRoute).isNotNull().isEqualTo(new NoRoute());
	}
	
	@Test
	public void findsNoRouteBetweenTwoTownsWhenPathDoesntExist() {
		//"AB5-BC10"
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("C").toTown("D").withADistanceOf(10))
				);
		
		IRoute resultRoute = railroadService.findRouteUsing(
				aRouteSpec().fromTown("A").toTown("D")
				.build());
		
		assertThat(resultRoute).isNotNull().isEqualTo(new NoRoute());
	}
	
	@Test
	public void computesDistanceOfRouteBetweenTwoTowns() {
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)));
		
		IRoute resultRoute = railroadService.findRouteUsing(
				aRouteSpec().fromTown("A").toTown("B")
				.build());
		
		theTotalDistanceOfTheRouteIs(5, resultRoute);
	}
	
	@Test
	public void computesDistanceOfRouteBetweenThreeTowns() {
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
		);
		
		IRoute resultRoute = railroadService.findRouteUsing(
				aRouteSpec().fromTown("A").toTown("B").toTown("C")
				.build());
		
		theTotalDistanceOfTheRouteIs(15, resultRoute);
	}
	
	@Test
	public void verifyProblemSampleDistanceCalculations() {
		// AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		havingConfigured(railRoadTracks()
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
		
		IRoute resultRoute = railroadService.findRouteUsing(
				aRouteSpec().fromTown("A").toTown("B").toTown("C").build());
		theTotalDistanceOfTheRouteIs(9, resultRoute);
	}
	
	
	
	
	private void havingConfigured(RailroadTracksBuilder aRailroadTracksBuilder) {
		TrackDescriptorList trackDescriptorList = aRailroadTracksBuilder.build();
		this.railroadService = new RailroadApplicationService(trackDescriptorList);
	}

	private void theTotalDistanceOfTheRouteIs(int distance, IRoute resultRoute) {
		assertThat(resultRoute).isNotNull().isNotEqualTo(new NoRoute());
		assertThat(resultRoute.getTotalDistance()).isEqualTo(Distance.valueOf(distance));
	}

}
