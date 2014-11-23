/**
 * 
 */
package za.co.thoughtworks.trains.adapters;

import java.util.List;

import za.co.thoughtworks.trains.application.Distance;
import za.co.thoughtworks.trains.model.trackmaps.TrackDescriptor;
import za.co.thoughtworks.trains.model.trackmaps.TrackDescriptorList;

/**
 * constructs TrackDescriptor from line of text.
 * 
 * @author Yusuf
 *
 */
public class TrackDescriptorParser {

	public TrackDescriptorList constructTrackDescriptorListUsing(
			List<String> lines) {
		TrackDescriptorList trackDescriptorList = new TrackDescriptorList();
		for (String line : lines) {
			constructTrackDescriptorAndAddToList(trackDescriptorList, line);
		}
		return trackDescriptorList;
	}

	private void constructTrackDescriptorAndAddToList(
			TrackDescriptorList trackDescriptorList, String line) {
		TrackDescriptor trackDescriptor = constructTrackDescriptorFromStringPattern(line);
		if (trackDescriptor != null) {
			trackDescriptorList.add(trackDescriptor);
		}
		else {
			System.err.println("Line doesn't conform to TrackDescriptor format [FromLocation-ToLocation-DistanceInteger]. Ignoring line [" + line + "]");
		}
	}
	
	/**
	 * Expected pattern: From-To-DistanceInteger
	 * Expected pattern example: A-B-4
	 * Items separated using '-'
	 * 
	 * @param stringPattern
	 * @return
	 */
	public TrackDescriptor constructTrackDescriptorFromStringPattern(
			String stringPattern) {
		stringPattern = stringPattern.trim();
		String[] elements = stringPattern.split("-");
		
		if (elements.length != 3) {
			return null;
		}
		String fromLocation = elements[0];
		String toLocation = elements[1];
		String distanceString = elements[2];
		
		Integer distance = convertStringToInt(distanceString);
		if (distance == null) {
			return null;
		}
		return new TrackDescriptor(fromLocation, toLocation, Distance.valueOf(distance));
	}

	private Integer convertStringToInt(String string) {
		Integer distance = null;
		try {
			distance = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			System.err.println("Failed to convert string to integer: " + string);
			System.err.println(e);
		}
		return distance;
	}
}
