package za.co.thoughtworks.trains.application.services;


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

	/**
	 * Expected pattern: From-To-DistanceInteger
	 * Expected pattern example: A-B-4
	 * Items separated using '-'
	 * 
	 * @param stringPattern
	 * @return
	 */
	public static TrackDescriptor constructTrackDescriptorFromStringPattern(
			String stringPattern) {
		stringPattern = stringPattern.trim();
		String[] elements = stringPattern.split("-");
		
		if (elements.length != 3) {
			return null;
		}
		String fromLocation = elements[0];
		String toLocation = elements[1];
		String distanceString = elements[2];
		
		Integer distance = convertStringToInt(distanceString);
		if (distance == null) {
			return null;
		}
		return new TrackDescriptor(fromLocation, toLocation, Distance.valueOf(distance));
	}

	private static Integer convertStringToInt(String string) {
		Integer distance = null;
		try {
			distance = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			System.err.println("Failed to convert string to integer: " + string);
			System.err.println(e);
		}
		return distance;
	}

}
