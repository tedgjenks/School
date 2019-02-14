package edu.lewis.ap.burroughs.trent;
import java.util.*;
import edu.jenks.dist.lewis.ap.*;

public class HighSchoolClass extends AbstractHighSchoolClass
{

    public HighSchoolClass(Student[] students)
    {
        super(students);
    }

    public Student[] getValedictorian(){
        ArrayList<Student> vale = new ArrayList<>();
        Double high = 0.0;
        int num = 1;
        for(int i = 0; i < students.length; i++){
            if(students[i].getGpa() > high){
                high = students[i].getGpa();
            }
        }
        for(int o = 0; o < students.length; o++){
            if(students[o].getGpa() == high){
                vale.add(students[o]);
            }
        }
        return vale.toArray(new Student[vale.size()]);
    }
    
    public double getHonorsPercent(){
        Double percent = 0.0;
        for(int i = 0; i < students.length; i++){
            if(students[i].isHonors()){
                percent++;
            }
        }
        return (percent/students.length)*100;
    }
    
    public double graduateWithHonorsPercent(){
        Double percent = 0.0;
        for(int i = 0; i < students.length; i++){
            if(students[i].getGpa() >= HONORS_GPA){
                percent++;
            }
        }
        return (percent/students.length)*100;
    }
    
    public double graduateWithHighestHonorsPercent(){
        Double percent = 0.0;
        for(int i = 0; i < students.length; i++){
            if(students[i].getGpa() >= HIGHEST_HONORS_GPA){
                percent++;
            }
        }
        return (percent/students.length)*100;
    }
    
    public Student[] getHonorsStudents(){
        ArrayList<Student> honors = new ArrayList<>();
        for(int i = 0; i < students.length; i++){
            if(students[i].isHonors()){
                honors.add(students[i]);
            }
        }
        return honors.toArray(new Student[honors.size()]);
    }
}
