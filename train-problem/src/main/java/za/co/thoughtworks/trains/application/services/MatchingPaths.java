/**
 * 
 */
package za.co.thoughtworks.trains.application.services;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author F3657051
 *
 */
public class MatchingPaths {

	private final List<Path> completedRoutes;

	protected MatchingPaths(){
		completedRoutes = null;
	}
	
	public MatchingPaths(List<Path> completedRoutes) {
		this.completedRoutes = completedRoutes;
	}

	public static MatchingPaths construct(List<Path> completedRoutes) {
		return new MatchingPaths(completedRoutes);
	}

	public Path findTheOnlyPath() {
		if (this instanceof NoPath) {
			return (NoPath)this;
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
		sb.append("[MatchingPaths ").append(this.completedRoutes)
			.append("]");
		return sb.toString();
	}

	public Path findRouteWithPath(String routePath) {
		for (Path route : completedRoutes) {
			if (route.hasPath(routePath)) {
				return route;
			}
		}
		return new NoPath();
	}
	
}
