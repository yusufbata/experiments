package za.co.thoughtworks.trains.model.path.finders;


/**
 * 
 * @author Yusuf
 *
 */
public class PathFinderFactory {

	public PathFinder constructPathFinder() {
		return new ExploratorySingleStepRoutingEngine();
	}

}