package edu.midterm.ics.hollingsworth.james;

import edu.jenks.dist.midterm.ics.AbstractMidterm;

public class Midterm extends AbstractMidterm {
    private int ticketNum = 0;
    public static void main(String[] args) {
        Midterm m = new Midterm();
        System.out.println(m.ticketMessage("James", 5));
        System.out.println(m.ticketMessage("James"));
        System.out.println(m.ticketMessage("Chad"));
        System.out.println(m.sum(100));
        System.out.println(m.sum(50));
        System.out.println(m.maxOfTwo(50, 51));
        System.out.println(m.maxOfTwo(50, 45));
        System.out.println(m.maxOfTwo(50, 50));
        System.out.println(m.countSubString("hahahakajaha", "ha"));
        System.out.println(m.countSubString("i really had to go to the bathroom a second ago", "to"));
        System.out.println(m.evenlyDivisible(5, 3));
        System.out.println(m.evenlyDivisible(10, 5));
        System.out.println(m.floatEquals(5, 6, 1));
        System.out.println(m.floatEquals(5, 7, 1));
        System.out.println(m.isIsosceles(5, 6, 1));
        System.out.println(m.isIsosceles(5, 5, 1));
        System.out.println(m.assignGrade(92));
        System.out.println(m.assignGrade(88));
        System.out.println(m.assignGrade(76));
        System.out.println(m.assignGrade(65));
        System.out.println(m.assignGrade(55));
    }
    
    public String ticketMessage(String name, int number) {
        return name + " is ticket number " + number + ".";
    }
    
    public String ticketMessage(String name) {
        return name + " is ticket number " + (++ticketNum) + ".";
    }
    
    public int sum(int lastNumber) {
        if((1 + lastNumber) * (lastNumber / 2) < 1) return 1;
        return (1 + lastNumber) * (lastNumber / 2);
    }
    
    public int maxOfTwo(int arg1, int arg2) {
        if(arg2 > arg1) return arg2;
        return arg1;
    }
    
    public int countSubString(String s, String sub) {
        int total = 0;
        for(int i = 0; i <= s.length() - sub.length(); i++) {
            if(s.substring(i, i + sub.length()).equals(sub)) total++;
        }
        return total;
    }
    
    public boolean evenlyDivisible(int dividend, int divisor) {
        double t = (double) dividend / divisor;
        double f = Math.floor((double) dividend / divisor);
        return (t == f);
    }
    
    public boolean floatEquals(double d1, double d2, double delta) {
        if(Math.abs(d1 - d2) <= delta) return true;
        return false;
    }
    
    public boolean isIsosceles(int s1, int s2, int s3) {
        return ((s1 == s2) || (s1 == s3) || (s2 == s3));
    }
    
    public String assignGrade(double score) {
        if(score >= 90) return "A";
        if(score >= 80) return "B";
        if(score >= 70) return "C";
        if(score >= 60) return "D";
        return "F";
    }
}