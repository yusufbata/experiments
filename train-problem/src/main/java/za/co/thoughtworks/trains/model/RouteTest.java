package za.co.thoughtworks.trains.model;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import za.co.thoughtworks.trains.application.TrackDescriptor;

public class RouteTest {

	@Test
	public void testFindNextPossibleRoutes() {
		TrackDescriptor aToB = new TrackDescriptor("A", "B", 5);
		TrackDescriptor bToC = new TrackDescriptor("B", "C", 6);
		List<TrackDescriptor> trackList = new ArrayList<TrackDescriptor>(Arrays.asList(aToB, bToC));

		Location targetEndLocation = new Location("C");
		
		
		List<String> toTownList = new ArrayList<String>();
		toTownList.addAll(Arrays.asList("B", "C"));
		
		Route route = new Route(trackList, targetEndLocation, toTownList);
		List<Route> nextPossibleRoutes = route.findNextPossibleRoutes();
		
		assertThat(nextPossibleRoutes).isNotNull().isNotEmpty().hasSize(1);
//		assertThat(nextPossibleRoutes.get(0)).isEqualTo(route.);
	}

}
