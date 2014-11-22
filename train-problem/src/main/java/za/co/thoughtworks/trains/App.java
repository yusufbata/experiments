package za.co.thoughtworks.trains;

import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.adapters.FileAdapter;
import za.co.thoughtworks.trains.application.RailroadApplicationService;
import za.co.thoughtworks.trains.application.RouteSpec;
import za.co.thoughtworks.trains.application.TrackDescriptorList;
import za.co.thoughtworks.trains.model.MatchingRoutes;

/**
 * @author Yusuf
 * 
 */
public class App {
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.err.println("Please provide exactly 2 file names as input. Example: tracks.txt routespecs.txt");
		}
		
		String trackDescriptorFileName = args[0];
		String routeSpecsFileName = args[1];
		
		runApplicationUsingInputFiles(trackDescriptorFileName, routeSpecsFileName);
	}

	private static void runApplicationUsingInputFiles(String trackDescriptorFileName,
			String routeSpecsFileName) {
		RailroadApplicationService railroadService = FileAdapter.configureRailroadApplicationServiceWith(trackDescriptorFileName);		
		List<RouteSpec> routeSpecList = FileAdapter.constructRouteSpecsFromFileWithName(routeSpecsFileName);
		
		for (RouteSpec routeSpec : routeSpecList) {
			MatchingRoutes allMatchingRoutesForSpec = railroadService.findAllRoutesUsing(routeSpec);
			System.out.println(allMatchingRoutesForSpec);
		}
	}
}
