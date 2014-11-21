package za.co.thoughtworks.trains.application;

import java.util.ArrayList;
import java.util.List;


public class RouteSpecBuilder {

	private String fromTown;
	private List<String> targetPath = new ArrayList<String>();

	public RouteSpecBuilder fromTown(String fromTown) {
		this.targetPath.add(0, fromTown);
		this.fromTown = fromTown;
		return this;
	}

	public RouteSpecBuilder toTown(String toTown) {
		this.targetPath.add(toTown);
		return this;
	}

	public RouteSpec build() {
		return new RouteSpec(fromTown, targetPath);
	}

}
