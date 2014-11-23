/**
 * 
 */
package za.co.thoughtworks.trains.application;

import java.util.List;

/**
 * Specifies the required route specifications.
 * TODO: Split into multiple enums instead, one per logical spec element. 
 * 
 * @author Yusuf
 *
 */
public class RouteSpec {

	private final String fromTown;
	private final List<String> targetPath;
	private final int maximumStops;
	private final int exactNumberOfStops;
	private final int maximumDistance;
	private final boolean findRouteWithShortestDistance;
	private final OutputMeasurement outputMeasurement;

	public RouteSpec(String fromTown, List<String> targetPath, int maximumStops, int exactNumberOfStops, int maximumDistance, boolean findRouteWithShortestDistance, OutputMeasurement outputMeasurement) {
		this.fromTown = fromTown;
		this.targetPath = targetPath;
		this.maximumStops = maximumStops;
		this.exactNumberOfStops = exactNumberOfStops;
		this.maximumDistance = maximumDistance;
		this.findRouteWithShortestDistance = findRouteWithShortestDistance;
		this.outputMeasurement = (outputMeasurement != null) ? outputMeasurement : OutputMeasurement.PathCount;
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
	
	public OutputMeasurement getOutputMeasurement() {
		return outputMeasurement;
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
