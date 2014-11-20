/**
 * 
 */
package za.co.thoughtworks.trains.model;


/**
 * @author F3657051
 *
 */
public class RailroadTracksBuilder {
	
	private RailroadTracks railroadTracks;

	public RailroadTracksBuilder() {
		this.railroadTracks = new RailroadTracks();
	}

	public RailroadTracksBuilder with(TrackBuilder trackBuilder) {
		this.railroadTracks.add(trackBuilder.build());
		return this;
	}

	public RailroadTracks build() {
		return railroadTracks;
	}

}
