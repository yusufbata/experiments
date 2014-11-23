package za.co.thoughtworks.trains.application.services;

import java.util.List;

public interface Path {

	Distance getTotalDistance();
	List<Path> findNextPossibleRoutes();
	boolean hasPath(String routePath);
	int getCurrentNumberOfStops();
	boolean hasEndLocationId(String lastLocationId);
	boolean completedLocationListMatchesTargetPath(
			List<String> targetPath);
	boolean isValid(List<Path> allCompletedRoutes);
	boolean isComplete(List<Path> completedRoutes);
	String getPrintString();

}