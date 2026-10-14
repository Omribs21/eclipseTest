package application;

public class InvalidAverageException extends Exception{
	public InvalidAverageException(double average) {
		super("invalid average, must be between 0-100: "+average);
	}
}
