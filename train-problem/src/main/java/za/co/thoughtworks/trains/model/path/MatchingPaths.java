/**
 * 
 */
package za.co.thoughtworks.trains.model.path;

import java.util.List;

import za.co.thoughtworks.trains.application.RouteSpec;

/**
 * Representing a collection of Paths.
 * Also has special path representing no matching paths (NoPathFound).
 * 
 * @author Yusuf
 *
 */
public class MatchingPaths {

	private final List<Path> completedRoutes;
	private static final NoPathFound NO_PATH_FOUND = new NoPathFound();

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
		if (this instanceof NoPathFound) {
			return (NoPathFound)this;
		}
		if (this.completedRoutes.size() != 1) {
			throw new NotExactlyOneResultException();
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
		return new NoPathFound();
	}
	
	public static NoPathFound noPathFound(){
		return NO_PATH_FOUND;
	}

	public Object getOutputMeasurement(RouteSpec routeSpec) {
		switch (routeSpec.getOutputMeasurement()) {
		case PathDistance:
			return this.findTheOnlyPath().getPrintString();
		case PathCount:
			return this.getNumberOfRoutes();
		default:
			System.err.println("Unknown OutputMeasurement: " + routeSpec.getOutputMeasurement());
			break;
		}
		return this.getNumberOfRoutes();
	}
	
}
