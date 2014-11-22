package za.co.thoughtworks.trains.application;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import za.co.thoughtworks.trains.application.services.Distance;

public class DistanceTest {

	@Test
	public void testAdd() {
		Distance distanceValue5 = Distance.valueOf(5);
		Distance distanceValue2 = Distance.valueOf(2);
		Distance totalDistance = distanceValue2.add(distanceValue5);
		assertThat(totalDistance).isNotNull();
		assertThat(totalDistance).isEqualTo(Distance.valueOf(7));
	}

}
