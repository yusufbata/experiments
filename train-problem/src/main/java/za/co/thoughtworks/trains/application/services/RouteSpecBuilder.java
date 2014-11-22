package za.co.thoughtworks.trains.application.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import za.co.thoughtworks.trains.adapters.OutputMeasurement;


public class RouteSpecBuilder {

	private String fromTown;
	private List<String> targetPath = new ArrayList<String>();
	private int maximumStops;
	private int exactNumberOfStops;
	private int maximumDistance;
	private boolean findRouteWithShortestDistance;
	private OutputMeasurement outputMeasurement;
	
	public RouteSpec build() {
		return new RouteSpec(fromTown, targetPath, maximumStops, exactNumberOfStops, maximumDistance, findRouteWithShortestDistance, outputMeasurement);
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

	public RouteSpecBuilder with(OutputMeasurement outputMeasurement) {
		this.outputMeasurement = outputMeasurement;
		return this;
	}
}
