package za.co.thoughtworks.trains.application.services;

import java.util.List;

public interface Path {

	public abstract Distance getTotalDistance();
	public abstract List<Path> findNextPossibleRoutes();
	public abstract boolean hasPath(String routePath);
	public abstract int getCurrentNumberOfStops();
	public abstract boolean hasEndLocationId(String lastLocationId);
	public abstract boolean completedLocationListMatchesTargetPath(
			List<String> targetPath);
	public abstract boolean isValid(List<Path> allCompletedRoutes);
	public abstract boolean isComplete(List<Path> completedRoutes);
	public abstract String getPrintString();

}