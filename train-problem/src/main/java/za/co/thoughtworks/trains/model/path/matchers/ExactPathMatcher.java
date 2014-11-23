package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.Track;

public class ExactPathMatcher  extends AbstractPathMatcher 
	implements PathMatcher<ExactPathMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ExactPathMatcher> {

	public ExactPathMatcher(List<String> targetPath) {
		super(targetPath);
	}

	@Override
	public boolean isRouteValid(PathMatcherInput pathMatcherInput) {
		if(numberOfHopsIsLessThanOrEqualToTargetPath(pathMatcherInput.getRoute().getCurrentNumberOfStops())
				&& pathMatcherInput.getRoute().completedLocationListMatchesTargetPath(this.targetPath))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isRouteComplete(PathMatcherInput pathMatcherInput) {
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (pathMatcherInput.getRoute().getCurrentNumberOfStops() > 0
				&& numberOfHopsIsSameAsTargetPath(pathMatcherInput.getRoute().getCurrentNumberOfStops())) {
			return pathMatcherInput.getRoute().hasEndLocationId(lastLocationId);
		}
		return false;
	}

	private boolean numberOfHopsIsSameAsTargetPath(int numberOfHops) {
		return this.targetPath.size() == (numberOfHops + 1);
	}
	
	private boolean numberOfHopsIsLessThanOrEqualToTargetPath(int numberOfHops) {
		return this.targetPath.size() >= (numberOfHops + 1);
	}

	@Override
	public ExactPathMatcher clone() {		
		return new ExactPathMatcher(new ArrayList<String>(this.targetPath));
	}
}
