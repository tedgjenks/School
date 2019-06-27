package edu.midterm.ics.britt.emory;

import edu.jenks.dist.midterm.ics.AbstractMidterm;

public class Midterm extends AbstractMidterm {
    public String ticketMessage(String name, int number) {
        return name + " is ticket number " + number + ".";
    }
    int ticketNumber = 0; 
    public String ticketMessage(String name) {
        ticketNumber += 1;
        return name + " is ticket number " + ticketNumber + ".";
    }

    public int sum(int lastNumber) {
        int count = 0; 
        for(int i = 1; i < lastNumber; i++){
            count += i; 
        }
        count += lastNumber; 
        return count;
    }

    public int maxOfTwo(int arg1, int arg2) {
        if(arg1 > arg2){
            return arg1; 
        } else if(arg1 < arg2){
            return arg2; 
        } 
        return arg1; 
    }

    public int countSubString(String s, String sub) {
        int count = 0; 
        for(int i = 0; i < s.length(); i++){
            if((i + sub.length()) <= s.length()){
                if(s.substring(i, i + sub.length()).equals(sub)){
                    count += 1;
                }
            }
        }
        return count;
    }

    public boolean evenlyDivisible(int dividend, int divisor) {
        if(dividend % divisor == 0){
            return true; 
        }
        return false;
    }

    public boolean floatEquals(double d1, double d2, double delta) {
        double difference = 0; 
        if(d1 > d2){
            difference = d1 - d2; 
        } else if(d2 > d1){
            difference = d2 - d1; 
        } else{
            if(delta == 0.0){
                return true; 
            }
            return false; 
        }
        if(difference <= delta){
            return true; 
        }
        return false;
    }

    public boolean isIsosceles(int s1, int s2, int s3) {
        if((s1 == s2) || (s1 == s3) || (s2 == s3)){
            return true; 
        }
        return false;
    }

    public String assignGrade(double score) {
        if(score >= 90){
            return "A";
        } else if(score >= 80){
            return "B";
        } else if(score >= 70){
            return "C";
        } else if(score >= 60){
            return "D";
        }
        return "F";
    }

    public static void main(String[] args) {
        
    }
}
