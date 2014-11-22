/**
 * 
 */
package za.co.thoughtworks.trains.application;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aRouteSpec;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrackList;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import za.co.thoughtworks.trains.application.services.Distance;
import za.co.thoughtworks.trains.application.services.NoPath;
import za.co.thoughtworks.trains.application.services.Path;
import za.co.thoughtworks.trains.application.services.RailroadApplicationService;
import za.co.thoughtworks.trains.application.services.RouteSpec;
import za.co.thoughtworks.trains.application.services.TrackDescriptorList;
import za.co.thoughtworks.trains.test.TestUtils;

/**
 * @author Yusuf
 *
 */
public class RailroadServiceExactRouteMatchTests {

	private RailroadApplicationService railroadService;
	
	@Test
	public void findsRouteBetweenTwoTowns() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B")));
		
		Path resultRoute = railroadService.findSingleRouteUsing(aRouteSpec().fromTown("A").toTown("B")
				.build());
		
		System.out.println(resultRoute);
		assertThat(resultRoute).isNotNull().isNotEqualTo(new NoPath());
//		
//		BatchApplicationRunner runner = new BatchApplicationRunner(railroadService, 
//				new ArrayList<RouteSpec>(Arrays.asList(aRouteSpec().fromTown("A").toTown("B").build())));
//		runner.run();
	}
	
	@Test
	public void returnsOneRouteForExactMatch() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
		);
		
		Path resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("B").toTown("C")
				.build());
		
		theTotalDistanceOfTheRouteIs(15, resultRoute);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void findsNoRouteBetweenTwoTownsWhenEndTownDoesntExist() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)));
		
		Path resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("C")
				.build());
		
		assertThat(resultRoute).isNotNull().isEqualTo(new NoPath());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void findsNoRouteBetweenTwoTownsWhenStartTownDoesntExist() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)));
		
		Path resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("C").toTown("A")
				.build());
		
		assertThat(resultRoute).isNotNull().isEqualTo(new NoPath());
	}
	
	@Test
	public void findsNoRouteBetweenTwoTownsWhenPathDoesntExist() {
		//"AB5-BC10"
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("C").toTown("D").withADistanceOf(10))
				);
		
		Path resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("D")
				.build());
		
		assertThat(resultRoute).isNotNull().isEqualTo(new NoPath());
	}
	
	@Test
	public void computesDistanceOfRouteBetweenTwoTowns() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)));
		
		Path resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("B")
				.build());
		
		theTotalDistanceOfTheRouteIs(5, resultRoute);
	}
	
	@Test
	public void computesDistanceOfRouteBetweenThreeTowns() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
		);
		
		Path resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("B").toTown("C")
				.build());
		
		theTotalDistanceOfTheRouteIs(15, resultRoute);
	}
	
	@Test
	public void computesDistanceOfRouteBetweenTwoTownsWithLastOneLinkedBackToFirst() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("A").withADistanceOf(10))
		);
		
		Path resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("B").toTown("A")
				.build());
		
		theTotalDistanceOfTheRouteIs(15, resultRoute);
	}
	
	@Test
	public void computesDistanceOfRouteBetweenTwoTownsViaStartTownBackToLast() {
		havingConfigured(aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("A").withADistanceOf(10))
		);
		
		Path resultRoute = railroadService.findSingleRouteUsing(
				aRouteSpec().fromTown("A").toTown("B").toTown("A").toTown("B")
				.build());
		
		theTotalDistanceOfTheRouteIs(20, resultRoute);
	}
	
	@Test
	public void verifyProblemSampleDistanceCalculations() {
		// AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		havingConfigured(TestUtils.buildSampleProblemTracks());
		
		/*
			1. The distance of the route A-B-C.
			2. The distance of the route A-D.
			3. The distance of the route A-D-C.
			4. The distance of the route A-E-B-C-D.
			5. The distance of the route A-E-D.
		 */
		
		Path resultRoute = railroadService.findSingleRouteUsing(
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
		assertThat(resultRoute).isNotNull().isEqualTo(new NoPath());
	}
	
	
	
	
	private void havingConfigured(TrackDescriptorListBuilder aTrackListBuilder) {
		TrackDescriptorList trackDescriptorList = aTrackListBuilder.build();
		this.railroadService = new RailroadApplicationService(trackDescriptorList);
	}

	private void theTotalDistanceOfTheRouteIs(int distance, Path resultRoute) {
		assertThat(resultRoute).isNotNull().isNotEqualTo(new NoPath());
		assertThat(resultRoute.getTotalDistance()).isEqualTo(Distance.valueOf(distance));
	}

}
