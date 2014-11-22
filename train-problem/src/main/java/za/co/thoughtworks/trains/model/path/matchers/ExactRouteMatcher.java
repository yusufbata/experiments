package za.co.thoughtworks.trains.model.path.matchers;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.ListUtils;
import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Track;

public class ExactRouteMatcher  extends AbstractRouteMatcher 
	implements RouteMatcher<ExactRouteMatcher>, za.co.thoughtworks.trains.infrastructure.utils.Cloneable<ExactRouteMatcher> {

	public ExactRouteMatcher(List<String> targetPath) {
		super(targetPath);
	}

	@Override
	public boolean isRouteValid(RouteMatcherInput routeMatcherInput) {
		if(numberOfHopsIsLessThanOrEqualToTargetPath(routeMatcherInput.getRoute().getCurrentNumberOfStops())
				&& routeMatcherInput.getRoute().completedLocationListMatchesTargetPath(this.targetPath))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isRouteComplete(RouteMatcherInput routeMatcherInput) {
		// TODO: Perhaps add isValid check here as well
		String lastLocationId = ListUtils.getLastItemFromList(targetPath);
		if (routeMatcherInput.getRoute().getCurrentNumberOfStops() > 0
				&& numberOfHopsIsSameAsTargetPath(routeMatcherInput.getRoute().getCurrentNumberOfStops())) {
			return routeMatcherInput.getRoute().hasEndLocationId(lastLocationId);
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
	public ExactRouteMatcher clone() {		
		return new ExactRouteMatcher(new ArrayList<String>(this.targetPath));
	}
}
