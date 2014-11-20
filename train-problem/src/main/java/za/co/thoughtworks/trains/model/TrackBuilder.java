package za.co.thoughtworks.trains.model;

public class TrackBuilder {

	private String fromTownId;
	private String toTownId;
	private int trackDistance;

	public TrackBuilder fromTown(String fromTownId) {
		this.fromTownId = fromTownId;
		return this;
	}

	public TrackBuilder toTown(String toTownId) {
		this.toTownId = toTownId;
		return this;
	}

	public TrackBuilder withADistanceOf(int trackDistance) {
		this.trackDistance = trackDistance;
		return this;
	}

	public Track build() {
		return new Track(fromTownId, toTownId, trackDistance);
	}

}
