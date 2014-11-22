package za.co.thoughtworks.trains.model;

import java.util.List;

import za.co.thoughtworks.trains.application.Distance;


public class NoRoute extends MatchingRoutes implements Path {

	@Override
	public Distance getTotalDistance() {
		return Distance.infinite();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof NoRoute) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[NoRoute]");
		return sb.toString();
	}

	@Override
	public List<Route> findNextPossibleRoutes() {
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
}
