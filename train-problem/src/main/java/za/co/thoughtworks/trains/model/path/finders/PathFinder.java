package za.co.thoughtworks.trains.model.path.finders;

import za.co.thoughtworks.trains.model.path.MatchingPaths;
import za.co.thoughtworks.trains.model.path.Path;
import za.co.thoughtworks.trains.model.path.matchers.RouteMatchers;

public interface PathFinder {

	MatchingPaths findPath(Path startingPath, RouteMatchers routeMatchers);

}