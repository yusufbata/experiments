package za.co.thoughtworks.trains.model.path.matchers;


public interface PathMatcher<T> extends za.co.thoughtworks.trains.infrastructure.utils.Cloneable<T> {

	public boolean isRouteValid(PathMatcherInput pathMatcherInput);
	public boolean isRouteComplete(PathMatcherInput routeCompletionCheckInput);
}