package edu.midterm.ics.whitt.rose;

import edu.jenks.dist.midterm.ics.AbstractMidterm;

public class Midterm extends AbstractMidterm {
        public static void main(String[] args) {
		// TODO Auto-generated method stub
		Midterm pc = new Midterm();
		
		//returns name is ticket number
		System.out.println(pc.ticketMessage("Rose", 2));
		
		System.out.println(pc.ticketMessage("Rose"));
		System.out.println(pc.ticketMessage("Rose"));
		
		//returns the sum of the integers from 1 to lastNumber inclusive
		System.out.println(pc.sum(5));
		//returns the larger of arg1 and arg2
		System.out.println(pc.maxOfTwo(2, 3));
		//returns the number of times sub is in s
		System.out.println(pc.countSubString("puns are not punny pun", "pun"));
		//returns true if dividend is evenly divisible by divisor, otherwise false
		System.out.println(pc.evenlyDivisible(308, 4));
		//returns true if d1 and d2 are no further apart than delta
		System.out.println(pc.floatEquals(10.3, 10.7, 0.5));
		//returns true if any of the arguments are the same, false if none are the same
		System.out.println(pc.isIsosceles(2, 3, 1));
		//returns the letter grade
		System.out.println(pc.assignGrade(52));
	}
	
	@Override
	public String ticketMessage(String name, int number) {
		return name + " is ticket number " + number + ".";
	}
	int ticketNum = 0;
	@Override
	public String ticketMessage(String name) {
	       ticketNum += 1;
	       return name + " is ticket number " + ticketNum + ".";
		
	}

	@Override
	public int sum(int lastNumber) {
	       int sumCount = 0;
	       for (int i = 1; i <= lastNumber; i++) {
		    sumCount += i;
		}
		return sumCount;
	}

	@Override
	public int maxOfTwo(int arg1, int arg2) {
		int thing = Integer.max(arg1, arg2);
		return thing;
	}

	@Override
	public int countSubString(String s, String sub) {
		int S_LENGTH = s.length();
	        int SUB_LENGTH = sub.length();
	        int count = 0;
		for (int i = 0; i < S_LENGTH - SUB_LENGTH + 1; i++) {
		    String search = s.substring(i, i + SUB_LENGTH);
		    if (search.equals(sub)) {
		        count++;
		    }
		}
		return count;
	}

	@Override
	public boolean evenlyDivisible(int dividend, int divisor) {
		return dividend % divisor == 0;
	}

	@Override
	public boolean floatEquals(double d1, double d2, double delta) {
		return Math.abs(d1 - d2) <= delta;
	}

	@Override
	public boolean isIsosceles(int s1, int s2, int s3) {
		if (s1 == s2 || s1 == s3 || s2 == s3) {
		    return true;
		}else {
		    return false;
		}
	}

	@Override
	public String assignGrade(double score) {
		if (score >= 90) {
		   return "A"; 
		} else if (score >= 80 && score < 90) {
		    return "B";
		} else if (score >= 70 && score < 80) {
		    return "C";
		} else if (score >= 60 && score < 70) {
		    return "D";
		} else {
		    return "F";
		}
	}

	

}
