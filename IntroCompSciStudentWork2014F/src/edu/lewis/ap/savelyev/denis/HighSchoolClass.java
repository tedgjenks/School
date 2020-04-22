package edu.lewis.ap.savelyev.denis;

import java.util.ArrayList;

import edu.jenks.dist.lewis.ap.*;

public class HighSchoolClass extends AbstractHighSchoolClass{
	
	public static void main (String[] args) {
		Student test1 = new Student("1first", "1last", 6.1, true);
		Student test2 = new Student("2first", "2last", 1.2, true);
		Student test3 = new Student("3first", "3last", 4.0, false);
		Student test4 = new Student("4first", "4last", 5.3, true);
		Student test5 = new Student("5first", "5last", 3.2, false);
		
		HighSchoolClass instance = new HighSchoolClass(new Student[]{test1, test2, test3, test4, test5});
		System.out.println(instance.graduateWithHonorsPercent());
	}
	
	public HighSchoolClass(Student[] students) {
		super(students);
	}

	public double getHonorsPercent() {
		double numHonors = 0.0;
		for (int x = 0; x < students.length; x++) {
			if (students[x].isHonors()) {
				numHonors++;
			}
		}
		return (numHonors / students.length) * 100;
	}

	public Student[] getHonorsStudents() {
		ArrayList<Student> arr = new ArrayList<> ();
		for(Student name : students) {
			if (name.isHonors() == true) {
				arr.add(name);
			}
		}
		return arr.toArray(new Student[0]);
	}

	public Student[] getValedictorian() {
		ArrayList<Student> arr = new ArrayList<> ();
		double highestGpa = 0.0;
		for(Student name : students) {
			if (name.getGpa() >= highestGpa) {
				highestGpa = name.getGpa();
			}
		}
		for(Student name : students) {
			if (name.getGpa() == highestGpa) {
				arr.add(name);
			}
		}
		return arr.toArray(new Student[0]);
	}

	public double graduateWithHighestHonorsPercent() {
		double count = 0;
		for(Student name : students) {
			if (name.getGpa() >= HIGHEST_HONORS_GPA) {
				count++;
			}
		}
		return ((double)count / (double)students.length) * 100;
	}

	public double graduateWithHonorsPercent() {
		double count = 0;
		for(Student name : students) {
			if (name.getGpa() >= HONORS_GPA) {
				count++;
			}
		}
		return ((double)count / (double)students.length) * 100;
	}
	
}
