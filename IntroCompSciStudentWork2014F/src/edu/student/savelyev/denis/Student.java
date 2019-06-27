package edu.student.savelyev.denis;

import edu.jenks.dist.student.AbstractStudent;
import edu.jenks.dist.student.Address;

public class Student extends AbstractStudent {
    double[] scores = {-1, 0, 0, 0};
    double testScore1 = 0;
    double testScore2 = 0;
    double testScore3 = 0;
    public static void main(String[] args) {
        String streetAddress = "123 red st";
        String city = "Greenwood";
        String state = "South Carolina";
        int zipCode = 29649;
        String firstName = "Denis";
        String lastName = "Save";
        int testScore1 = 100;
        int testScore2 = 100;
        int testScore3 = 100;
        Address homeAddress = new Address(streetAddress, city, state, zipCode);
        Address schoolAddress = new Address(streetAddress, city, state, zipCode);
        Student instance = new Student(firstName, lastName, homeAddress, schoolAddress, testScore1, testScore2, testScore3);
        System.out.println();
        instance.setTestScore(1, 98);
        instance.setTestScore(2, 74);
        instance.setTestScore(3, 92);
        //System.out.println("Test Score 1: " + instance.getTestScore(1));
        //System.out.println("Test Score 2: " + instance.getTestScore(2));
        //System.out.println("Test Score 3: " + instance.getTestScore(3));
        //System.out.println(instance.average());
        System.out.println(instance.toString());
    }

    public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3) {
        super(firstName, lastName, homeAddress, schoolAddress);
        this.testScore1 = testScore1;
        this.testScore2 = testScore2;
        this.testScore3 = testScore3;
    }

    public void setTestScore(int testNumber, double score) {
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

    public double getTestScore(int testNumber) {
        switch(testNumber) {
        case 1:
            return testScore1;
        case 2:
            return testScore2;
        case 3:
            return testScore3;
        default:
            return 0;
        }
    }

    public double average() {
        return (testScore1 + testScore2 + testScore3) / 3;
    }

    public String toString() {
        return getFirstName() + " " + getLastName() + NEW_LINE + "Home Address:" + NEW_LINE + getHomeAddress().getStreetAddress() + NEW_LINE + getHomeAddress().getCity() + ", " + getHomeAddress().getState() + " " + getHomeAddress().getZipCode() + NEW_LINE + "School Address:" + NEW_LINE + getSchoolAddress().getStreetAddress() + NEW_LINE + getSchoolAddress().getCity() + ", " + getSchoolAddress().getState() + " " + getSchoolAddress().getZipCode() + NEW_LINE + "Test score 1: " + getTestScore(1) + NEW_LINE + "Test score 2: " + getTestScore(2) + NEW_LINE + "Test score 3: " + getTestScore(3) + NEW_LINE +"Average test score: " + average();
    }
}
