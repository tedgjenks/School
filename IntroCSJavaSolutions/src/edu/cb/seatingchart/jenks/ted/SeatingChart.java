/**
 * 
 */
package edu.cb.seatingchart.jenks.ted;

import java.util.List;

import edu.jenks.dist.cb.seatingchart.AbstractSeatingChart;
import edu.jenks.dist.cb.seatingchart.Student;

/**
 * Use a two-dimensional array to represent the seating arrangement of students in a classroom. <br/>
 * The seats in the classroom are in a rectangular arrangement of rows and columns.
 * @author JenksT
 *
 */
public class SeatingChart extends AbstractSeatingChart {

	public SeatingChart() {
		
	}

	/** 
	 * Creates a seating chart with the given number of rows and columns from the students in <code>studentList</code>. <br/>
	 * Empty seats in the seating chart are represented by <code>null</code>. <br/>
	 * Precondition:  <code>rows</code> > 0; <code>cols</code> > 0; <br/>
	 * <code>rows * cols >= studentList.size()</code> <br/>
	 * Postcondition:<br/>
	 * - Students appear in the seating chart in the same order as they appear<br/>
	 * in <code>studentList</code>, starting at <code>seats[0][0]</code>.  <br/>
	 * - <code>seats</code> is filled column by column from <code>studentList</code>, followed by any<br/>
	 * empty seats (represented by <code>null</code>). <br/>
	 * - <code>studentList</code> is unchanged.
	 * @param rows the number of rows of seats in the classroom
	 * @param cols the number of columns of seats in the classroom
	 */ 
	public SeatingChart(List<Student> studentList, int rows, int cols) {
		int studentListIndex = 0;
		seats = new Student[rows][cols];
		for(int colIndex= 0; colIndex < cols && studentListIndex < studentList.size(); colIndex++) {
			for(int rowIndex = 0; rowIndex < rows && studentListIndex < studentList.size(); rowIndex++) {
				seats[rowIndex][colIndex] = studentList.get(studentListIndex++);
			}
		}
	}

	/**
	 * @see edu.jenks.dist.cb.seatingchart.AbstractSeatingChart#removeAbsentStudents(int)
	 */
	@Override
	public int removeAbsentStudents(int allowedAbsences) {
		int studentsRemoved = 0;
		for(int rowIndex = 0; rowIndex < seats.length; rowIndex++) {
			Student[] students = seats[rowIndex];
			for(int colIndex = 0; colIndex < students.length; colIndex++) {
				Student student = students[colIndex];
				if(student != null && student.getAbsenceCount() > allowedAbsences) {
					students[colIndex] = null;
					studentsRemoved++;
				}
			}
		}
		return studentsRemoved;
	}
}
