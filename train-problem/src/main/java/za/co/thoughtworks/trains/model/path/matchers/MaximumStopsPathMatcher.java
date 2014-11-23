package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.Track;

public class MaximumStopsPathMatcher  extends AbstractPathMatcher  
	implements PathMatcher<MaximumStopsPathMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<MaximumStopsPathMatcher> {

	private final int maximumStops;

	public MaximumStopsPathMatcher(List<String> targetPath, int maximumStops) {
		super(targetPath);
		this.maximumStops = maximumStops;
	}

	@Override
	public boolean isRouteValid(PathMatcherInput pathMatcherInput) {
		return pathMatcherInput.getRoute().getCurrentNumberOfStops() <= maximumStops;
	}

	@Override
	public boolean isRouteComplete(PathMatcherInput pathMatcherInput) {
		// TODO: Perhaps add isValid check here as well
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (pathMatcherInput.getRoute().getCurrentNumberOfStops() > 0) {
			return pathMatcherInput.getRoute().hasEndLocationId(lastLocationId);
		}
		return false;
	}

	@Override
	public MaximumStopsPathMatcher clone() {		
		return new MaximumStopsPathMatcher(new ArrayList<String>(this.targetPath), this.maximumStops);
	}
}
