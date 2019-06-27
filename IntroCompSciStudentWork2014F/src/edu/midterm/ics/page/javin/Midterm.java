package edu.midterm.ics.page.javin;

import edu.jenks.dist.midterm.ics.*;

public class Midterm extends AbstractMidterm {
	int ticketNum = 0;
	 
	public String ticketMessage(String name, int number) {
		return name + " is ticket number " + number + ".";
	}

	 
	public String ticketMessage(String name) {
		ticketNum ++;
		return name + " is ticket number " + ticketNum + ".";
	}

	 
	public int sum(int lastNumber) {
		int i = 0;
		int sum = 0;
		while(i <= lastNumber) {
			sum += i;
			i++;
		}
		return sum;
	}

	 
	public int maxOfTwo(int arg1, int arg2) {
		return Math.max(arg1, arg2);
	}

	 
	public int countSubString(String s, String sub) {
		int i = 0;
		int count = 0;
		while (i < s.length() - sub.length() + 1) {
			if (s.substring(i, i + sub.length()).contentEquals(sub)) {
				count++;
			}
			i++;
		}
		return count;
	}

	 
	public boolean evenlyDivisible(int dividend, int divisor) {
		if (dividend % divisor == 0) {
			return true;
		}
		return false;
	}

	 
	public boolean floatEquals(double d1, double d2, double delta) {
		if (Math.abs(d1 - d2) <= delta) {
			return true;
		}else if (Math.abs(d2 - d1) <= delta) {
			return true;
		}
		return false;
	}

	 
	public boolean isIsosceles(int s1, int s2, int s3) {
		if(s1 == s2 || (s2 == s3 || s1 == s3)) {
			return true;
		}
		return false;
	}

	 
	public String assignGrade(double score) {
		if(score >= 90) {
			return "A";
		}else if (score >= 80 && score < 90) {
			return "B";
		}else if (score >= 70 && score < 80) {
			return "C";
		}else if (score >= 60 && score < 70) {
			return "D";
		}
		return "F";
	}

	public static void main(String[] args) {
		Midterm instance = new Midterm();
		System.out.println(instance.isIsosceles(1, 1, 2));
		System.out.println(instance.isIsosceles(1, 2, 1));
		System.out.println(instance.isIsosceles(1, 1, 1));
		System.out.println(instance.isIsosceles(2, 1, 1));
		System.out.println(instance.floatEquals(10.2, 10.7, 0.5));
	}

}