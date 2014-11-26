package edu.jenks.google.drive;

import org.jdom2.Element;

public class Student {
	private String firstName;
	private String lastName;
	private Element studentElement;

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Element getStudentElement() {
		return studentElement;
	}

	public void setStudentElement(Element studentElement) {
		this.studentElement = studentElement;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return toString().equals(arg0.toString());
	}
	
	@Override
	public String toString() {
		return lastName + firstName;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}
