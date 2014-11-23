package za.co.thoughtworks.trains.infrastructure.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	private ListUtils() {}

	public static <T extends Cloneable> List<T> cloneListWithContents(List<T> aListToClone) {
		List<T> clone = new ArrayList<T>(aListToClone.size());
		for (T track : aListToClone) {
			clone.add((T)track.clone());
		}
		return clone;
	}

	public static <T> T getLastItemFromList(List<T> list) {
		return list.get(list.size() - 1);
	}
}
