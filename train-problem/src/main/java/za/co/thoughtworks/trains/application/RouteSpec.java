/**
 * 
 */
package za.co.thoughtworks.trains.application;

import java.util.List;

/**
 * @author Yusuf
 *
 */
public class RouteSpec {

	private final String fromTown;
	private final List<String> targetPath;
	private int maximumStops;

	public RouteSpec(String fromTown, List<String> targetPath, int maximumStops) {
		this.fromTown = fromTown;
		this.targetPath = targetPath;
		this.maximumStops = maximumStops;
	}

	public String getStartLocationId() {
		return fromTown;
	}

	public String getEndLocationId() {
		return this.targetPath.get(this.targetPath.size() - 1);
	}

	public List<String> getTargetPath() {
		return targetPath;
	}

	public int getMaximumStops() {
		return maximumStops;
	}
	
}
