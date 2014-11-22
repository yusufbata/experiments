package za.co.thoughtworks.trains;

import java.io.PrintStream;

public class Printer {

	private static PrintStream printStream = System.out;
	
	public static void printLine(Object object){
		printStream.println(object);
	}
}
