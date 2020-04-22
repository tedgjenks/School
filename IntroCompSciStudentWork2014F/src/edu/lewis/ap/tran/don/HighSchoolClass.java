package edu.lewis.ap.tran.don;


import edu.jenks.dist.lewis.ap.*;
import edu.jenks.dist.lewis.ap.Student;
import java.util.*;

public class HighSchoolClass extends AbstractHighSchoolClass {
	public Student[] allStudents;

	public static void main(String[] args) {
		//System.out.println(HONORS_GPA);
		ArrayList<Student> doo = new ArrayList();
		doo.add(new Student("dan", "tran", 4.0, true));
		doo.add(new Student("Griff", "nad", 3.8, false));
		Student[] stud = new Student[doo.size()];
		for (int i = 0; i < stud.length; i++) {
			stud[i] = doo.get(i);
		}
		HighSchoolClass what = new HighSchoolClass(stud);
		System.out.println(what.getValedictorian()[0].getFirstName());
		System.out.println(what.getValedictorian().length);
		System.out.println(what.getHonorsStudents()[0].getFirstName());
		System.out.println(what.getHonorsStudents().length);
		System.out.println(what.getHonorsPercent());
		System.out.println(what.graduateWithHighestHonorsPercent());
	}

	public HighSchoolClass(Student[] students) {
		super(students);
		allStudents = students;
	}

	public double getHonorsPercent() {
		//System.out.println(getHonorsStudents().length + "   dad    " + allStudents.length);
		//System.out.println(((double)getHonorsStudents().length / (double)allStudents.length) * 100.0);
		return ((double)getHonorsStudents().length / (double)allStudents.length) * 100.0;
	}

	public Student[] getHonorsStudents() {
		ArrayList<Student> honorStuds = new ArrayList();
		for(int i = 0; i < allStudents.length; i++) {
			if(allStudents[i].isHonors()) {
				honorStuds.add(allStudents[i]);
			}
		}
		Student[] stud = new Student[honorStuds.size()];
		for (int i = 0; i < stud.length; i++) {
			stud[i] = honorStuds.get(i);
		}
		return stud;
	}

	public Student[] getValedictorian() {
		ArrayList<Student> valedStuds = new ArrayList();
		if (allStudents.length == 0) {
			return (Student[]) valedStuds.toArray();
		}
		double maxGPA = allStudents[0].getGpa();
		for (int i = 0; i < allStudents.length; i++) {
			if (allStudents[i].getGpa() > maxGPA) {
				maxGPA = allStudents[i].getGpa();
			}
		}
		for (int i = 0; i < allStudents.length; i++) {
			if (allStudents[i].getGpa() == maxGPA) {
				valedStuds.add(allStudents[i]);
			}
		}
		Student[] stud = new Student[valedStuds.size()];
		for (int i = 0; i < stud.length; i++) {
			stud[i] = valedStuds.get(i);
		}
		return stud;
	}

	public double graduateWithHighestHonorsPercent() {
		int highHonStuds = 0;
		for(int i = 0; i < allStudents.length; i++) {
			if(allStudents[i].getGpa() >= HIGHEST_HONORS_GPA) {
				highHonStuds++;
			}
		}
		
		return ((double) highHonStuds / (double) allStudents.length) * 100.0;
	}

	public double graduateWithHonorsPercent() {
		int highHonStuds = 0;
		for(int i = 0; i < allStudents.length; i++) {
			if(allStudents[i].getGpa() >= HONORS_GPA) {
				highHonStuds++;
			}
		}
		
		return ((double) highHonStuds / (double) allStudents.length) * 100.0;
	}
}
