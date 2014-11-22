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

	private final List<Path> completedRoutes;

	protected MatchingRoutes(){
		completedRoutes = null;
	}
	
	public MatchingRoutes(List<Path> completedRoutes) {
		this.completedRoutes = completedRoutes;
	}

	public static MatchingRoutes construct(List<Path> completedRoutes) {
		return new MatchingRoutes(completedRoutes);
	}

	public Path getTheOnlyRoute() {
		if (this instanceof NoRoute) {
			return (NoRoute)this;
		}
		if (this.completedRoutes.size() != 1) {
			throw new RuntimeException("More than one route not expected!");
		}
		return this.completedRoutes.get(0);
	}

	public int getNumberOfRoutes() {
		return (this.completedRoutes != null) ? this.completedRoutes.size() : 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[MatchingRoutes ").append(this.completedRoutes)
			.append("]");
		return sb.toString();
	}

	public Path findRouteWithPath(String routePath) {
		for (Path route : completedRoutes) {
			if (route.hasPath(routePath)) {
				return route;
			}
		}
		return new NoRoute();
	}
	
}
