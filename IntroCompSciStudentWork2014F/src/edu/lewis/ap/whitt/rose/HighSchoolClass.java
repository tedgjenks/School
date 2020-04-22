package edu.lewis.ap.whitt.rose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.jenks.dist.lewis.ap.AbstractHighSchoolClass;
import edu.jenks.dist.lewis.ap.Student;

public class HighSchoolClass extends AbstractHighSchoolClass {
	int numStudents = students.length;
	public HighSchoolClass(Student[] students) {
		super(students);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
	}
	
	public double getHighestGPA() {
		double[] studGPAs = new double[numStudents];
		for (int i = 0; i < numStudents; i++) {
			studGPAs[i] = students[i].getGpa();
		}
		Arrays.sort(studGPAs);
		
		return studGPAs[studGPAs.length - 1];
	}
	public Student[] getValedictorian() {
		double highestGPA = getHighestGPA();
		
		ArrayList<Student> valsList = new ArrayList<Student>();
		for (int i = 0; i < students.length; i++) {
			if (students[i].getGpa() == highestGPA) {
				valsList.add(students[i]);
			}
		}
		
		Student[] valsArray = (Student[]) valsList.toArray(new Student[] {});
		return valsArray;
//		Student curVal = students[0];
//		for (int i = 0; i < numStudents; i++) {
//			if (students[i].getGpa() > curVal.getGpa()) {
//				curVal = students[i];
//			}
//		}
//		Student[] test = {curVal};
//		return test;
//		
//		double[] studGPAs = new double[numStudents];
//		for (int i = 0; i < numStudents; i++) {
//			studGPAs[i] = students[i].getGpa();
//		}
//		Arrays.sort(studGPAs);
//		//find highest gpa and then search all students for that gpa
	}
	
	public double getHonorsPercent() {
		Student[] honorsStudentsArray = getHonorsStudents();
		int numHonorsStudents = honorsStudentsArray.length;
		return ((double) numHonorsStudents * 100) / (double) numStudents;
	}
	
	public double graduateWithHonorsPercent() {
		ArrayList<Student> honorsStudentsList = new ArrayList<Student>();
		for (int i = 0; i < numStudents; i++) {
			if (students[i].getGpa() >= HONORS_GPA) {
				honorsStudentsList.add(students[i]);
			}
		}
		Student[] honorsStudentsArray = (Student[]) honorsStudentsList.toArray(new Student[] {});
		int numGradHonors = honorsStudentsArray.length;
		
		return ((double) numGradHonors * 100) / (double) numStudents;
	}
	
	public double graduateWithHighestHonorsPercent() {
		ArrayList<Student> honorsStudentsList = new ArrayList<Student>();
		for (int i = 0; i < numStudents; i++) {
			if (students[i].getGpa() >= HIGHEST_HONORS_GPA) {
				honorsStudentsList.add(students[i]);
			}
		}
		Student[] honorsStudentsArray = (Student[]) honorsStudentsList.toArray(new Student[] {});
		int numGradHonors = honorsStudentsArray.length;
		
		return ((double) numGradHonors * 100) / (double) numStudents;
	}
	
	public Student[] getHonorsStudents() {
		ArrayList<Student> honorsStudentsList = new ArrayList<Student>();
		for (int i = 0; i < numStudents; i++) {
			if (students[i].isHonors()) {
				honorsStudentsList.add(students[i]);
			}
		}
		Student[] honorsStudentsArray = (Student[]) honorsStudentsList.toArray(new Student[] {});
		return honorsStudentsArray;

	}
}
