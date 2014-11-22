/**
 * 
 */
package za.co.thoughtworks.trains;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Yusuf
 *
 */
public class AppTest {

	@Test
	public void testApplicationWithSampleData() {
		String[] args = {"/sample-tracks.txt", "/sample-routespecs.txt"};
		App.main(args);
	}

}
