/**
 * 
 */
package edu.jenks.dist.student;

import edu.jenks.util.ComparisonUtil;
import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
/**
 * <p><b>Data:</b> first name, last name, home address, school address</p>
 * @author jenkst
 *
 */
public abstract class AbstractStudent {
	private String firstName, lastName;
	private Address homeAddress, schoolAddress;
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param homeAddress
	 * @param schoolAddress
	 */
	public AbstractStudent(String firstName, String lastName, Address homeAddress, Address schoolAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeAddress = homeAddress;
		this.schoolAddress = schoolAddress;
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

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(Address schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null || !getClass().getSuperclass().equals(arg0.getClass().getSuperclass()))
			return false;
		AbstractStudent arg = (AbstractStudent)arg0;
		return StringUtil.equalAllowNull(firstName, arg.firstName) &&
				StringUtil.equalAllowNull(lastName, arg.lastName) &&
				ComparisonUtil.equalAllowNull(homeAddress, arg.homeAddress) &&
				ComparisonUtil.equalAllowNull(schoolAddress, arg.schoolAddress);
	}

	/**
	 * @param testNumber (1, 2, or 3)
	 * @param score percent scored
	 */
	public abstract void setTestScore(int testNumber, double score);
	
	/**
	 * @param testNumber (1, 2, or 3)
	 * @return score as a percent
	 */
	public abstract double getTestScore(int testNumber);
	
	/**
	 * @return average of all 3 test scores
	 */
	public abstract double average();
	
	/**
	 * <code>firstName</code> <code>lastName</code><br>
	 * Home Address:<br>
	 * <code>homeAddress</code><br>
	 * School Address:<br>
	 * <code>schoolAddress</code>
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder(75);
		sb.append(firstName).append(" ").append(lastName).append("\n");
		sb.append("Home Address:\n").append(homeAddress).append("\n");
		sb.append("School Address:\n").append(schoolAddress);
		return sb.toString();
	}
}
