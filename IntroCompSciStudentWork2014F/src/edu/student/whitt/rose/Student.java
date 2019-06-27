package edu.student.whitt.rose;

import edu.jenks.dist.student.*;
/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student extends AbstractStudent
{
    double scoreArray[];
    public static void main(String[] args) {
        System.out.println("test");
        Address ad = new Address("102 Sherwood Lane", "Greenwood", "South Carolina", 29649);
        Student pc = new Student("Rose", "Whitt", ad, ad, 90.0, 95.0, 100.0);
        //double ret = pc.getTestScore(0);
        //System.out.println(ret);
        System.out.println(pc.average());
        System.out.println(pc.toString());
    }
    public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3) {
        super(firstName, lastName, homeAddress, schoolAddress);  
        scoreArray = new double [3];
        scoreArray[0] = testScore1;
        scoreArray[1] = testScore2;
        scoreArray[2] = testScore3;
    }
    public void setTestScore(int testNumber, double score) {
        //int place = testNumber - 1;
        scoreArray[testNumber - 1] = score;
        //return ret;
    }
    public double getTestScore(int testNumber) {
        //int place = testNumber - 1;
        //return scoreArray[place];
        //return scoreArray[testNumber - 1];
        return scoreArray[testNumber - 1];
    }
    public double average() {
        double sum = scoreArray[0] + scoreArray[1] + scoreArray[2];
        return sum/3;
    }
    public String toString() {
        return getFirstName() + " " + getLastName()
        + NEW_LINE
        + "Home Address:"
        + NEW_LINE
        + getHomeAddress()
        + NEW_LINE
        + "School Address:"
        + NEW_LINE
        + getSchoolAddress()
        + NEW_LINE
        + "Test score 1: " + getTestScore(1)
        + NEW_LINE
        + "Test score 2: " + getTestScore(2)
        + NEW_LINE
        + "Test score 3: " + getTestScore(3)
        + NEW_LINE
        + "Average test score: " + average();
    }
    
}
