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
import za.co.thoughtworks.trains.application.services.TrackDescriptor;
import za.co.thoughtworks.trains.application.services.TrackDescriptorBuilder;
import za.co.thoughtworks.trains.application.services.TrackDescriptorList;

/**
 * @author F3657051
 *
 */
public class FileAdapter {

	public static RailroadApplicationService configureRailroadApplicationServiceWith(
			String trackDescriptorFileName) {
		List<String> lines = getAllNonEmptyAndNonCommentLinesFromFile(trackDescriptorFileName);
		TrackDescriptorList trackDescriptorList = constructTrackDescriptorListUsing(lines);
		System.out.println("Constructed trackDescriptorList=" + trackDescriptorList);
		RailroadApplicationService railroadService = new RailroadApplicationService(trackDescriptorList);
		return railroadService;
	}

	private static TrackDescriptorList constructTrackDescriptorListUsing(
			List<String> lines) {
		TrackDescriptorList trackDescriptorList = new TrackDescriptorList();
		for (String line : lines) {
			TrackDescriptor trackDescriptor = TrackDescriptorBuilder.constructTrackDescriptorFromStringPattern(line);
			if (trackDescriptor != null) {
				trackDescriptorList.add(trackDescriptor);
			}
			else {
				System.err.println("Line doesn't conform to TrackDescriptor format [From-To-Distance]. Ignoring line [" + line + "]");
			}
		}
		return trackDescriptorList;
	}

	private static List<String> getAllNonEmptyAndNonCommentLinesFromFile(String trackDescriptorFileName) {
		List<String> lines = null;
		try {
//			trackDescriptorFileName = "/" + trackDescriptorFileName;
			// "/input.txt"
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
		printLinesFromFile(trackDescriptorFileName, lines);
		return lines;
	}

	private static void printLinesFromFile(String trackDescriptorFileName,
			List<String> lines) {
		System.out.println("Loading following lines from file: " + trackDescriptorFileName);
		for (String line : lines) {
			System.out.println(line);
		}
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
		System.out.println("Constructed routeSpecList=" + routeSpecList);
		return routeSpecList;
	}

	

}
