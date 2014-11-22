package za.co.thoughtworks.trains.application.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RouteSpecBuilder {

	private String fromTown;
	private List<String> targetPath = new ArrayList<String>();
	private int maximumStops;
	private int exactNumberOfStops;
	private int maximumDistance;
	private boolean findRouteWithShortestDistance;
	
	public RouteSpec build() {
		return new RouteSpec(fromTown, targetPath, maximumStops, exactNumberOfStops, maximumDistance, findRouteWithShortestDistance);
	}

	public RouteSpecBuilder fromTown(String fromTown) {
		this.targetPath.add(0, fromTown);
		this.fromTown = fromTown;
		return this;
	}

	public RouteSpecBuilder toTown(String toTown) {
		this.targetPath.add(toTown);
		return this;
	}
	
	public RouteSpecBuilder withMaximumStops(int maximumStops) {
		this.maximumStops = maximumStops;
		return this;
	}
	
	public RouteSpecBuilder withExactNumberOfStops(int exactNumberOfStops) {
		this.exactNumberOfStops = exactNumberOfStops;
		return this;
	}

	public RouteSpecBuilder withMaximumDistance(int maximumDistance) {
		this.maximumDistance = maximumDistance;
		return this;
	}

	public RouteSpecBuilder withShortestDistance() {
		this.findRouteWithShortestDistance = true;
		return this;
	}
}
