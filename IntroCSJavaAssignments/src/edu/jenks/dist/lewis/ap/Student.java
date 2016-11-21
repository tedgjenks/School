/**
 * 
 */
package edu.jenks.dist.lewis.ap;

/**
 * @author Ted Jenks
 *
 */
public class Student {
	private String firstName, lastName;
	private double gpa;
	private boolean honors;

	/**
	 * @param first
	 * @param last
	 * @param gpa
	 * @param honors true if in the honors program, false if not
	 */
	public Student(String first, String last, double gpa, boolean honors) {
		firstName = first;
		lastName = last;
		this.gpa = gpa;
		this.honors = honors;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * @param gpa
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * @return true if in the honors program, false if not
	 */
	public boolean isHonors() {
		return honors;
	}

	/**
	 * @param honors
	 */
	public void setHonors(boolean honors) {
		this.honors = honors;
	}
}
