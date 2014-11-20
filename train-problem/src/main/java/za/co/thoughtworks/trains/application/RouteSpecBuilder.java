package za.co.thoughtworks.trains.application;


public class RouteSpecBuilder {

	private String fromTown;
	private String toTown;

	public RouteSpecBuilder fromTown(String fromTown) {
		this.fromTown = fromTown;
		return this;
	}

	public RouteSpecBuilder toTown(String toTown) {
		this.toTown = toTown;
		return this;
	}

	public RouteSpec build() {
		return new RouteSpec(fromTown, toTown);
	}

}
