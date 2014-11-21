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

	private String fromTown;
	private List<String> toTownList;

	public RouteSpec(String fromTown, List<String> toTownList) {
		this.fromTown = fromTown;
		this.toTownList = toTownList;
	}

	public String getStartLocationId() {
		return fromTown;
	}

	public String getEndLocationId() {
		return this.toTownList.get(this.toTownList.size() - 1);
	}

	public List<String> getToTownList() {
		return toTownList;
	}

}
