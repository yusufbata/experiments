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
		
		assertThat(resultRoute).isNotNull();
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
		
		assertThat(resultRoute).isNotNull().isNotEqualTo(new NoRoute());
		theTotalDistanceOfTheRouteIs(15, resultRoute);
	}
	
	
	
	
	
	
	private void havingConfigured(RailroadTracksBuilder aRailroadTracksBuilder) {
		TrackDescriptorList trackDescriptorList = aRailroadTracksBuilder.build();
		this.railroadService = new RailroadApplicationService(trackDescriptorList);
	}

	private void theTotalDistanceOfTheRouteIs(int distance, IRoute resultRoute) {
		assertThat(resultRoute).isNotNull();
		assertThat(resultRoute.getTotalDistance()).isEqualTo(Distance.valueOf(distance));
	}

}
