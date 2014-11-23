package za.co.thoughtworks.trains.test;

import static org.fest.assertions.Assertions.assertThat;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrack;
import static za.co.thoughtworks.trains.application.BuilderFactory.aTrackList;
import za.co.thoughtworks.trains.application.services.TrackDescriptorListBuilder;
import za.co.thoughtworks.trains.model.path.MatchingPaths;
import za.co.thoughtworks.trains.model.path.NoPathFound;

public class TestUtils {

	public static TrackDescriptorListBuilder buildSampleProblemTracks() {
		return aTrackList()
				.with(aTrack().fromTown("A").toTown("B").withADistanceOf(5))
				.with(aTrack().fromTown("B").toTown("C").withADistanceOf(4))
				.with(aTrack().fromTown("C").toTown("D").withADistanceOf(8))
				.with(aTrack().fromTown("D").toTown("C").withADistanceOf(8))
				.with(aTrack().fromTown("D").toTown("E").withADistanceOf(6))
				.with(aTrack().fromTown("A").toTown("D").withADistanceOf(5))
				.with(aTrack().fromTown("C").toTown("E").withADistanceOf(2))
				.with(aTrack().fromTown("E").toTown("B").withADistanceOf(3))
				.with(aTrack().fromTown("A").toTown("E").withADistanceOf(7));
	}

	public static void containsASingleValidRoute(MatchingPaths matchingPaths) {
		assertThat(matchingPaths).isNotNull();
		assertThat(matchingPaths.findTheOnlyPath()).isNotNull().isNotEqualTo(MatchingPaths.noPathFound());
	}
}
