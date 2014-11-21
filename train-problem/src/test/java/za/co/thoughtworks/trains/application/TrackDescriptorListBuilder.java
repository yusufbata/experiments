/**
 * 
 */
package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.application.TrackDescriptorBuilder;
import za.co.thoughtworks.trains.application.TrackDescriptorList;


/**
 * @author F3657051
 *
 */
public class TrackDescriptorListBuilder {
	
	private TrackDescriptorList trackDescriptorList;

	public TrackDescriptorListBuilder() {
		this.trackDescriptorList = new TrackDescriptorList();
	}

	public TrackDescriptorListBuilder with(TrackDescriptorBuilder trackDescriptorBuilder) {
		this.trackDescriptorList.add(trackDescriptorBuilder.build());
		return this;
	}

	public TrackDescriptorList build() {
		return trackDescriptorList;
	}

}
