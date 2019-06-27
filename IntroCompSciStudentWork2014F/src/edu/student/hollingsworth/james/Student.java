package edu.student.hollingsworth.james;

import edu.jenks.dist.student.AbstractStudent;
import edu.jenks.dist.student.Address;

public class Student extends AbstractStudent {
    double[] grades = {0,0,0};
    public static void main(String[] args) {
        Student s = new Student("James", "Hollingsworth", new Address("3512 HWY 246 N", "Hodges", "SC", 29653), new Address("3492 Northside Dr", "Greenwood", "SC", 29648), 98.0, 80.0, 93.7);
        System.out.println(s.toString());
    }
    
    public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3) {
        super(firstName, lastName, homeAddress, schoolAddress);
        setTestScore(1, testScore1);
        setTestScore(2, testScore2);
        setTestScore(3, testScore3);
    }
    
    public void setTestScore(int testNumber, double score) {
        grades[testNumber - 1] = score;
    }
    
    public double getTestScore(int testNumber) {
        return grades[testNumber - 1];
    }
    
    public double average() {
        return (grades[0] + grades[1] + grades[2]) / 3;
    }
    
    public String toString() {
        return super.toString() + NEW_LINE + "Test score 1: " + getTestScore(1) + NEW_LINE + "Test score 2: " + getTestScore(2) + NEW_LINE + "Test score 3: " + getTestScore(3) + NEW_LINE + "Average test score: " + average();
    }
}
