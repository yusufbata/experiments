package za.co.thoughtworks.trains.application;

public class InvalidConfigurationException extends RuntimeException {

	public InvalidConfigurationException(String message) {
		super(message);
	}
	
	public InvalidConfigurationException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	
}
