package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.services.Distance;
import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.Track;

public class MaximumDistancePathMatcher  extends AbstractPathMatcher  
	implements PathMatcher<MaximumDistancePathMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<MaximumDistancePathMatcher> {

	private final int maximumDistance;

	public MaximumDistancePathMatcher(List<String> targetPath, int maximumDistance) {
		super(targetPath);
		this.maximumDistance = maximumDistance;
	}

	@Override
	public boolean isRouteValid(PathMatcherInput pathMatcherInput) {
		return pathMatcherInput.getRoute().getTotalDistance().value() <= maximumDistance;
	}

	@Override
	public boolean isRouteComplete(PathMatcherInput pathMatcherInput) {
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (pathMatcherInput.getRoute().getCurrentNumberOfStops() > 0) {
			return pathMatcherInput.getRoute().hasEndLocationId(lastLocationId);
		}
		return false;
	}

	@Override
	public MaximumDistancePathMatcher clone() {		
		return new MaximumDistancePathMatcher(new ArrayList<String>(this.targetPath), this.maximumDistance);
	}
}
