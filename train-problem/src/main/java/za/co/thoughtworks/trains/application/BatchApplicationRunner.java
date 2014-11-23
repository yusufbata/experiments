/**
 * 
 */
package za.co.thoughtworks.trains.application;

import java.util.List;

import za.co.thoughtworks.trains.Printer;
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
	private Printer printer;

	public BatchApplicationRunner(RailroadApplicationService railroadService,
			List<RouteSpec> routeSpecList) {
		this(railroadService, routeSpecList, new Printer());
	}
	
	public BatchApplicationRunner(RailroadApplicationService railroadService,
			List<RouteSpec> routeSpecList, Printer printer) {
		this.railroadService = railroadService;
		this.routeSpecList = routeSpecList;
		this.printer = printer;
	}

	public void run() {
		for (int index = 0; index < routeSpecList.size(); index++) {
			RouteSpec routeSpec = routeSpecList.get(index);
			MatchingPaths allMatchingRoutesForSpec = railroadService.findAllRoutesUsing(routeSpec);
			printOutputForResult(index, routeSpec, allMatchingRoutesForSpec);
		}
	}

	private void printOutputForResult(int index, RouteSpec routeSpec,
			MatchingPaths allMatchingRoutesForSpec) {
		String outputMessage = String.format("Output #%s: %s", index + 1, allMatchingRoutesForSpec.getOutputMeasurement(routeSpec));
		printer.printLine(outputMessage);
	}
}