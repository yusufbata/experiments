/**
 * 
 */
package za.co.thoughtworks.trains.model.path.finders;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.model.path.MatchingPaths;
import za.co.thoughtworks.trains.model.path.Path;
import za.co.thoughtworks.trains.model.path.matchers.PathMatchers;
import za.co.thoughtworks.trains.model.trackmaps.Location;


/**
 * Finds a path using the PathMatchers specified.
 * Steps through each node one step at a time starting from startingPath (node).
 * At each step evaluates next possible nodes for validity and completeness.
 * 
 * @author Yusuf
 *
 */
public class ExploratorySingleStepRoutingEngine implements PathFinder {

	public ExploratorySingleStepRoutingEngine() {}

	@Override
	public MatchingPaths findPath(Path startingPath, PathMatchers pathMatchers) {
		List<Path> completedRoutes = new ArrayList<Path>();
		List<Path> incompleteMatchingRoutes = new ArrayList<>();
		
		incompleteMatchingRoutes.add(startingPath);
		
		findAllValidRoutes(completedRoutes, incompleteMatchingRoutes);
		
		if (!completedRoutes.isEmpty()) {
			// required for shortest distance matcher
			// perhaps only run required matchers - will need flag to identify global matchers
			completedRoutes = validateAllCompletedRoutes(completedRoutes);
			return MatchingPaths.construct(completedRoutes);
		}
		
		return MatchingPaths.noPathFound();
	}

	private List<Path> validateAllCompletedRoutes(List<Path> allCompletedRoutes) {
		List<Path> validatedCompletedRoutes = new ArrayList<Path>();
		for (Path route : allCompletedRoutes) {
			if(route.isValid(allCompletedRoutes))
				validatedCompletedRoutes.add(route);
		}
		return validatedCompletedRoutes;
	}

	private void findAllValidRoutes(List<Path> completedRoutes,
			List<Path> previousIncompleteMatchingRoutes) {

		List<Path> newIncompleteMatchingRoutes = new ArrayList<Path>();
		for (Path potentialRoute : previousIncompleteMatchingRoutes) {
			if (potentialRoute.isValid(completedRoutes)) {
				if (potentialRoute.isComplete(completedRoutes)) {
					completedRoutes.add(potentialRoute);
				}

				List<Path> morePotentialRoutes = potentialRoute.findNextPossiblePaths();
				newIncompleteMatchingRoutes.addAll(morePotentialRoutes);
			}
		}
		if (!newIncompleteMatchingRoutes.isEmpty()) {
			findAllValidRoutes(completedRoutes, newIncompleteMatchingRoutes);
		}
	}

}
