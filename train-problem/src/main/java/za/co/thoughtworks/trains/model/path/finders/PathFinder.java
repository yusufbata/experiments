package za.co.thoughtworks.trains.model.path.finders;

import za.co.thoughtworks.trains.model.path.MatchingPaths;
import za.co.thoughtworks.trains.model.path.Path;
import za.co.thoughtworks.trains.model.path.matchers.PathMatchers;

public interface PathFinder {

	MatchingPaths findPath(Path startingPath, PathMatchers pathMatchers);

}