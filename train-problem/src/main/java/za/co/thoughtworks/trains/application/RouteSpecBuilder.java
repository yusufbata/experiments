package za.co.thoughtworks.trains.application;

import java.util.ArrayList;
import java.util.List;


public class RouteSpecBuilder {

	private String fromTown;
	private List<String> toTownList = new ArrayList<String>();

	public RouteSpecBuilder fromTown(String fromTown) {
		this.fromTown = fromTown;
		return this;
	}

	public RouteSpecBuilder toTown(String toTown) {
		this.toTownList.add(toTown);
		return this;
	}

	public RouteSpec build() {
		return new RouteSpec(fromTown, toTownList);
	}

}
