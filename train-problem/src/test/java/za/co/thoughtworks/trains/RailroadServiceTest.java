/**
 * 
 */
package za.co.thoughtworks.trains;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.application.RailroadApplicationService;
import za.co.thoughtworks.trains.application.RouteSpec;
import za.co.thoughtworks.trains.application.RouteSpecBuilder;
import za.co.thoughtworks.trains.model.RailroadTracks;
import za.co.thoughtworks.trains.model.RailroadTracksBuilder;
import za.co.thoughtworks.trains.model.Route;
import za.co.thoughtworks.trains.model.TrackBuilder;

/**
 * @author Yusuf
 *
 */
public class RailroadServiceTest {

	private RailroadApplicationService railroadService;
	
	@Test
	public void findsRouteBetweenTwoTowns() {
		havingConfigured(railRoadTracks());
		
		Route resultRoute = railroadService.findRouteWith(aRouteSpec().fromTown("A").toTown("B")
				.build());
		
		assertThat(resultRoute).isNotNull();
//		assertThat(resultRoute.getStartLocation())
	}
	
	@Ignore
	@Test
	public void findsNoRouteBetweenTwoTownsWhenTownDoesntExist() {
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)));
		
		Route resultRoute = railroadService.findRouteWith(
				aRouteSpec().fromTown("A").toTown("B")
				.build());
		
		assertThat(resultRoute).isNotNull();
	}
	
	@Ignore
	@Test
	public void findsNoRouteBetweenTwoTownsWhenPathDoesntExist() {
		//"AB5-BC10"
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(10))
				);
		
		Route resultRoute = railroadService.findRouteWith(
				aRouteSpec().fromTown("A").toTown("B")
				.build());
		
		assertThat(resultRoute).isNotNull();
	}
	
	@Test
	public void computesDistanceOfRouteBetweenTwoTowns() {
		havingConfigured(railRoadTracks()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5)));
		
		Route resultRoute = railroadService.findRouteWith(
				aRouteSpec().fromTown("A").toTown("B")
				.build());
		
		theDistanceOfTheRouteIs(5, resultRoute);
	}
	
	
	
	
	
	
	
	
	

	private void theDistanceOfTheRouteIs(int distance, Route resultRoute) {
		assertThat(resultRoute).isNotNull();
		assertThat(resultRoute.getTotalDistance()).isEqualTo(Distance.valueOf(distance));
	}

	private RouteSpecBuilder aRouteSpec() {
		return new RouteSpecBuilder();
	}

	private void havingConfigured(RailroadTracksBuilder aRailroadTracksBuilder) {
		RailroadTracks railroadTracks = aRailroadTracksBuilder.build();
		this.railroadService = new RailroadApplicationService(railroadTracks);
	}

	private TrackBuilder aTrack() {
		return new TrackBuilder();
	}

	public static RailroadTracksBuilder railRoadTracks() {
		return new RailroadTracksBuilder();
	}

}
