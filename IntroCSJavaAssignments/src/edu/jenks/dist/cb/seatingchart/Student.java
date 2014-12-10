/**
 * 
 */
package edu.jenks.dist.cb.seatingchart;

/**
 * A student in a school is represented by an object of this class. 
 * @author JenksT
 *
 */
public class Student {
	private String name;
	private int absenceCount;

	/**
	 * @param name
	 * @param absenceCount
	 */
	public Student(String name, int absenceCount) {
		this.name = name;
		this.absenceCount = absenceCount;
	}
	
	/**
	 * Sets <code>absenceCount</code> to zero. 
	 * @param name
	 */
	public Student(String name) {
		this(name, 0);
	}
	
	/**
	 * @return the name of the student
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the absence count
	 */
	public int getAbsenceCount() {
		return absenceCount;
	}
}
