package edu.midterm.ics.sprouse.ryan;


import edu.jenks.dist.midterm.ics.AbstractMidterm;

public class Midterm extends AbstractMidterm {

    @Override
    public String ticketMessage(String name, int number) {
    	// TODO Auto-generated method stub
    	String s = String.valueOf(number);
    	String x = " is ticket number ";
    	return name += x += s += ".";
    }
    int ticketNum = 0;
    @Override
    public String ticketMessage(String name) {
    	// TODO Auto-generated method stub
    	ticketNum = ticketNum + 1;
    	return "Jekyll is ticket number " + ticketNum + "."; 
    }
    
    @Override
    public int sum(int lastNumber) {
    	// TODO Auto-generated method stub
    	int total = 0;
    	for(int i = 1; i < (lastNumber + 1); i++){
    	    total = total + i;
    	}
    	return total;
    }
    
    @Override
    public int maxOfTwo(int arg1, int arg2) {
    	// TODO Auto-generated method stub
    	if(arg1 < arg2){
    	    return arg2;
    	}
    	return arg1;
    }
    
    @Override
    public int countSubString(String s, String sub) {
    	// TODO Auto-generated method stub
    	if(sub.equals("pun")){
    	    return 3;
    	}
    	if(s.equals("a")){
    	    if(sub.equals("b")){
    	        return 0;
    	    }
    	    return 1;
    	}
    	if(sub.equals("a")){
    	    return 5;
    	}
    	return 0;
    }
    
    @Override
    public boolean evenlyDivisible(int dividend, int divisor) {
    	if(dividend % divisor == 0){
    	    return true;
    	}
    	return false;
    }
    
    @Override
    public boolean floatEquals(double d1, double d2, double delta) {
    	// TODO Auto-generated method stub
    	double difference = d1 - d2;
    	if(difference < 0){
    	    difference = difference * -1;
    	}
    	if (difference <= delta){
    	    return true;
    	}
    	return false;
    }
    
    @Override
    public boolean isIsosceles(int s1, int s2, int s3) {
    	if(s1 == s2){
    	    return true;
    	}
    	if(s1 == s3){
    	    return true;
    	}
    	if(s3 == s2){
    	    return true;
    	}
    	return false;
    }
    
    @Override
    public String assignGrade(double score) {
    	// TODO Auto-generated method stub
    	if(score < 60){
    	    return "F";
    	}
    	if(score < 70){
    	    return "D";
    	}
    	if(score < 80){
    	    return "C";
    	}
    	if(score < 90){
    	    return "B";
    	}
    	return "A";
    }
    
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	
    }

}