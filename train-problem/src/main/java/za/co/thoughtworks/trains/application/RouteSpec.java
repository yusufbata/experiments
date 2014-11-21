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
	private int exactNumberOfStops;
	private int maximumDistance;

	public RouteSpec(String fromTown, List<String> targetPath, int maximumStops, int exactNumberOfStops, int maximumDistance) {
		this.fromTown = fromTown;
		this.targetPath = targetPath;
		this.maximumStops = maximumStops;
		this.exactNumberOfStops = exactNumberOfStops;
		this.maximumDistance = maximumDistance;
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

	public int getExactNumberOfStops() {
		return exactNumberOfStops;
	}

	public int getMaximumDistance() {
		return maximumDistance;
	}
	
}
