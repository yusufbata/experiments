/**
 * 
 */
package za.co.thoughtworks.trains.model.trackmaps;



/**
 * Builds a collection of TrackDescriptors (TrackDescriptorList).
 * 
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
