/**
 * 
 */
package edu.jenks.dist.lewis.ap;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractHighSchoolClass {
	
	/**
	 * Cutoff for graduating with honors
	 */
	public static final double HONORS_GPA = 3.5;
	
	/**
	 * Cutoff for graduating with highest honors
	 */
	public static final double HIGHEST_HONORS_GPA = 3.8;
		
	protected Student[] students;

	/**
	 * <b>precondition: </b>
	 *  - <code>students</code> is not null and has at least one element.<br>
	 *  - None of the elements are null
	 * @param students
	 */
	public AbstractHighSchoolClass(Student[] students) {
		this.students = students;
	}
	
	/**
	 * @return the <code>Student(s)</code> with the highest GPA
	 */
	public abstract Student[] getValedictorian();
	
	/**
	 * @return percent of students in the honors program
	 */
	public abstract double getHonorsPercent();
	
	/**
	 * @return percent of students with GPA greater than or equal to <code>HONORS_GPA</code>
	 */
	public abstract double graduateWithHonorsPercent();
	
	/**
	 * @return percent of students with GPA greater than or equal to <code>HIGHEST_HONORS_GPA</code>
	 */
	public abstract double graduateWithHighestHonorsPercent();
	
	/**
	 * @return students in the honors program
	 */
	public abstract Student[] getHonorsStudents();
}
