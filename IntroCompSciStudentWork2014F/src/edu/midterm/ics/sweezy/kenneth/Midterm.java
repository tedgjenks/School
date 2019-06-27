package edu.midterm.ics.sweezy.kenneth;

import edu.jenks.dist.midterm.ics.*;

public class Midterm extends AbstractMidterm {
    int ticketNum = 1;
    public void main(String[] args) {
        System.out.println(countSubString("puns are not punny pun", "pun"));
    }
    
    public String ticketMessage(String name, int number) {
        return name + " is ticket number " + number + ".";
    }
    
    public String ticketMessage(String name) {
        String message = name + " is ticket number " + ticketNum + ".";
        ticketNum++;
        return message;
    }

    public int sum(int lastNumber) {
        int temp = 0;
        for(int i = 0; i <= lastNumber; i++) {
            temp += i;
        }
        return temp;
    }

    public int maxOfTwo(int arg1, int arg2) {
        return Math.max(arg1, arg2);
    }

    public int countSubString(String s, String sub) {
        /*
        int subLength = sub.length();
        String tempString = "";
        int count = 0;
        for(int i = 0; i < s.length() - 1; i++) {
            for(int k = 0; k < subLength - 1; k++) {
                tempString += s.charAt(i + k);
            }
            if(tempString.equals(sub)) {
                count++;
            }
        }
        */
        return 0;
    }

    public boolean evenlyDivisible(int dividend, int divisor) {
        return dividend % divisor == 0;
    }

    public boolean floatEquals(double d1, double d2, double delta) {
        return Math.abs((d1 - d2)) <= delta;
    }

    public boolean isIsosceles(int s1, int s2, int s3) {
        if (s1 == s2 || s1 == s3 || s2 == s3) return true;
        return false;
    }

    public String assignGrade(double score) {
        if(score >= 90) {
            return "A";
        } else if(score < 90 && score >= 80) {
            return "B";
        } else if(score < 80 && score >= 70) {
            return "C";
        } else if(score < 70 && score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
