package edu.lewis.ap.gabriel.mitchell;
import edu.jenks.dist.lewis.ap.*;
import java.util.*;
public class HighSchoolClass extends AbstractHighSchoolClass
{
    public HighSchoolClass(Student[] students){
        super(students);
    }
    public Student[] getValedictorian(){
        List<Student> geniusBar = new ArrayList<Student>();
        geniusBar.add(students[0]);
        for (int i = 1; i < students.length;i++){
            if (students[i].getGpa() == geniusBar.get(0).getGpa()){
                geniusBar.add(students[i]);
            } else if (students[i].getGpa() > geniusBar.get(0).getGpa()){
                geniusBar.clear();
                geniusBar.add(students[i]);
            }
        }
        Student[] rickAndMortyViewers = new Student[geniusBar.size()];
        for (Student i: geniusBar){
            for (int z = 0; z <rickAndMortyViewers.length;z++){
                rickAndMortyViewers[z] = i;
            }
        }
        return rickAndMortyViewers;
    }
    public double getHonorsPercent(){
        double hon = 0;
        int totStudents = students.length;
        for (Student i : students){
            if (i.isHonors()){
                hon++;
            }
        }
        return (hon/totStudents)*100;
    }
    public double graduateWithHonorsPercent(){
        double hon = 0;
        int totStudents = students.length;
        for (Student i : students){
            if (i.getGpa()>=HONORS_GPA){
                hon++;
            }
        }
        return (hon/totStudents)*100;
    }
    public double graduateWithHighestHonorsPercent(){
        double hiHon = 0;
        int totStudents = students.length;
        for (Student i : students){
            if (i.getGpa()>=HIGHEST_HONORS_GPA){
                hiHon++;
            }
        }
        return (hiHon/totStudents)*100;
    }
    public Student[] getHonorsStudents(){
        List<Student> smartyBois = new ArrayList<Student>();
        for (Student i : students){
            if (i.isHonors()){
                smartyBois.add(i);
            }
        }
        Student[] highIq = new Student[smartyBois.size()];
        for (Student i: smartyBois){
            for (int z = 0; z < highIq.length;z++){
                highIq[z] = i;
            }
        }
        return highIq;
    }
}