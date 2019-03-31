package edu.lewis.ap.salter.bella;
import java.util.*;
import edu.jenks.dist.lewis.ap.*;
public class HighSchoolClass extends AbstractHighSchoolClass
{
    public HighSchoolClass(Student[] students)
    {
     super(students); 
    }
    public Student[] getValedictorian() {
        double baseGPA = students[0].getGpa(); 
        int countVal = 0;
        for(int i = 0; i < students.length; i++) {
            if(students[i].getGpa() > baseGPA) {
                baseGPA = students[i].getGpa();
            }
        }
        for(int c = 0; c < students.length; c++) {
            if(students[c].getGpa() == baseGPA) {
                countVal ++;
            }
        }
        Student[] Valedictorians = new Student[countVal];
        int numValInArr = 0;
        for(int e = 0; e < students.length; e++ ){
			if(students[e].getGpa() == baseGPA) {
            Valedictorians[numValInArr] = students[e];
            numValInArr++;
			}
        }
        return Valedictorians;
    }
    public double getHonorsPercent() {
        double countHonors = 0;
        for(int i = 0; i < students.length; i++) {
            if(students[i].isHonors()) {
                countHonors++;
            }
        }
        return ((double) countHonors / students.length) * 100;
    }
    public double graduateWithHonorsPercent() {
        double countHonors = 0;
        for(int i = 0; i < students.length; i++) {
            if(students[i].getGpa() >= HONORS_GPA) {
                countHonors ++;
            }
        }
        return ((double) countHonors / students.length) * 100;
    }
    public double graduateWithHighestHonorsPercent() {
        double countHighestHonors = 0;
        for(int i = 0; i < students.length; i++) {
            if(students[i].getGpa() >= HIGHEST_HONORS_GPA) {
                countHighestHonors ++;
            }
        }
        return ((double) countHighestHonors / students.length) * 100;
    }
    public Student[] getHonorsStudents() {
        Student[] honorsStudents = new Student[students.length];
        int countHonors = 0; 
        for(int i = 0; i < students.length; i++) {
            if(students[i].isHonors()) {
                honorsStudents[countHonors] = students[i];
				countHonors++;
            }
        }
        return honorsStudents;
    }
}
