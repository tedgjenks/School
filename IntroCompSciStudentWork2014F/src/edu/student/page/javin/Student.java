package edu.student.page.javin;

import edu.jenks.dist.student.*;
/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student extends AbstractStudent
{
	private double testScore1 = 0.0;
	private double testScore2 = 0.0;
	private double testScore3 = 0.0;
	public static void main(String[] args) {
		String streetAddress = "123 Address St.";
		int zipCode = 12345;
		String city = "Idiotown";
		String state = "Oregon";
		String firstName = "John";
		String lastName = "Doe";
		Address homeAddress = new Address(streetAddress, city, state, zipCode);
		Address schoolAddress = new Address(streetAddress, city, state, zipCode);
		Student instance = new Student(firstName, lastName, homeAddress, schoolAddress, 0, 0, 0);
		instance.setTestScore(1, 45);
		instance.setTestScore(2, 98);
		instance.setTestScore(3, 73);
		System.out.println(instance.toString());
	}
    public Student(String firstName,String lastName, Address homeAddress, Address schoolAddress,double testScore1, double testScore2, double testScore3){
        super(firstName, lastName, homeAddress, schoolAddress);
        this.testScore1 = testScore1;
        this.testScore2 = testScore2;
        this.testScore3 = testScore3;
    }
    public void setTestScore(int testNumber, double score){
    	switch(testNumber) {
    		case 1:
    			testScore1 = score;
    			break;
    		case 2:
    			testScore2 = score;
    			break;
    		case 3:
    			testScore3 = score;
    			break;
    		default:
    			break;
    	}
    }
    public double getTestScore(int testNumber){
        switch(testNumber) {
        	case 1:
        		return testScore1;
        	case 2:
        		return testScore2;
        	case 3:
        		return testScore3;
        	default:
        		return 0.0;
        }
    }
    public double average(){
        return (testScore1 + testScore2 + testScore3) / 3;
    }
    public String toString(){
        return getFirstName() + " " + getLastName() + NEW_LINE + "Home Address:" + NEW_LINE + getHomeAddress() + NEW_LINE + "School Address:" + NEW_LINE + getSchoolAddress() + NEW_LINE + "Test score 1: " + getTestScore(1) + NEW_LINE + "Test score 2: " + getTestScore(2) + NEW_LINE + "Test score 3: " + getTestScore(3) + NEW_LINE + "Average test score: " + average();
    }
}
