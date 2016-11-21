/**
 * 
 */
package edu.jenks.dist.midterm.ics;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractMidterm {

	/**
	 * 
	 */
	public AbstractMidterm() {}
	
	/**
	 * <b>postcondition: </b>state is unchanged.
	 * @param name
	 * @param number
	 * @return <code>name</code> is ticket number <code>number</code>.
	 */
	public abstract String ticketMessage(String name, int number);
	
	/**
	 * <p>Assign a new ticket number every time the method is called.</p>
	 * The first call to this method will assign ticket number 1.<br>
	 * Every subsequent call to this method will increase the ticket number by 1.<br>
	 * <b>precondition: </b>the ticket number does not exceed the maximum int value.<br>
	 * <b>postcondition: </b>ticket number increases by one.
	 * @param name
	 * @return <code>name</code> is ticket number #.
	 */
	public abstract String ticketMessage(String name);
	
	/**
	 * <b>precondition: </b><code>lastNumber</code> is greater than 0.
	 * @param lastNumber
	 * @return the sum of the integers from 1 to <code>lastNumber</code>.
	 */
	public abstract int sum(int lastNumber);
	
	/**
	 * @param arg1
	 * @param arg2
	 * @return the larger of <code>arg1</code> and <code>arg2</code>. Return either if they are the same.
	 */
	public abstract int maxOfTwo(int arg1, int arg2);
	
	/**
	 * @param s
	 * @param sub
	 * @return the number of times <code>sub</code> is in <code>s</code>.
	 */
	public abstract int countSubString(String s, String sub);
	
	/**
	 * @param dividend
	 * @param divisor
	 * @return true if <code>dividend</code> is evenly divisible by <code>divisor</code>, otherwise false.
	 */
	public abstract boolean evenlyDivisible(int dividend, int divisor);
	
	/**
	 * @param d1
	 * @param d2
	 * @param delta
	 * @return true if <code>d1</code> and <code>d2</code> are no further apart than <code>delta</code>.
	 */
	public abstract boolean floatEquals(double d1, double d2, double delta);
	
	/**
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return true if any of the arguments are the same, false if none are the same.
	 */
	public abstract boolean isIsosceles(int s1, int s2, int s3);
	
	/**
	 * <p>Assign a letter grade based on <code>score</code>.
	 * Grades:<br>
	 * - <code>score</code> greater than or equal to 90 -> A<br>
	 * - <code>score</code> greater than or equal to 80, less than 90 -> B<br>
	 * - <code>score</code> greater than or equal to 70, less than 80 -> C<br>
	 * - <code>score</code> greater than or equal to 60, less than 70 -> D<br>
	 * - <code>score</code> less than 60 -> F
	 * @param score
	 * @return the grade.
	 */
	public abstract String assignGrade(double score);

}
