package za.co.thoughtworks.trains.model.path;

import java.util.List;

import za.co.thoughtworks.trains.application.Distance;

/**
 * Represents a directed path through nodes/locations.
 * 
 * @author Yusuf
 *
 */
public interface Path {

	Distance getTotalDistance();
	List<Path> findNextPossiblePaths();
	boolean hasPath(String routePath);
	int getCurrentNumberOfStops();
	boolean hasEndLocationId(String lastLocationId);
	boolean completedLocationListMatchesTargetPath(
			List<String> targetPath);
	boolean isValid(List<Path> allCompletedRoutes);
	boolean isComplete(List<Path> allCompletedRoutes);
	String getPrintString();
}