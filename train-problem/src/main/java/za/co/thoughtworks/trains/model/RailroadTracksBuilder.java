/**
 * 
 */
package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.TrackDescriptorBuilder;
import za.co.thoughtworks.trains.application.TrackDescriptorList;


/**
 * @author F3657051
 *
 */
public class RailroadTracksBuilder {
	
	private TrackDescriptorList trackDescriptorList;

	public RailroadTracksBuilder() {
		this.trackDescriptorList = new TrackDescriptorList();
	}

	public RailroadTracksBuilder with(TrackDescriptorBuilder trackDescriptorBuilder) {
		this.trackDescriptorList.add(trackDescriptorBuilder.build());
		return this;
	}

	public TrackDescriptorList build() {
		return trackDescriptorList;
	}

}
