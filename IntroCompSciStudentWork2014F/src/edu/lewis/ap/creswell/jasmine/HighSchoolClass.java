package edu.lewis.ap.creswell.jasmine;
import edu.jenks.dist.lewis.ap.*;
import java.util.*;
/**
 * Write a description of class HighSchoolClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HighSchoolClass extends AbstractHighSchoolClass
{
    public HighSchoolClass(Student[] students) {
        super(students);
    }
    public Student[] getValedictorian() {
        double highest=0;
        int index;
        List<Student> vale = new ArrayList<>();
        
        for (int i=0; i<students.length; i++) {
            if (students[i].getGpa() > highest) {
                
                highest=students[i].getGpa();
            }
        }
        for (int i=0; i<students.length; i++) {
            if (students[i].getGpa() == highest) {
                vale.add(students[i]);
            }
        }
      
        return vale.toArray(new Student[vale.size()]);
    }
    public double getHonorsPercent() {
        double honors=0.0;
        for (Student i : students) {
            if (i.isHonors()) {
                honors++;
            }
        }
        return ((honors/students.length)*100) ;
    }
    public double graduateWithHonorsPercent() {
        double honors=0.0;
        for (Student i : students) {
            if (i.getGpa()>=HONORS_GPA) {
                honors++;
            }
        }
        return ((honors/students.length)*100) ;
        
    }
    public double graduateWithHighestHonorsPercent() {
        double honors=0.0;
        for (Student i : students) {
            if (i.getGpa()>HIGHEST_HONORS_GPA) {
                honors++;
            }
        }
        return ((honors/students.length)*100) ;
        
    }
    public Student[] getHonorsStudents() {
        List<Student> hon = new ArrayList<>();
        for ( Student i : students) {
            if (i.isHonors()) {
            hon.add(i);
            }
        }    
        return hon.toArray(new Student[hon.size()]);
    }    
    
}
