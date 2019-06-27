package edu.midterm.ics.driscoll.aidan;


import edu.jenks.dist.midterm.ics.AbstractMidterm;

public class Midterm extends AbstractMidterm {
    public int number = 0;
    public String ticketMessage(String name, int number) {
        return name + " is ticket number " + number + ".";
    }

    public String ticketMessage(String name) {
        number++;
        return name + " is ticket number " + number + ".";
    }

    public int sum(int lastNumber) {
        int finalN = 0;
        int i = 1;
        if(lastNumber > 1){
            while(i <= lastNumber){
                finalN += i;
                i++;
            }
        }else{
            return lastNumber;
        }
        return finalN;
    }

    public int maxOfTwo(int arg1, int arg2) {
        int max = arg1;
        if(arg2 > max){
            max = arg2;
        }
        return max;
    }

    public int countSubString(String s, String sub) {
        int count = 0;
        int subLength = sub.length();
        for(int i = 0; i < s.length(); i++){
            if(i + subLength > s.length()){
                
            }else{
                if(sub.equals(s.substring(i, i + subLength))){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean evenlyDivisible(int dividend, int divisor) {
        if(dividend % divisor == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean floatEquals(double d1, double d2, double delta) {
        double difference = Math.abs(d1 - d2);
        if(difference <= delta){
            return true;
        }
        return false;
    }

    public boolean isIsosceles(int s1, int s2, int s3) {
        if(s1 == s2){
            return true;
        }
        if(s2 == s3){
            return true;
        }
        if(s3 == s1){
            return true;
        }  
        return false;
    }

    public String assignGrade(double score) {
        String grade = "";
        if(score >= 90){
            grade = "A";
            return grade;
        }
        if(score >= 80){
            grade = "B";
            return grade;
        }
        if(score >= 70){
            grade = "C";
            return grade;
        }
        if(score >= 60){
            grade = "D";
            return grade;
        }
        if(score < 60){
            grade = "F";
            return grade;
        }
        return grade;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
