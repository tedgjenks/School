package edu.midterm.ics.crosby.amber;

import edu.jenks.dist.midterm.ics.AbstractMidterm;

public class Midterm extends AbstractMidterm {
   
    public String ticketMessage(String name, int number) {
        String numTicket = name + " is ticket number " + number + ".";
        return numTicket;
    }
    
    int num = 1;
    public String ticketMessage(String name) {
        name = name + " is ticket number " + num + ".";
        num += 1;
        return name;
    }

    public int sum(int lastNumber) {
        int num = 0;
        for (int i = 1; i <= lastNumber; i++){
            num += i;
        }
        return num;
    }

    public int maxOfTwo(int arg1, int arg2) {
        int max = Math.max(arg1, arg2);
        return max;
    }

    public int countSubString(String s, String sub) {
        
        return 0;
    }

    public boolean evenlyDivisible(int dividend, int divisor) {
        
        return false;
    }
    
    public boolean floatEquals(double d1, double d2, double delta) {
        double num = d1 - d2;
        double num2 = d2 - d1;
        if(Math.abs(num) <= delta){
            return true;
        }else if (Math.abs(num2) <= delta){
            return true;
        }
        return false;
    }

    public boolean isIsosceles(int s1, int s2, int s3) {
        if (s1 == s2){
            return true;
        }
        if(s2 == s3){
            return true;
        }
        if(s1 == s3){
            return true;
        }
        return false;
    }

    public String assignGrade(double score) {
        if (score >= 90){
            return "A";
        }
        if (score >= 80 & score < 90){
            return "B";
        }
        if (score >= 70 & score < 80){
            return "C";
        }
        if (score >= 60 & score < 70){
            return "D";
        }
        if (score < 60){
            return "F";
        }
        return null;
    }

    public static void main(String[] args) {
        

    }

}
