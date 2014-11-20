/**
 * 
 */
package za.co.thoughtworks.trains.application;

/**
 * @author Yusuf
 *
 */
public class RouteSpec {

	private String fromTown;
	private String toTown;

	public RouteSpec(String fromTown, String toTown) {
		this.fromTown = fromTown;
		this.toTown = toTown;
	}

	public String getStartLocationId() {
		return fromTown;
	}

	public String getEndLocationId() {
		return this.toTown;
	}

}
