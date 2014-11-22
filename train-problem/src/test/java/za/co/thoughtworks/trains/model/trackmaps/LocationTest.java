package za.co.thoughtworks.trains.model.trackmaps;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import za.co.thoughtworks.trains.application.services.Distance;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.Track;

public class LocationTest {

	@Test
	public void testAddOutgoingTrack() {
		List<Track> outgoingTracks = new ArrayList<Track>();
		Location location = new Location("A", outgoingTracks);
		Track newTrack = new Track(location, location, Distance.valueOf(5));
		location.addOutgoingTrack(newTrack);
		
		assertThat(location.getOutgoingTracks()).isNotEmpty().hasSize(1);
		assertThat(location.getOutgoingTracks().get(0)).isEqualTo(newTrack);
	}

	@Test
	public void testAddOutgoingTrackTwice() {
		List<Track> outgoingTracks = new ArrayList<Track>();
		Location location = new Location("A", outgoingTracks);
		Track newTrack = new Track(location, location, Distance.valueOf(5));
		
		location.addOutgoingTrack(newTrack);
		location.addOutgoingTrack(newTrack);
		
		assertThat(location.getOutgoingTracks()).isNotEmpty().hasSize(2);
		assertThat(location.getOutgoingTracks().get(0)).isEqualTo(newTrack);
		assertThat(location.getOutgoingTracks().get(1)).isEqualTo(newTrack);
	}

}
