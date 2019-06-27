package edu.midterm.ics.macias.marcus;

import edu.jenks.dist.midterm.ics.AbstractMidterm;

public class Midterm extends AbstractMidterm {
    public static void main(String[] args) {
	Midterm run = new Midterm();
	
	//System.out.println(run.isIsosceles(3,4,4));
	//System.out.println(run.floatEquals((double)10.3,(double)10.9,(double).5));
	System.out.println(run.countSubString("a man a plan tucan","a"));
	//System.out.println(run.sum(66));
    }
    
    int ticketNumber = 0;
    
    public String ticketMessage(String name, int number) {
        // TODO Auto-generated method stub
        
        return name + " is ticket number " + number + ".";
    }

    
    public String ticketMessage(String name) {
        ticketNumber += 1;
        
        return name + " is ticket number " + ticketNumber + ".";
    }

    
    public int sum(int lastNumber) {
        // TODO Auto-generated method stub
        int sum = 0;
        for(int i = 1; i <= lastNumber;i++){
            sum+=i;
        }
        return sum;
    }

    
    public int maxOfTwo(int arg1, int arg2) {
        return Math.max(arg1,arg2);
    }

    
    public int countSubString(String s, String sub) {
        // TODO Auto-generated method stub
        if(s.length() == 1 && s.equals(sub)){
            return 1;
        }
        int thing = sub.length() - 1;
        int answer = 0;
        for(int i = 0 ; i < s.length() - thing;i++){
            if(s.substring(i,i+sub.length()).equals(sub)){
                
                answer += 1;
            }
        }
        return answer;
    }

    
    public boolean evenlyDivisible(int dividend, int divisor) {
        // TODO Auto-generated method stub
        boolean answer = dividend % divisor == 0;
        return answer;
    }

    
    public boolean floatEquals(double d1, double d2, double delta) {
        
        if(Math.abs(d1-d2) <= delta){
            return true;
        }
        return false;
    }

    
    public boolean isIsosceles(int s1, int s2, int s3) {
        // TODO Auto-generated method stub
        if(s1 == s2 || s1 == s3 || s3 == s2){
            return true;
        }
        return false;
    }

    
    public String assignGrade(double score) {
        if(score >= 90){
            return "A";
        }
        if(score >= 80){
            return "B";
        }
        if(score >= 70){
            return "C";
        }
        if(score >= 60){
            return "D";
        }
        return "F";
    }
    

}
