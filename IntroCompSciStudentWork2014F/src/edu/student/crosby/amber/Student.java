package edu.student.crosby.amber;

import edu.jenks.dist.student.*;

public class Student extends AbstractStudent
{
    private double score1, score2, score3;
    public static void main(String[] args){
        
    }
    
    public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3){
        super(firstName, lastName, homeAddress, schoolAddress);
        score1 = testScore1;
        score2 = testScore2;
        score3 = testScore3;
    }
    
    public void setTestScore(int testNumber, double score){
        if(testNumber == 1){
            score1 = score;
        }
        if(testNumber == 2){
            score2 = score;
        }
        if(testNumber ==3){
            score3 = score;
        }
    }
    
    public double getTestScore(int testNumber){
        if(testNumber == 1){
            return score1;
        }
        if(testNumber == 2){
            return score2;
        }
        if(testNumber == 3){
            return score3;
        }
        return 0;
    }
    
    public double average(){
        double add = score1 + score2+ score3;
        double avg = add/3;
        return avg;
    }
    
    public String toString(){
        String name = getFirstName() + " " + getLastName() + NEW_LINE;
        String address = "Home Address:" + NEW_LINE + getHomeAddress() + NEW_LINE + "School Address:" + NEW_LINE + getSchoolAddress() + NEW_LINE;
        String testScores = "Test score 1: " + score1 + NEW_LINE + "Test score 2: " + score2 + NEW_LINE + "Test score 3: " + score3 + NEW_LINE;
        String average = "Average test score: " + average();
        return name + address + testScores + average;
    }
}
