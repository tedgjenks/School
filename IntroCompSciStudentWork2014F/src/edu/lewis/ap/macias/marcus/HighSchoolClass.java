package edu.lewis.ap.macias.marcus;
//import edu.jenks.dist.lewis.ap;
import edu.jenks.dist.lewis.ap.AbstractHighSchoolClass;
import edu.jenks.dist.lewis.ap.Student;
import java.util.*;
public class HighSchoolClass extends AbstractHighSchoolClass {
	public int getNumHonorStud() {
		int answer = 0;
		Student[] arrayOfStudents = students;
		for(Student person: arrayOfStudents) {
			if(person.isHonors()) {
				answer++;
			}
		}
		return answer;
	}
	public HighSchoolClass(Student[] students) {
		super(students);
		
	}

	public static void main(String[] args) {
		Student person = new Student("Marcus","Macias",3.8,true);
		Student person1 = new Student("Don","Tran",4.0,true);
		Student person2 = new Student("Grif","Trif",4.0,true);
		Student person3 = new Student("Maddux","Rhodes",3.5,true);
		Student person4 = new Student("Brendan","Dianda",0.1,true);
		Student[] array = new Student[5];
		array[0] = person;
		array[1] = person1;
		array[2] = person2;
		array[3] = person3;
		array[4] = person4;
		
		
		
		HighSchoolClass run = new HighSchoolClass(array);
		
		System.out.println(run.getHonorsPercent());
		test(run.getHonorsStudents());
		test(run.getValedictorian());
		System.out.println(run.graduateWithHighestHonorsPercent());
		System.out.println(run.graduateWithHonorsPercent());

	}
	public static void test(Student[] x) {
		
		for(Student person : x) {
			System.out.print(person.getFirstName() + " ");
		}
		System.out.println("");
	}
	
	public double getHonorsPercent() {
		double count = students.length;
		double numOfHonors = 0;
		Student[] arrayOfStudents = students;
		for(Student person : arrayOfStudents) {
			if(person.isHonors()) {
				numOfHonors++;
			}
		}
		return (numOfHonors/count) * 100;
	}
	
	
	public Student[] getHonorsStudents() {
		int i = 0;
		//Student[] arrayOfStudents = students;
		Student[] answers = new Student[getNumHonorStud()];
		for(Student person: students) {
			if(person.isHonors()) {
				answers[i] = person;
				i++;
			}
			
		}
		
		return answers;
	}
	public int getLength(double GPA) {
		int answer = 0;
		for(Student person : students) {
			if(person.getGpa() == GPA) {
				answer++;
			}
		}
		
		
		return answer;
	}
	public Student[] getValedictorian() {
		int i = 0;
		
		double highestCurrentGPA = 0;
		for(Student person : students) {
			if(person.getGpa() >= highestCurrentGPA) {
				highestCurrentGPA = person.getGpa();
			}
		}
		Student[] answers = new Student[getLength(highestCurrentGPA)];
		for(Student person : students) {
			if(person.getGpa() == highestCurrentGPA) {
				answers[i] = person;
				i++;
			}
			
		}
		System.out.println("");
		
		return answers;
	}
	
	public double graduateWithHighestHonorsPercent() {
		double count = students.length;
		double numOfHonors = 0;
		Student[] arrayOfStudents = students;
		for(Student person : arrayOfStudents) {
			if(person.getGpa() >= HIGHEST_HONORS_GPA) {
				numOfHonors++;
			}
		}
		return (numOfHonors/count) * 100;
		
	}
	
	public int getNumHighHonorStud() {
		int answer = 0;
		Student[] arrayOfStudents = students;
		for(Student person: arrayOfStudents) {
			if(person.getGpa() >= HIGHEST_HONORS_GPA) {
				answer++;
			}
		}
		return answer;
	}
	
	public double graduateWithHonorsPercent() {
		double count = students.length;
		double numOfHonors = 0;
		Student[] arrayOfStudents = students;
		for(Student person : arrayOfStudents) {
			if(person.getGpa() >= HONORS_GPA) {
				numOfHonors++;
			}
		}
		return (numOfHonors/count) * 100;
		
	}

}
