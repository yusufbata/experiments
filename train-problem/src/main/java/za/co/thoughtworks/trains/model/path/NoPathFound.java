package za.co.thoughtworks.trains.model.path;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.services.Distance;


public class NoPathFound extends MatchingPaths implements Path {

	protected NoPathFound(){};
	
	@Override
	public Distance getTotalDistance() {
		return Distance.infinite();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof NoPathFound) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 7;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[NoPathFound]");
		return sb.toString();
	}

	@Override
	public List<Path> findNextPossiblePaths() {
		return new ArrayList<Path>();
	}

	@Override
	public boolean hasPath(String routePath) {
		return false;
	}

	@Override
	public int getCurrentNumberOfStops() {
		return 0;
	}

	@Override
	public boolean hasEndLocationId(String lastLocationId) {
		return false;
	}

	@Override
	public boolean completedLocationListMatchesTargetPath(
			List<String> targetPath) {
		return false;
	}

	@Override
	public boolean isValid(List<Path> allCompletedRoutes) {
		return false;
	}

	@Override
	public boolean isComplete(List<Path> completedRoutes) {
		return true;
	}

	@Override
	public String getPrintString() {
		return "NO SUCH ROUTE";
	}
}
