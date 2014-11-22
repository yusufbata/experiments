/**
 * 
 */
package za.co.thoughtworks.trains.adapters;

import static za.co.thoughtworks.trains.application.BuilderFactory.aRouteSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import za.co.thoughtworks.trains.application.services.RouteSpec;
import za.co.thoughtworks.trains.application.services.RouteSpecBuilder;

/**
 * @author F3657051
 *
 */
public class RouteSpecParser {

	/**
	 * Format example: EXACT_PATH/START_AND_END = A-B-C-D | MAXHOPS/EXACTSTOPS/MAXDISTANCE/NONE = 3 | NONE/SHORTEST_DISTANCE |  DISTANCE/PATH_COUNT
	 * 
	 * Element separators '|' . Note that they can't be used for values.
	 * Exactly one item required per element (NONE allowed if not required).
	 * Exact number of elements (4) required in every line, including NONE if element not required.
	 * Key value separators '='. Values ignored when NONE specified.
	 * 
	 * @param line
	 * @return
	 */
	protected RouteSpec constructRouteSpecFromStringPattern(
			String stringPattern) {
		stringPattern = stringPattern.trim().toUpperCase();

		// Escape required for PIPE character
		String[] elements = stringPattern.split("\\|");
		
		if (elements.length != 4) {
			System.err.println("Line does not have EXACTLY 4 elements. Ignoring: " + stringPattern 
					+ ". Elements=" + Arrays.asList(elements));
			return null;
		}
		
		String pathElement = elements[0].trim();
		String measureElement = elements[1].trim();
		String pathFilterElement = elements[2].trim();
		String outputElement = elements[3].trim();
		
		if (elementsAreEmptyOrNone(pathElement, outputElement)) {
			System.err.println("Path and Output elements cannot be empty or NONE. Ignoring: " + stringPattern);
			return null;
		}
		if (measureElement.isEmpty() || pathFilterElement.isEmpty()) {
			System.err.println("Measure and Path Filter elements cannot be empty. Ignoring: " + stringPattern);
			return null;
		}
		
		String pathElementKey = getKeyFromElement(pathElement);
		String pathElementValue = getValueFromElement(pathElement);
		
		String measureElementKey = getKeyFromElement(measureElement);
		String measureElementValue = getKeyFromElement(measureElement);

		String[] pathElementValueItems = pathElementValue.split("-");
		
		if (pathElementValueItems.length < 2) {
			System.err.println("Path elements must be at least 2 [A-B]. Ignoring: " + stringPattern);
			return null;
		}
		
		RouteSpecBuilder aRouteSpec = aRouteSpec().fromTown(pathElementValueItems[0]);
		for (int i = 1; i < pathElementValueItems.length; i++) {
			aRouteSpec.toTown(pathElementValueItems[i]);
		}
		
//		RouteSpec routeSpec = 
				//new RouteSpec(fromTown, targetPath, maximumStops, exactNumberOfStops, maximumDistance, findRouteWithShortestDistance);
//				null;
		return aRouteSpec.build();
	}
	
	public List<RouteSpec> constructRouteSpecListUsing(
			List<String> lines) {
		List<RouteSpec> routeSpecList = new ArrayList<RouteSpec>();
		for (String line : lines) {
			RouteSpec routeSpec = constructRouteSpecFromStringPattern(line);
			if (routeSpec != null) {
				routeSpecList.add(routeSpec);
			}
			else {
				System.err.println("Line doesn't conform to RouteSpec format [XXX]. Ignoring line [" + line + "]");
			}
		}
		return routeSpecList;
	}

	private boolean elementsAreEmptyOrNone(String ... elements) {
		for (String element : elements) {
			if (element.isEmpty() || element.contains("NONE")) {
				return true;
			}
		}
		return false;
	}
	
	private String getKeyFromElement(String element) {
		return getItemFromElementWithIndex(element, 0, "=", 2);
	}

	private String getValueFromElement(String element) {
		return getItemFromElementWithIndex(element, 1, "=", 2);
	}

	private String[] getItemsFromElementBySplitting(String element, String separator) {
		String[] items = element.split("=");
		if (items.length != 2) {
			System.err.println("Key value item doesn't have correct structure X = Y. Ignoring: " + element);
			return null;
		}
		return items;
	}

	private String getItemFromElementWithIndex(String element, int index, String separator, int exactSize) {
		String[] items = getItemsFromElementBySplitting(element, separator);
		if (items != null) {
			if (exactSize == 0) {
				return items[index].trim();
			}
			else if(exactSize > 0){
				if (items.length == exactSize) {
					return items[index].trim();
				}
			}
		}
		return null;
	}
	
	/*privateString getItemFromElementWithIndex(String element, int index, String separator) {
		return getItemFromElementWithIndex(element, index, separator, 0);
	}*/

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