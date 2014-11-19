/**
 * 
 */
package za.co.thoughtworks.trains;

/**
 * @author Yusuf
 *
 */
public class Distance {

	private final long units;

	/**
	 * @param units
	 */
	private Distance(long units) {
		this.units = units;
	}

	public static Distance valueOf(long units) {
		return new Distance(units);
	}

}
