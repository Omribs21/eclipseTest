package question1;

import application.InvalidAverageException;
import application.InvalidIdException;
import application.InvalidNameException;
import application.InvallidAgeException;

public class Student {
	private int id;
	private String name;
	private int age;
	private double average;
	public Student(int id, String name, int age, double average) throws InvalidNameException,InvalidIdException,InvalidAverageException,InvallidAgeException {
		super();
		setId(id);
		setName(name);
		setAge(age);
		setAverage(average);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) throws InvalidIdException {
		if (id < 0) {
			throw new InvalidIdException(id);
		}
			
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws InvalidNameException {
		if (name == null) {
			throw new InvalidNameException(name);
		}
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) throws InvallidAgeException{
		if(age <= 0) {
			throw new InvallidAgeException(age);
		}
		this.age = age;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) throws InvalidAverageException {
		if(average < 0 || average > 100) {
			throw new InvalidAverageException(average);
		}
		this.average = average;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", average=" + average + "]";
	}
	
}
