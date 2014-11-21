package za.co.thoughtworks.trains.application;

public class TrackDescriptorBuilder {

	private String fromTownId;
	private String toTownId;
	private int trackDistance;

	public TrackDescriptorBuilder fromTown(String fromTownId) {
		this.fromTownId = fromTownId;
		return this;
	}

	public TrackDescriptorBuilder toTown(String toTownId) {
		this.toTownId = toTownId;
		return this;
	}

	public TrackDescriptorBuilder withADistanceOf(int trackDistance) {
		this.trackDistance = trackDistance;
		return this;
	}

	public TrackDescriptor build() {
		return new TrackDescriptor(fromTownId, toTownId, Distance.valueOf(trackDistance));
	}

}
