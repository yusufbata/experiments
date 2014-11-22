package za.co.thoughtworks.trains.model.path.matchers;


public interface RouteMatcher<T> extends za.co.thoughtworks.trains.infrastructure.utils.Cloneable<T> {

	public boolean isRouteValid(RouteMatcherInput routeMatcherInput);
	public boolean isRouteComplete(RouteMatcherInput routeCompletionCheckInput);
}