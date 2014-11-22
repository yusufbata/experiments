package za.co.thoughtworks.trains.application;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrackList;

import java.util.List;

import org.junit.Test;

import za.co.thoughtworks.trains.application.services.TrackDescriptorList;
import za.co.thoughtworks.trains.infrastructure.persistence.InMemoryLocationRepository;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.LocationFactory;

public class LocationFactoryTest {

	@Test
	public void constructsLocationsFromRailroadTracks() {
		TrackDescriptorList trackDescriptorList = aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(5))
				.with(aTrack().fromTown("C").toTown("A").withADistanceOf(5))
				.build();

		LocationFactory locationFactory = new LocationFactory();
		List<Location> locationList = locationFactory.constructLocationsFrom(trackDescriptorList);

		assertThat(locationList).isNotNull().isNotEmpty();
		assertThat(locationList).hasSize(3);
		assertThat(locationList.get(0).getId()).isEqualTo("A");
		assertThat(locationList.get(1).getId()).isEqualTo("B");
		assertThat(locationList.get(2).getId()).isEqualTo("C");
		
		assertThat(locationList.get(0).getOutgoingTracks()).isNotEmpty().hasSize(1);
	}
	
	@Test
	public void hasCorrectTrackLinksWithSingleTrack() {
		TrackDescriptorList trackDescriptorList = aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.build();
		LocationFactory locationFactory = new LocationFactory();
		
		List<Location> locationList = locationFactory.constructLocationsFrom(trackDescriptorList);
		
		LocationRepository locationRepository = new InMemoryLocationRepository(locationList);
		assertThat(locationList).isNotNull().isNotEmpty();
		assertThat(locationRepository.findLocationWithId("A").getOutgoingTracks()).isNotEmpty().hasSize(1);
		assertThat(locationRepository.findLocationWithId("B").getOutgoingTracks()).isEmpty();
	}
	
	@Test
	public void hasCorrectTrackLinksWithTwoTracks() {
		TrackDescriptorList trackDescriptorList = aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("A").toTown("C").withADistanceOf(5))
				.build();
		LocationFactory locationFactory = new LocationFactory();
		
		List<Location> locationList = locationFactory.constructLocationsFrom(trackDescriptorList);
		
		LocationRepository locationRepository = new InMemoryLocationRepository(locationList);
		assertThat(locationList).isNotNull().isNotEmpty();
		assertThat(locationRepository.findLocationWithId("B").getOutgoingTracks()).isEmpty();
		assertThat(locationRepository.findLocationWithId("C").getOutgoingTracks()).isEmpty();
		assertThat(locationRepository.findLocationWithId("A").getOutgoingTracks()).isNotEmpty().hasSize(2);
	}
}
