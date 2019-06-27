package edu.student.britt.emory;
import edu.jenks.dist.student.*;

public class Student extends AbstractStudent {
    public double tS1; 
    public double tS2; 
    public double tS3; 
    public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3){
        super(firstName, lastName, homeAddress, schoolAddress);
        tS1 = testScore1; 
        tS2 = testScore2; 
        tS3 = testScore3; 
    }
    public void setTestScore(int testNumber, double score){
        if(testNumber == 1){  
             tS1 = score; 
        } else if(testNumber == 2){
            tS2 = score; 
        } else{
            tS3 = score; 
        }
    }
    public double getTestScore(int testNumber){
        if(testNumber == 1){  
             return tS1;  
        } else if(testNumber == 2){
            return tS2;
        } else{
            return tS3; 
        }
    }
    public double average(){
        double sum = tS1 + tS2 + tS3; 
        double average = sum / 3;
        return average; 
    }
    public String toString(){ 
        String name = getFirstName() + " " + getLastName() + NEW_LINE;
        String address = "Home Address:" + NEW_LINE + getHomeAddress() + NEW_LINE + "School Address:" + NEW_LINE + getSchoolAddress() + NEW_LINE; 
        String scores = "Test score 1: " + getTestScore(1) + NEW_LINE + "Test score 2: " + getTestScore(2) + NEW_LINE + "Test score 3: " + getTestScore(3) + NEW_LINE;
        String stringAverage = "Average test score: " + average();  
        return name + address + scores + stringAverage;
    }
}
