/**
 * 
 */
package za.co.thoughtworks.trains.application.services;

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
	private boolean findRouteWithShortestDistance;

	public RouteSpec(String fromTown, List<String> targetPath, int maximumStops, int exactNumberOfStops, int maximumDistance, boolean findRouteWithShortestDistance) {
		this.fromTown = fromTown;
		this.targetPath = targetPath;
		this.maximumStops = maximumStops;
		this.exactNumberOfStops = exactNumberOfStops;
		this.maximumDistance = maximumDistance;
		this.findRouteWithShortestDistance = findRouteWithShortestDistance;
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

	public boolean shouldFindRouteWithShortestDistance() {
		return findRouteWithShortestDistance;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[RouteSpec from=").append(this.fromTown)
			.append(" targetPath=").append(this.targetPath)
			.append(" maximumStops=").append(this.maximumStops)
			.append(" exactStops=").append(this.exactNumberOfStops)
			.append(" maximumDistance=").append(this.maximumDistance)
			.append(" findRouteWithShortestDistance=").append(this.findRouteWithShortestDistance)
			.append("]");
		return sb.toString();
	}
}
