/**
 * 
 */
package za.co.thoughtworks.trains.adapters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.services.RailroadApplicationService;
import za.co.thoughtworks.trains.application.services.RouteSpec;
import za.co.thoughtworks.trains.application.services.TrackDescriptorList;

/**
 * @author F3657051
 *
 */
public class FileAdapter {

	public static RailroadApplicationService configureRailroadApplicationServiceWith(
			String trackDescriptorFileName) {
		List<String> lines = getAllNonEmptyAndNonCommentLinesFromFile(trackDescriptorFileName);
		TrackDescriptorList trackDescriptorList = new TrackDescriptorParser().constructTrackDescriptorListUsing(lines);
		RailroadApplicationService railroadService = new RailroadApplicationService(trackDescriptorList);
		return railroadService;
	}

	private static List<String> getAllNonEmptyAndNonCommentLinesFromFile(String trackDescriptorFileName) {
		List<String> lines = null;
		try {
			URL urlResource = FileAdapter.class.getResource(trackDescriptorFileName);
			File inputFile = new File(urlResource.toURI());
			final BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			lines = new ArrayList<String>();
	        while (reader.ready()) {
	        	String currentLine = reader.readLine().trim();
	        	if (!lineIsEmptyOrHasComments(currentLine)) {
	        		lines.add(currentLine);
				}
	        }
	        reader.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Failed to create file using path: " + trackDescriptorFileName, e);
		}
		return lines;
	}

	private static boolean lineIsEmptyOrHasComments(String currentLine) {
		if (currentLine.isEmpty() || currentLine.startsWith("#")) {
			return true;
		}
		return false;
	}

	public static List<RouteSpec> constructRouteSpecsFromFileWithName(
			String routeSpecsFileName) {
		List<String> lines = getAllNonEmptyAndNonCommentLinesFromFile(routeSpecsFileName);
		List<RouteSpec> routeSpecList = new RouteSpecParser().constructRouteSpecListUsing(lines);
		return routeSpecList;
	}

	

}
