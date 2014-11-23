package za.co.thoughtworks.trains.model.path.matchers;

import java.util.List;

import za.co.thoughtworks.trains.infrastructure.utils.Cloneable;
import za.co.thoughtworks.trains.model.trackmaps.Location;
import za.co.thoughtworks.trains.model.trackmaps.Track;

public class AbstractPathMatcher {

	protected final List<String> targetPath;

	public AbstractPathMatcher(List<String> targetPath) {
		this.targetPath = targetPath;
	}
}