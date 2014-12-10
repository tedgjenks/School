/**
 * 
 */
package edu.jenks.dist.cb.seatingchart;

/**
 * @author JenksT
 *
 */
public abstract class AbstractSeatingChart {

	/** 
	 * <code>seats[r][c]</code> represents the Student in row r and column c in the classroom.
	 */ 
	protected Student[][] seats; 
	
	/**
	 * 
	 */
	public AbstractSeatingChart() {}

	/** 
	 * Removes students who have more than a given number of absences from the <br/>
	 * seating chart, replacing those entries in the seating chart with <code>null</code>
	 * and returns the number of students removed. <br/>
	 * Postcondition:<br/>
	 * - All students with <code>allowedAbsences</code> or fewer are in their original positions in <code>seats</code><br/>
	 * - No student in <code>seats</code> has more than <code>allowedAbsences</code> absences.<br/>
	 * - Entries without students contain <code>null</code>.
	 * @param allowedAbsences an integer >= 0
	 * @return number of students removed from <code>seats</code>
	 */ 
	public abstract int removeAbsentStudents(int allowedAbsences);
	
	public Student[][] getSeats() {
		return seats;
	}
	
	public void setSeats(Student[][] seats) {
		this.seats = seats;
	}
}
