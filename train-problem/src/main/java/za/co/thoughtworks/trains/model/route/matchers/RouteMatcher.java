package za.co.thoughtworks.trains.model.route.matchers;

import java.util.List;

import za.co.thoughtworks.trains.model.Location;
import za.co.thoughtworks.trains.model.Track;

public interface RouteMatcher<T> extends za.co.thoughtworks.trains.infrastructure.utils.Cloneable<T> {

	public boolean isRouteValid(List<Location> completedLocationList);
	public boolean isRouteComplete(List<Track> trackList);
}