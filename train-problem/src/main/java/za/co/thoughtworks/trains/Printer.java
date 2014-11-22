package za.co.thoughtworks.trains;

import java.io.PrintStream;

public class Printer {

	private PrintStream printStream;
	
	public Printer() {
		printStream = System.out;
	}
	
	public Printer(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void printLine(Object object){
		printStream.println(object);
	}
}
