package edu.midterm.ics.savelyev.denis;

import edu.jenks.dist.midterm.ics.*;

public class Midterm extends AbstractMidterm{
    int ticketNum = 0;
    public static void main(String[] args) {
        Midterm instance  = new Midterm();
        System.out.println(instance.countSubString("a man a plan tucan", "a"));
    }
    
    public String ticketMessage(String name, int number) {
        return name + " is ticket number " + number + ".";
    }

    public String ticketMessage(String name) {
        ticketNum ++;
        return name + " is ticket number " + ticketNum + ".";
    }

    public int sum(int lastNumber) {
        int sum = 0;
        for (int i = 0; i <= lastNumber; i ++) {
            sum += i;
        }
        return sum;
    }

    public int maxOfTwo(int arg1, int arg2) {
        return Math.max(arg1, arg2);
    }

    public int countSubString(String s, String sub) {
        int count = 0;
        return count;
    }

    public boolean evenlyDivisible(int dividend, int divisor) {
        if (dividend % divisor == 0) {
            return true;
        }
        return false;
    }

    public boolean floatEquals(double d1, double d2, double delta) {
        if ((Math.abs(d1 - d2) <= delta) && (Math.abs(d1 - d2) <= delta)) {
            return true;
        }
        return false;
    }

    public boolean isIsosceles(int s1, int s2, int s3) {
        if (s1 == s2 || s2 == s3 || s1 == s3) {
            return true;
        }
        return false;
    }

    public String assignGrade(double score) {
        if (score >= 90) {
            return "A";
        } else if(score >= 80 && score <= 89) {
            return "B";
        } else if(score >= 70 && score <= 79) {
            return "C";
        } else if(score >= 60 && score <= 69) {
            return "D";
        } else {
            return "F";
        }
    }

}
