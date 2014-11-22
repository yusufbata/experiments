package za.co.thoughtworks.trains;

import java.util.Arrays;
import java.util.List;

import za.co.thoughtworks.trains.adapters.FileAdapter;
import za.co.thoughtworks.trains.application.BatchApplicationRunner;
import za.co.thoughtworks.trains.application.services.RailroadApplicationService;
import za.co.thoughtworks.trains.application.services.RouteSpec;

/**
 * @author Yusuf
 * 
 */
public class App {
	
	public static void main(String[] args) {
		boolean inputIsValid = validateInputArguments(args);
		if (!inputIsValid) {
			return;
		}
		
		String trackDescriptorFileName = args[0];
		String routeSpecsFileName = args[1];		
		runApplicationUsingInputFiles(trackDescriptorFileName, routeSpecsFileName);
	}

	private static boolean validateInputArguments(String[] args) {
		if (args.length != 2) {
			System.err.println("Expected exactly 2 input arguments but found " + args.length + ". Provided arguments=" + Arrays.asList(args));
			System.err.println("Please provide exactly 2 file names as input in the correct order. Example: /tracks.txt /routespecs.txt");
			System.err.println("You can also run the JUnit test [za.co.thoughtworks.trains.AppTest] to use the application with the default file names (including sample data). Sample files located in /src/main/resources/*");
			return false;
		}
		return true;
	}

	private static void runApplicationUsingInputFiles(String trackDescriptorFileName,
			String routeSpecsFileName) {
		try {
			FileAdapter fileAdapter = new FileAdapter();
			RailroadApplicationService railroadService = fileAdapter.configureRailroadApplicationServiceWith(trackDescriptorFileName);		
			List<RouteSpec> routeSpecList = fileAdapter.constructRouteSpecsFromFileWithName(routeSpecsFileName);
			BatchApplicationRunner runner = new BatchApplicationRunner(railroadService, routeSpecList);
			runner.run();
		} catch (Exception e) {
			System.err.println("Application failure occurred");
			e.printStackTrace();
		}
	}
}