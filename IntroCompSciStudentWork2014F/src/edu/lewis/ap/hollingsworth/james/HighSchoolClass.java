package edu.lewis.ap.hollingsworth.james;

import edu.jenks.dist.lewis.ap.AbstractHighSchoolClass;
import edu.jenks.dist.lewis.ap.Student;

public class HighSchoolClass extends AbstractHighSchoolClass {

	public HighSchoolClass(Student[] students) {
		super(students);
	}

	public static void main(String[] args) {
		HighSchoolClass h = new HighSchoolClass(new Student[] {new Student("James", "Hollingsworth", 4.7, true), new Student("Chad", "Hollingsworth", 3.7, true)});
		System.out.println(h.getHonorsPercent());
		Student[] list = h.getHonorsStudents();
		for(Student s : list) System.out.print(s.getFirstName() + " " + s.getLastName() + ", ");
		System.out.println();
		list = h.getValedictorian();
		for(Student s : list) System.out.print(s.getFirstName() + " " + s.getLastName() + ", ");
		System.out.println();
		System.out.println(h.graduateWithHighestHonorsPercent());
		System.out.println(h.graduateWithHonorsPercent());
	}

	@Override
	public double getHonorsPercent() {
		double totalHonors = 0.0;
		for(Student s : students)
			if(s.isHonors()) totalHonors++;
		return totalHonors / students.length * 100;
	}

	@Override
	public Student[] getHonorsStudents() {
		int total = 0;
		for(Student s : students)	if(s.isHonors()) total++;
		
		Student[] hStudents = new Student[total];
		int index = 0;
		for(Student s : students)	if(s.isHonors()) hStudents[index++] = s;
		return hStudents;
	}

	@Override
	public Student[] getValedictorian() {
		int total = 0;
		double highest = -9999999;
		for(Student s : students) {
			if(s.getGpa() > highest) {
				total = 0;
				highest = s.getGpa();
			}
			if(s.getGpa() >= highest) total++;
		}
		
		Student[] hStudents = new Student[total];
		int index = 0;
		for(Student s : students)	if(s.getGpa() >= highest) hStudents[index++] = s;
		return hStudents;
	}

	@Override
	public double graduateWithHighestHonorsPercent() {
		double totalHonors = 0.0;
		for(Student s : students)
			if(s.getGpa() >= HIGHEST_HONORS_GPA) totalHonors++;
		return totalHonors / students.length * 100;
	}

	@Override
	public double graduateWithHonorsPercent() {
		double totalHonors = 0.0;
		for(Student s : students)
			if(s.getGpa() >= HONORS_GPA) totalHonors++;
		return totalHonors / students.length * 100;
	}

}
