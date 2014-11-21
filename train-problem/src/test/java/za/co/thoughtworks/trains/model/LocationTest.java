package za.co.thoughtworks.trains.model;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import za.co.thoughtworks.trains.application.Distance;

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
	
	@Ignore
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
