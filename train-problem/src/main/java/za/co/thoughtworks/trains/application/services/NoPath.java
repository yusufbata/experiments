package za.co.thoughtworks.trains.application.services;

import java.util.List;


public class NoPath extends MatchingPaths implements Path {

	@Override
	public Distance getTotalDistance() {
		return Distance.infinite();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof NoPath) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[NoPath]");
		return sb.toString();
	}

	@Override
	public List<Path> findNextPossibleRoutes() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}
}
