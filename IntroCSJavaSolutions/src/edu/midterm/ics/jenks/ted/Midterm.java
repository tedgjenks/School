/**
 * 
 */
package edu.midterm.ics.jenks.ted;

import edu.jenks.dist.midterm.ics.AbstractMidterm;;

/**
 * @author Ted jenks
 *
 */
public class Midterm extends AbstractMidterm {
	private int ticketNumber = 1;

	@Override
	public String ticketMessage(String name, int number) {
		return new StringBuilder(30).append(name).append(" is ticket number ").append(number).append(".").toString();
	}

	@Override
	public String ticketMessage(String name) {
		return ticketMessage(name, ticketNumber++);
	}

	@Override
	public int sum(int lastNumber) {
		int sum = 0;
		for(int term = lastNumber; term >= 1; term--)
			sum += term;
		return sum;
	}

	@Override
	public int maxOfTwo(int arg1, int arg2) {
		return arg1 > arg2 ? arg1 : arg2;
	}

	@Override
	public int countSubString(String s, String sub) {
		int count = 0;
		for(int fromIndex = 0; fromIndex < s.length() && (fromIndex = s.indexOf(sub, fromIndex)) >= 0; count++, fromIndex += sub.length());
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
		return s1 == s2 || s1 == s3 || s2 == s3;
	}
	
	public String assignGrade(double score) {
		String grade = "F";
		if(score >= 90)
			grade =  "A";
		else if(score >= 80)
			grade = "B";
		else if(score >= 70)
			grade = "C";
		else if(score >= 60)
			grade = "D";
		
		return grade;
	}

}
