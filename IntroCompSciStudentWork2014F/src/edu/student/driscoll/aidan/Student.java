package edu.student.driscoll.aidan;

import edu.jenks.dist.student.*;
/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student extends AbstractStudent
{
    public double testScore1;
    public double testScore2;
    public double testScore3;
    public static void main(String[] args){
        System.out.println("Begin");
        Address stud = new Address("211 Chatham Ct, ", "Greenwood, ", "SC ", 29649);
        Address school = new Address("1816 Cokesbury Rd, ", "Greenwood, ", "SC ", 29649);
        Student pc = new Student("Aidan ", "Driscoll", stud, school, 98, 95, 100);
        System.out.println(pc.getTestScore(1));
        System.out.println("End");
    }
    
    public Student(String firstName,String lastName, Address homeAddress,Address schoolAddress, double testScore1, double testScore2, double testScore3){
        super(firstName, lastName, homeAddress, schoolAddress);
        this.testScore1 = testScore1;
        this.testScore2 = testScore2;
        this.testScore3 = testScore3;
    }
    
    public double getTestScore(int testNumber){
       if(testNumber == 1){
           return testScore1;
       }
       if(testNumber == 2){
           return testScore2;
       }
       if(testNumber == 3){
           return testScore3;
       }
       return 0;
    } 
    
    public void setTestScore(int testNumber, double score){
        if(testNumber == 1){
            testScore1 = score;
        }
        if(testNumber == 2){
            testScore2 = score;
        }
        if(testNumber == 3){
            testScore3 = score;
        }
    }
    
    public double average(){
        double sum = testScore1 + testScore2 + testScore3;
        double avg = sum / 3;
        return avg;
    }
    
    public String toString(){
        String name = getFirstName() + " " + getLastName() + NEW_LINE;
        String addresses = "Home Address:" + NEW_LINE + getHomeAddress() + NEW_LINE + "School Address:" + NEW_LINE +  getSchoolAddress() + NEW_LINE;
        String testScores = "Test score 1: " + testScore1 + NEW_LINE + "Test score 2: " + testScore2 + NEW_LINE + "Test score 3: " + testScore3 + NEW_LINE;
        String average = "Average test score: " + average();
        return name + addresses + testScores + average;
    }
}
