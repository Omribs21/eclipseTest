package application;

public class InvalidIdException extends Exception {
	public InvalidIdException(double id) {
		super("invalid id, must be between bigger than 0:  "+id);
	}
}
