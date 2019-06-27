package edu.student.tran.don;

import edu.jenks.dist.student.*; 

public class Student extends AbstractStudent
{   
    private double score1;
    private double score2;
    private double score3;
    private double averaged;

    //public static final String NEW_LINE;
    public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3) {
        super(firstName, lastName, homeAddress, schoolAddress);
        score1 = testScore1;
        score2 = testScore2;
        score3 = testScore3;
        averaged = average();
    }

    //double[] storedTest = new double[]{testScore1, testScore2, testScore3};
    public void setTestScore(int testNumber, double score) {
        if(testNumber == 1) {
            double testScore1 = score;
        }
        if(testNumber == 2) {
            double testScore2 = score;
        }
        if(testNumber == 3) {
            double testScore3 = score;
        }
    }
    public double getTestScore(int testNumber) {
        if(testNumber == 1) {
            return score1;
        }
        if(testNumber == 2) {
            return score2;
        }
        if(testNumber == 3) {
            return score3; 
        }
        return score1;
    }
    public double average() {
        return (score1 + score2 + score3) / 3;
    }
    public String toString() {
        //return "";
        return getFirstName() + " " + getLastName() + NEW_LINE + "Home Address:" + NEW_LINE + getHomeAddress() + NEW_LINE + "School Address:" + NEW_LINE + getSchoolAddress() + NEW_LINE + "Test score 1: " + score1 + NEW_LINE + "Test score 2: " + score2 + NEW_LINE + "Test score 3: " + score3 + NEW_LINE + "Average test score: " + averaged;
    }
    
}
























    /*
    public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3) ;
    public void setTestScore(int testNumber, double score) {
    }
    public double getTestScore(int testNumber) {
    }
    public double average() {
    }
    public String toString() {
    }
    */