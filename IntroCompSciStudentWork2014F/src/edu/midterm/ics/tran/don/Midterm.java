package edu.midterm.ics.tran.don;

import edu.jenks.dist.midterm.ics.AbstractMidterm;

public class Midterm extends AbstractMidterm {

    @Override
    public String ticketMessage(String name, int number) {
        return name + " is ticket number " + number + ".";
    }
    private int ticketNumber = 0;
    @Override
    public String ticketMessage(String name) {
        ticketNumber++;
        return name + " is ticket number " + ticketNumber + ".";
    }

    @Override
    public int sum(int lastNumber) {
        int sum = 0;
        int count = 1;
        if(lastNumber == 1) {
            return 1;
        }
        if(lastNumber < 0) {
            while(true) {
                sum += count;
                count--;
                if(count == lastNumber) {
                    sum += count;
                    break;
                }
            }
        } else {
            while(true) {
                sum += count;
                count++;
                if(count == lastNumber) {
                    sum += count;
                    break;
                }
            }
        }
        return sum;
    }

    @Override
    public int maxOfTwo(int arg1, int arg2) {
        return Math.max(arg1, arg2);
    }

    @Override
    public int countSubString(String s, String sub) {
        int count = 0;
        if(sub.length() > s.length()) {
            return 0;
        }
        int subLength = sub.length();
        int testLength = s.length() - subLength;
        for(int i = 0; i <= testLength; i++) {
            String curr = s.substring(i, i+subLength);
            if(curr.equals(sub)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean evenlyDivisible(int dividend, int divisor) {
        if(dividend % divisor == 0) {
            return true;
          }
        return false;
    }

    @Override
    public boolean floatEquals(double d1, double d2, double delta) {
        double test = Math.abs(d1 - d2);
        if(test <= delta) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isIsosceles(int s1, int s2, int s3) {
        if(s1 == s2 || s2 == s3 || s3 == s1) {
            return true;
        }
        return false;
    }

    @Override
    public String assignGrade(double score) {
        if(score >= 90) {
            return "A";
        }
        if(score >= 80) {
             return "B";
        }
        if(score >= 70) {
            return "C";
        }
        if(score >= 60) {
            return "D";
        }
        return "F";
    }

    public static void main(String[] args) {
        Midterm act = new Midterm();
        int summed = act.countSubString("a man a plan tucan", "a");
        System.out.println(summed);
    }

}
