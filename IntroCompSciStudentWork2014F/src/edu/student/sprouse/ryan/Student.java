package edu.student.sprouse.ryan;

import edu.jenks.dist.student.*;

public class Student extends AbstractStudent
{
    double scoreOne = 0;
    double scoreTwo = 0;
    double scoreThree = 0;
    public Student(String firstName,String lastName,Address homeAddress,Address schoolAddress,double testScore1,double testScore2,double testScore3){
        super(firstName, lastName, homeAddress, schoolAddress);
        scoreOne = testScore1;
        scoreTwo = testScore2;
        scoreThree = testScore3;
    }
    public void setTestScore(int testNumber, double score){
        if(testNumber == 1){
            scoreOne = score;
        }
        if(testNumber == 2){
            scoreTwo = score;
        }
        if(testNumber == 3){
            scoreThree = score;
        }
    }
    public double getTestScore(int testNumber){
        if(testNumber == 1){
            return scoreOne;
        }
        if(testNumber == 2){
            return scoreTwo;
        }
        if(testNumber == 3){
            return scoreThree;
        }
        return 0;
    }
    public double average(){
        double total = (scoreOne + scoreTwo + scoreThree);
        return (total / 3);
    }
    public String toString(){
        String name = getFirstName() + " " + getLastName() + NEW_LINE;
        String homeAddress = "Home Address:" + NEW_LINE + getHomeAddress() + NEW_LINE;
        String schoolAddress = "School Address:" + NEW_LINE + getSchoolAddress() + NEW_LINE;
        String scores = "Test score 1: " + getTestScore(1) + NEW_LINE + "Test score 2: " + getTestScore(2) + NEW_LINE + "Test score 3: " + getTestScore(3) + NEW_LINE;
        String averageScore = "Average test score: " + average();
        return name + homeAddress + schoolAddress + scores + averageScore;
    }
}
