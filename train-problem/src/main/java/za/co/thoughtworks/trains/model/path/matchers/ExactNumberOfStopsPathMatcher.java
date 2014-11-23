package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;

public class ExactNumberOfStopsPathMatcher extends AbstractPathMatcher 
	implements PathMatcher<ExactNumberOfStopsPathMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ExactNumberOfStopsPathMatcher> {

	private final int exactNumberOfStops;

	public ExactNumberOfStopsPathMatcher(List<String> targetPath, int exactNumberOfStops) {
		super(targetPath);
		this.exactNumberOfStops = exactNumberOfStops;
	}

	@Override
	public boolean isRouteValid(PathMatcherInput pathMatcherInput) {
		return pathMatcherInput.getRoute().getCurrentNumberOfStops() <= exactNumberOfStops;
	}

	@Override
	public boolean isRouteComplete(PathMatcherInput pathMatcherInput) {
		// TODO: Perhaps add isValid check here as well
		if (pathMatcherInput.getRoute().getCurrentNumberOfStops() == exactNumberOfStops) {
			String lastLocationId = ListUtils.getLastItemFromList(targetPath);
			if (pathMatcherInput.getRoute().getCurrentNumberOfStops() > 0) {
				return pathMatcherInput.getRoute().hasEndLocationId(lastLocationId);
			}
		}
		
		return false;
	}

	@Override
	public ExactNumberOfStopsPathMatcher clone() {		
		return new ExactNumberOfStopsPathMatcher(new ArrayList<String>(this.targetPath), this.exactNumberOfStops);
	}
}
