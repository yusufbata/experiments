/**
 * 
 */
package za.co.thoughtworks.trains.application;

import java.util.List;

import za.co.thoughtworks.trains.application.services.MatchingPaths;
import za.co.thoughtworks.trains.application.services.RailroadApplicationService;
import za.co.thoughtworks.trains.application.services.RouteSpec;

/**
 * @author F3657051
 *
 */
public class BatchApplicationRunner {

	private final RailroadApplicationService railroadService;
	private final List<RouteSpec> routeSpecList;

	public BatchApplicationRunner(RailroadApplicationService railroadService,
			List<RouteSpec> routeSpecList) {
		this.railroadService = railroadService;
		this.routeSpecList = routeSpecList;
	}

	public void run() {
		for (RouteSpec routeSpec : routeSpecList) {
			MatchingPaths allMatchingRoutesForSpec = railroadService
					.findAllRoutesUsing(routeSpec);
			System.out.println(allMatchingRoutesForSpec);
		}
	}
}