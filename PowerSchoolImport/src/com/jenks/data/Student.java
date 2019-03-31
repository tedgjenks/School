/**
 * 
 */
package com.jenks.data;

/**
 * @author Jenks
 *
 */
public class Student implements Comparable<Student> {
	
	private String nameLastCommaFirst = "";
	private String lastName = "";
	private String firstName = "";
	private long number;
	
	public Student() {}
	
	public Student(String nameLastCommaFirst, long number) {
		setNameLastCommaFirst(nameLastCommaFirst);
		this.number = number;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the name
	 */
	public String getNameLastCommaFirst() {
		return nameLastCommaFirst;
	}
	/**
	 * @param name the name to set
	 */
	public void setNameLastCommaFirst(String name) {
		this.nameLastCommaFirst = name;
		String[] names = nameLastCommaFirst.split(",");
		setLastName(names[0]);
		if(names.length > 1)
			setFirstName(names[1]);
	}
	
	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Student arg = (Student)obj;
		return lastName.equals(arg.lastName) && firstName.equals(arg.firstName);
	}

	@Override
	public int compareTo(Student o) {
		String oLast = o.getLastName();
		String oFirst = o.getFirstName();
		return lastName.equals(oLast) ? firstName.compareTo(oFirst) : lastName.compareTo(oLast);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(firstName).append(" ").append(lastName);
		if(number > 0)
			sb.append("; ").append(number);
		return sb.toString();
	}
}
