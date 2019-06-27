package edu.student.macias.marcus;

import edu.jenks.dist.student.*;

public class Student extends AbstractStudent
{
    double score1 ;
    double score2 ;
    double score3 ;
    public Student(String firstName,String lastName,Address homeAddress,Address schoolAddress,double testScore1,double testScore2,double testScore3){
        super(firstName,lastName,homeAddress,schoolAddress);
        score1 = testScore1;
        score2 = testScore2;
        score3 = testScore3;
    }
    public static void main(String[] args){
        String answer = "Test score 1:";
        answer += NEW_LINE;
        answer += 100;
        answer += NEW_LINE;
        answer += "Test score 2:";
        answer += NEW_LINE;
        answer += 92;
        answer += NEW_LINE;
        answer += "Test score 3:";
        answer += NEW_LINE;
        answer += 92;
        answer += NEW_LINE;
        answer += "Average test score:";
        answer += NEW_LINE;
        answer += 94.7;
        System.out.println(answer);
    }
    public void setTestScore(int testNumber,double score){
        if(testNumber == 1){
            double testScore1 = score;
        }
        if(testNumber == 2){
            double testScore2 = score;
        }
        if(testNumber == 3){
            double testScore3 = score;
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
        return score1;
    }
    public double average(){
        double average = (score1 + score2 + score3) / 3;
        return average;
    }
    public String toString(){
        String answer = getFirstName() + " " + getLastName();
        answer += NEW_LINE;
        answer += "Home Address:";
        answer += NEW_LINE;
        answer += getHomeAddress();
        answer += NEW_LINE;
        answer += "School Address:";
        answer += NEW_LINE;
        answer += getSchoolAddress();
        answer += NEW_LINE;
        answer += "Test score 1: " + score1;
        answer += NEW_LINE;
        answer += "Test score 2: " + score2;
        answer += NEW_LINE;
        answer += "Test score 3: " + score3;
        answer += NEW_LINE;
        answer += "Average test score: " + average();
        return answer;
    }
}
