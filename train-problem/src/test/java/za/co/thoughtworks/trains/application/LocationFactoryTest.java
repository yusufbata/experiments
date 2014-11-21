package za.co.thoughtworks.trains.application;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.railRoadTracks;

import java.util.List;

import org.junit.Test;

import za.co.thoughtworks.trains.model.Location;

public class LocationFactoryTest {

	@Test
	public void constructsLocationsFromRailroadTracks() {
		TrackDescriptorList trackDescriptorList = railRoadTracks().with(
				aTrack().fromTown("A").toTown("B").withADistanceOf(5)).build();

		LocationFactory locationFactory = new LocationFactory();
		List<Location> locationList = locationFactory.constructLocationsFrom(trackDescriptorList);

		assertThat(locationList).isNotNull().isNotEmpty();
		assertThat(locationList).hasSize(2);
		assertThat(locationList.get(0).getId()).isEqualTo("A");
		assertThat(locationList.get(1).getId()).isEqualTo("B");
	}

}
