/**
 * 
 */
package za.co.thoughtworks.trains.application;

/**
 * A value type representing distance. 
 * 
 * @author Yusuf
 *
 */
public class Distance {

	private final int units;

	/**
	 * @param units
	 */
	private Distance(int units) {
		this.units = units;
	}

	public static Distance valueOf(int units) {
		return new Distance(units);
	}
	
	@Override
	public int hashCode() {
		return 31 * (31 + this.units);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Distance) {
			Distance other = (Distance)obj;
			if (this.units == other.units) {
				return true;
			}
		}
		return false;
	}

	public static Distance infinite() {
		return new Distance(Integer.MAX_VALUE);
	}

	public Distance add(Distance otherDistance) {
		int newValue = this.units + otherDistance.units;
		return Distance.valueOf(newValue);
	}

	public int value() {
		return this.units;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Distance=").append(this.units).append("]");
		return sb.toString();
	}
}
