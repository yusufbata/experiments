package za.co.thoughtworks.trains.model;

import java.util.List;

import za.co.thoughtworks.trains.application.Distance;

public interface Path {

	public abstract Distance getTotalDistance();
	public abstract List<Route> findNextPossibleRoutes();
	public abstract boolean hasPath(String routePath);
	public abstract int getCurrentNumberOfStops();
	public abstract boolean hasEndLocationId(String lastLocationId);
	public abstract boolean completedLocationListMatchesTargetPath(
			List<String> targetPath);
	public abstract boolean isValid(List<Path> allCompletedRoutes);

}