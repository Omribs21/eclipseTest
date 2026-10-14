package application;

public class InvallidAgeException extends Exception {
	public InvallidAgeException(int age) {
		super("invalid age, must be above 0"+age);
	}
}
