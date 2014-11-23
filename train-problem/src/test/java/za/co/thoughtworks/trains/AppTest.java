/**
 * 
 */
package za.co.thoughtworks.trains;

import java.io.File;

import org.junit.Test;

/**
 * @author Yusuf
 *
 */
public class AppTest {

	@Test
	public void testApplicationWithSampleData() {
		String[] args = {"src/main/resources/sample-tracks.txt", "src/main/resources/sample-routespecs.txt"};
		App.main(args);
	}
}
