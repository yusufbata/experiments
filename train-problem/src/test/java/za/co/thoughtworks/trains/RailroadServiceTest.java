/**
 * 
 */
package za.co.thoughtworks.trains;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Yusuf
 *
 */
public class RailroadServiceTest {

	@Test
	public void findsRouteBetweenTwoTowns() {
		RailTracks railTracks = new RailTracks("AB5");
		RailroadService railroadService = new RailroadService(railTracks);
		
		RouteSpec routeSpec = new RouteSpec("A-B");
		Route resultRoute = railroadService.findRoute(routeSpec);
		assertThat(resultRoute).isNotNull();
	}
	
	@Test
	public void computesDistanceOfRouteBetweenTwoTowns() {
		RailTracks railTracks = new RailTracks("AB5");
		RailroadService railroadService = new RailroadService(railTracks);
		
		RouteSpec routeSpec = new RouteSpec("A-B");
		Route resultRoute = railroadService.findRoute(routeSpec);
		assertThat(resultRoute).isNotNull();
		assertThat(resultRoute.getTotalDistance()).equals(Distance.valueOf(5));
	}

}
