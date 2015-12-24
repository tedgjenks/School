package edu.cb.seatingchart.jenks.ted.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.cb.seatingchart.jenks.ted.SeatingChart;
import edu.jenks.dist.cb.seatingchart.Student;

public class SeatingChartTest {
	
	Student[][] expSeats;
	Student karen = new Student("Karen", 3), liz = new Student("Liz", 1),
			paul = new Student("Paul", 4), lester = new Student("Lester", 1),
			henry = new Student("Henry", 5), renee = new Student("Renee", 9),
			glen = new Student("Glen", 2), fran = new Student("Fran", 6),
			david = new Student("David", 1), danny = new Student("Danny", 3);
	
	@Before
	public void setUp() throws Exception {
		Student[][] expSeats = {
				{karen, lester, glen, danny},
				{liz, henry, fran, null},
				{paul, renee, david, null}
		};
		this.expSeats = expSeats;
	}

	@Test
	public void testRemoveAbsentStudents() {
		Student[][] expSeatsAfterRemove = {
				{karen, lester, glen, danny},
				{liz, null, null, null},
				{paul, null, david, null}
		};
		SeatingChart seatingChart = new SeatingChart();
		seatingChart.setSeats(expSeats);
		int expRemoved = 3;
		int actRemoved = seatingChart.removeAbsentStudents(4);
		assertEquals(expRemoved, actRemoved);
		Student[][] actSeatsAfterRemove = seatingChart.getSeats();
		for(int rowIndex = 0; rowIndex < expSeatsAfterRemove.length; rowIndex++) {
			Student[] expStudents = expSeatsAfterRemove[rowIndex];
			Student[] actStudents = actSeatsAfterRemove[rowIndex];
			for(int colIndex = 0; colIndex < expStudents.length; colIndex++) {
				Student expStudent = expStudents[colIndex];
				Student actStudent = actStudents[colIndex];
				if(expStudent != null || actStudent != null) {
					assertEquals(expStudent, actStudent);
				}
			}
		}
	}

	@Test
	public void testSeatingChart() {
		List<Student> studentList = new ArrayList<Student>();
		studentList.addAll(Arrays.asList(karen, liz, paul, lester, henry,
				renee, glen, fran, david, danny));
		SeatingChart seatingChart = new SeatingChart(studentList, 3, 4);
		assertEquals(expSeats.length, seatingChart.getSeats().length);
		for(int index = 0; index < expSeats.length; index++) {
			Student[] expStudents = expSeats[index];
			Student[] actStudents = seatingChart.getSeats()[index];
			assertEquals(expStudents.length, actStudents.length);
			for(int colIndex = 0; colIndex < expStudents.length; colIndex++) {
				Student expStudent = expStudents[colIndex];
				Student actStudent = actStudents[colIndex];
				if(expStudent != null || actStudent != null) {
					assertEquals(expStudent.getName(), actStudent.getName());
					assertEquals(expStudent.getAbsenceCount(), actStudent.getAbsenceCount());
				}
			}
		}
	}
	

}
