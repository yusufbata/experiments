/**
 * 
 */
package za.co.thoughtworks.trains.application;

import za.co.thoughtworks.trains.application.services.TrackDescriptorBuilder;
import za.co.thoughtworks.trains.application.services.TrackDescriptorList;


/**
 * @author Yusuf
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
