package za.co.thoughtworks.trains.model.trackmaps;

import za.co.thoughtworks.trains.application.services.Distance;
import za.co.thoughtworks.trains.application.services.TrackDescriptor;
import za.co.thoughtworks.trains.infrastructure.utils.Cloneable;

/**
 * Represents a specific track linked to a start location and end location.
 * 
 * @author Yusuf
 *
 */
public class Track extends TrackDescriptor {

	private final Location fromLocation;
	private final Location toLocation;

	public Track(Location fromLocation, Location toLocation, Distance trackDistance) {
		super(fromLocation.getId(), toLocation.getId(), trackDistance);
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
	}

	public Location getFromLocation() {
		return this.fromLocation;
	}

	public boolean endLocationHasId(String locationId) {
		return this.toLocation.hasId(locationId);
	}

	public Location getToLocation() {
		return this.toLocation;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Track ").append(this.fromLocation.getId())
			.append("-").append(this.toLocation.getId())
			.append(" ").append(this.getDistance())
			.append("]");
		return sb.toString();
	}
}
