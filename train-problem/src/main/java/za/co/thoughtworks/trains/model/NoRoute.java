package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.Distance;


public class NoRoute extends MatchingRoutes implements IRoute {

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
}
