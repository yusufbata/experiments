package za.co.thoughtworks.trains;

import java.util.ArrayList;
import java.util.List;

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
		
		List<String> fileNames = new ArrayList<String>(2);
		
		TrackDescriptorList trackDescriptorList = TrackDescriptorList.constructFromFileWithName(fileNames.get(0));
		RailroadApplicationService railroadService = new RailroadApplicationService(trackDescriptorList);
		
		List<RouteSpec> routeSpecList = RouteSpec.constructFromFileWithName(fileNames.get(1));
		for (RouteSpec routeSpec : routeSpecList) {
			MatchingRoutes allMatchingRoutesForSpec = railroadService.findAllRoutesUsing(routeSpec);
			System.out.println(allMatchingRoutesForSpec);
		}
	}
}
