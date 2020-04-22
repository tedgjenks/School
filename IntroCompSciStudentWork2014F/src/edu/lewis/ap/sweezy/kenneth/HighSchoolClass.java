package edu.lewis.ap.sweezy.kenneth;

import java.util.ArrayList;

import edu.jenks.dist.lewis.ap.*;

public class HighSchoolClass extends AbstractHighSchoolClass {

	public static void main(String[] args) {
		Student testStudent1 = new Student("Denis1", "Savelyev", 1.67, false);
		Student testStudent2 = new Student("Javin1", "Page", 3.43, true);
		Student testStudent3 = new Student("Denis2", "Savelyev", 0.23, true);
		Student testStudent4 = new Student("Javin2", "Page", 6.65, true);
		Student testStudent5 = new Student("Denis3", "Savelyev", 1.2, false);
		Student testStudent6 = new Student("Javin3", "Page", 3.14, true);
		Student testStudent7 = new Student("Denis4", "Savelyev", 1.9, true);
		Student testStudent8 = new Student("Javin4", "Page", 4.20, true);
		Student testStudent9 = new Student("Denis5", "Savelyev", 1.99, false);
		Student testStudent10 = new Student("Javin4", "Page", 6.9, false);
		
		
		HighSchoolClass instance = new HighSchoolClass(new Student[] {testStudent1, testStudent2, testStudent3, testStudent4, testStudent5, testStudent6, testStudent7, testStudent8, testStudent9, testStudent10});
		System.out.println(instance.getValedictorian());
	}
	
	public HighSchoolClass(Student[] students) {
		super(students);
	}

	public double getHonorsPercent() {
		double numHonors = 0.0;
		for(int i = 0; i < students.length; i++) {
			if(students[i].isHonors()) { numHonors++; };
		}
		return (numHonors / students.length) * 100;
	}

	public Student[] getHonorsStudents() {
		ArrayList<Student> temp = new ArrayList<>();
		for(Student name : students) {
			 if(name.isHonors()) {
				 temp.add(name);
			 }
		}
		return (Student[])temp.toArray(new Student[0]);
	}

	public Student[] getValedictorian() {
		ArrayList<Student> temp = new ArrayList<>();
		double highestGPA = 0.0;
		for(Student name : students) {
			if(name.getGpa() >= highestGPA) { 
				highestGPA = name.getGpa();
			}
		}
		for(Student name : students) {
			if(name.getGpa() == highestGPA) {
				temp.add(name);
			}
		}
		return (Student[])temp.toArray(new Student[0]);
	}

	public double graduateWithHighestHonorsPercent() {
		double numGreaterThanHighestHonors = 0.0;
		for(int q = 0; q < students.length; q++) {
			if(students[q].getGpa() >= HIGHEST_HONORS_GPA) {
				numGreaterThanHighestHonors++;
			}
		}
		return (numGreaterThanHighestHonors / students.length) * 100;
	}

	public double graduateWithHonorsPercent() {
		double numGreaterThanHonors = 0.0                                   ;
		for(int k = 0; k < students.length; k++) {
			if(students[k].getGpa() >= HONORS_GPA) {
				numGreaterThanHonors++                                      ;
			}
		}
		return (numGreaterThanHonors / students.length) * 100               ;
	}
}