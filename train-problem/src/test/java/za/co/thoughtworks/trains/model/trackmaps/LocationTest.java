package za.co.thoughtworks.trains.model.trackmaps;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.Track;

public class LocationTest {

	private Location location;

	@Test
	public void testAddOutgoingTrack() {
		Location endLocation = configureLocationWithEmptyTracks("A", "B");
		Track newTrack = new Track(location, endLocation, Distance.valueOf(5));
		
		location.addOutgoingTrack(newTrack);
		
		assertThat(location.getOutgoingTracks()).isNotEmpty().hasSize(1);
		assertThat(location.getOutgoingTracks().get(0)).isEqualTo(newTrack);
	}

	@Test
	public void testAddOutgoingTrackTwice() {
		Location endLocation = configureLocationWithEmptyTracks("A", "B");
		Track newTrack = new Track(location, endLocation, Distance.valueOf(5));
		Location anotherEndLocation = configureLocationWithEmptyTracks("A", "C");
		Track anotherTrack = new Track(location, anotherEndLocation, Distance.valueOf(7));
		
		location.addOutgoingTrack(newTrack);
		location.addOutgoingTrack(anotherTrack);
		
		assertThat(location.getOutgoingTracks()).isNotEmpty().hasSize(2);
		assertThat(location.getOutgoingTracks().get(0)).isEqualTo(newTrack);
		assertThat(location.getOutgoingTracks().get(1)).isEqualTo(anotherTrack);
	}

	private Location configureLocationWithEmptyTracks(String from, String to) {
		List<Track> outgoingTracks = new ArrayList<Track>();
		location = new Location(from, outgoingTracks);
		Location endLocation = new Location(to, outgoingTracks);
		return endLocation;
	}
}
