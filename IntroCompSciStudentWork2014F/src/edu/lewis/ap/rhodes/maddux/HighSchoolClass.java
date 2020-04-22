package edu.lewis.ap.rhodes.maddux;
import edu.jenks.dist.lewis.ap.AbstractHighSchoolClass;
import edu.jenks.dist.lewis.ap.Student;
import java.util.*;

public class HighSchoolClass extends AbstractHighSchoolClass {
	
	public HighSchoolClass(Student[] students) {
		super(students);
	}
	
	public static void main(String[] args) {
		Student meanie = new Student("Maddux", "Rhodes", 4.0, true);
		Student meanie2 = new Student("Ascascs", "Sacacs", 3.9, false);
		Student[] arrStud = new Student[2];
		arrStud[0] = meanie;
		arrStud[1] = meanie2;
		HighSchoolClass test = new HighSchoolClass(arrStud);
		test.getHonorsPercent();
	}
	
	public double getHonorsPercent() {
		double numHonorsStudents = 0;
		double wholeSchool = students.length;
		for(int i = 0; i < wholeSchool; i++) {
			if(students[i].isHonors()) {
				numHonorsStudents++;
			}
		}
		return (numHonorsStudents/wholeSchool)*100;
	}

	public Student[] getHonorsStudents() {
		ArrayList<Student> honorsStudents = new ArrayList();
		for(int i = 0; i < students.length; i++) {
			if(students[i].isHonors()) {
				honorsStudents.add(students[i]);
			}
		}
		Student[] blah = honorsStudents.toArray(new Student[] {});
		return blah;
	}

	public Student[] getValedictorian() {
		double gpaTemp = 0;
		ArrayList<Student> valedictorianArrList = new ArrayList();
		for(int i = 0; i < students.length; i++) {
			if(students[i].getGpa() >= gpaTemp) {
				gpaTemp = students[i].getGpa();
			}
		}
		for(int i = 0; i < students.length; i++) {
			if(students[i].getGpa() == gpaTemp) {
				valedictorianArrList.add(students[i]);
			}
		}
		Student[] valedictorainArr = valedictorianArrList.toArray(new Student[] {});
		System.out.println(valedictorainArr[0].getFirstName());
		return valedictorainArr;
	}

	public double graduateWithHighestHonorsPercent() {
		double count = 0;
		for(int i = 0; i < students.length; i++) {
			if(students[i].getGpa() >= HIGHEST_HONORS_GPA) {
				count++;
			}
		}
		return (count/(double)students.length)*100;
	}

	public double graduateWithHonorsPercent() {
		double count = 0;
		for(int i = 0; i < students.length; i++) {
			if(students[i].getGpa() >= HONORS_GPA) {
				count++;
			}
		}
		return (count/(double)students.length)*100;	
	}
}
