/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.student.nelson.chris;
import edu.jenks.dist.student.*;
/**
 *
 * @author chris
 */
public class Student extends AbstractStudent{

    /**
    
     * @param args the command line arguments
     */
    private double score1;
    private double score2;
    private double score3;
    
    
    public Student(String firstName, String lastName, Address homeAddress, Address schoolAddress, double testScore1, double testScore2, double testScore3) {
		super(firstName, lastName, homeAddress, schoolAddress);
                score1 = testScore1;
                score2 = testScore2;
                score3 = testScore3;
    }
    public static void main(String[] args) {
        Address home = new Address("home street 1","home city","Home State",11111);
        Address school = new Address("school street 2", "school city", "School State", 11111);
        Student test = new Student("j", "j", home, school, 100 ,100 ,100);
        System.out.println(test.toString());
    }
    public void setTestScore(int testNumber, double score){
        if(testNumber==1){
            score1 = score;
        }else if(testNumber ==2){
            score2 = score;
        }else{
            score3 = score;
        }
    }
    public double getTestScore(int testNumber){
        if(testNumber==1){
            return score1; 
        }else if(testNumber ==2){
            return score2;
        }else{
            return score3;
        }
    }
    public double average(){
        double avg = (score1+score2+score3)/3;
        return avg;
    }
    public String toString(){
        double average = (score1 + score2 + score3)/3;
        return super.toString() + super.NEW_LINE + "Test score 1: " +  score1 + super.NEW_LINE 
        +  "Test score 2: " +  score2 + super.NEW_LINE + "Test score 3: " + score3 + super.NEW_LINE + "Average test score: " + average; 
    }
}