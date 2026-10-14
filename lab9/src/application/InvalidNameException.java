package application;

public class InvalidNameException extends Exception {
	public InvalidNameException(String name) {
		super("invalid name, name cant be null: "+name);
	}
}
