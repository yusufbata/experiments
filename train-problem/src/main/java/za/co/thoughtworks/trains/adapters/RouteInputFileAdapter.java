/**
 * 
 */
package za.co.thoughtworks.trains.adapters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import za.co.thoughtworks.trains.application.InvalidConfigurationException;
import za.co.thoughtworks.trains.application.RouteSpec;
import za.co.thoughtworks.trains.application.services.RailroadApplicationService;
import za.co.thoughtworks.trains.model.trackmaps.TrackDescriptorList;

/**
 * Used for providing input to the application via files.
 * Loads track configuration information from one file, and route spec from another file.
 * 
 * @author Yusuf
 *
 */
public class RouteInputFileAdapter {

	public RailroadApplicationService configureRailroadApplicationServiceWith(
			String trackDescriptorFileName) {
		List<String> lines = getAllNonEmptyAndNonCommentLinesFromFile(trackDescriptorFileName);
		TrackDescriptorList trackDescriptorList = new TrackDescriptorParser().constructTrackDescriptorListUsing(lines);
		RailroadApplicationService railroadService = new RailroadApplicationService(trackDescriptorList);
		return railroadService;
	}

	private List<String> getAllNonEmptyAndNonCommentLinesFromFile(String trackDescriptorFileName) {
		List<String> lines = null;
		try {
			File inputFile = getFile(trackDescriptorFileName);
			lines = readLinesFromFile(inputFile);
		} catch (Exception e) {
			throw new InvalidConfigurationException("Failed to create file using path: " + trackDescriptorFileName, e);
		}
		return lines;
	}

	private File getFile(String fileName)
			throws URISyntaxException {
		File inputFile = new File(fileName);
		return inputFile;
	}

	private List<String> readLinesFromFile(File inputFile)
			throws FileNotFoundException, IOException {
		List<String> lines = new ArrayList<String>();
		final BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		try {
			while (reader.ready()) {
				addLineToListIfValid(lines, reader);
			}
		} finally {
			reader.close();
		}
		return lines;
	}

	private void addLineToListIfValid(List<String> lines,
			final BufferedReader reader) throws IOException {
		String currentLine = reader.readLine().trim();
		if (!lineIsEmptyOrHasComments(currentLine)) {
			lines.add(currentLine);
		}
	}

	private boolean lineIsEmptyOrHasComments(String currentLine) {
		if (currentLine.isEmpty() || currentLine.startsWith("#")) {
			return true;
		}
		return false;
	}

	public List<RouteSpec> constructRouteSpecsFromFileWithName(
			String routeSpecsFileName) {
		List<String> lines = getAllNonEmptyAndNonCommentLinesFromFile(routeSpecsFileName);
		List<RouteSpec> routeSpecList = new RouteSpecParser().constructRouteSpecListUsing(lines);
		return routeSpecList;
	}
}
