/**
 * 
 */
package za.co.thoughtworks.trains.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author F3657051
 *
 */
public class MatchingRoutes {

	private final List<Route> completedRoutes;

	protected MatchingRoutes(){
		completedRoutes = null;
	}
	
	public MatchingRoutes(List<Route> completedRoutes) {
		this.completedRoutes = completedRoutes;
	}

	public static MatchingRoutes construct(List<Route> completedRoutes) {
		return new MatchingRoutes(completedRoutes);
	}

	public IRoute getTheOnlyRoute() {
		if (this instanceof NoRoute) {
			return (NoRoute)this;
		}
		if (this.completedRoutes.size() != 1) {
			throw new RuntimeException("More than one route not expected!");
		}
		return this.completedRoutes.get(0);
	}

	public int getNumberOfRoutes() {
		return this.completedRoutes.size();
	}

	public Route get(int index) {
		return completedRoutes.get(index);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[MatchingRoutes ").append(this.completedRoutes)
			.append("]");
		return sb.toString();
	}
	
}
