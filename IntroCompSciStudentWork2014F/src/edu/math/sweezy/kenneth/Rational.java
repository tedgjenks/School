package edu.math.sweezy.kenneth;

import edu.jenks.dist.math.*;

public class Rational extends AbstractRational {
	public static void main(String[] args) {
		Rational testRational1 = new Rational(719, 128);
		Rational testRational2 = new Rational(719, 1);
		System.out.println(testRational1.compareTo(testRational2));
	}

	public Rational(long numerator, long denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		if (denominator < 0) {
			setDenominator(Math.abs(denominator));
			setNumerator(numerator *= -1);
		}
		reduce();
	}

	public int compareTo(AbstractRational arg0) {
		if (arg0.getDenominator() < 0) {
			arg0.setDenominator(Math.abs(arg0.getDenominator()));
			arg0.setNumerator(arg0.getNumerator() * -1);
		}
		
		setDenominator(getDenominator() * arg0.getDenominator());
		arg0.setDenominator(getDenominator() * arg0.getDenominator());
		arg0.setNumerator(arg0.getNumerator() * getDenominator());
		setNumerator(getNumerator() * getDenominator());
		
		System.out.println("First Numerator: " + getNumerator() + " Second Numerator: " + arg0.getNumerator());
		System.out.println("First Denominator: " + getDenominator() + " Second Denominator: " + arg0.getDenominator());
		
		if(getNumerator() > arg0.getNumerator()) {
			return 1;
		} else if(getNumerator() < arg0.getNumerator()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public boolean decimalTerminates() {
		long temp = getDenominator();
		while(temp % 2 == 0) {
			temp /= 2;
		}
		while(temp % 5 == 0) {
			temp /= 5;
		}
		if(temp == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public AbstractRational divide(AbstractRational arg0) {
		return multiply(arg0.reciprocal());
	}
	
	public AbstractRational multiply(AbstractRational arg0) {
		Rational tempRational = new Rational(getNumerator() * arg0.getNumerator(), arg0.getDenominator() * getDenominator());
		tempRational.reduce();
		return tempRational;
	}
	
	public AbstractRational subtract(AbstractRational arg0) {
		Rational tempRational = new Rational(getNumerator(), getDenominator());
		arg0.setNumerator(arg0.getNumerator() * -1);
		return tempRational.add(arg0);
	}
	
	public AbstractRational add(AbstractRational arg0) {
		Rational tempAdded = new Rational((getNumerator() * arg0.getDenominator()) + (arg0.getNumerator() * getDenominator()), (getDenominator() * arg0.getDenominator()));
		return tempAdded;
	}

	public boolean equals(AbstractRational arg0) {
		Rational tempEquals = new Rational(getNumerator() * arg0.getDenominator(),
				getDenominator() * arg0.getDenominator());
		Rational tempEquals2 = new Rational(arg0.getNumerator() * getDenominator(),
				arg0.getDenominator() * getDenominator());
		if (tempEquals.getNumerator() == tempEquals2.getNumerator()
				&& tempEquals.getDenominator() == tempEquals2.getDenominator()) {
			return true;
		}
		return false;
	}

	public AbstractRational reciprocal() {
		if (getNumerator() < 0) {
			long tempNum = Math.abs(getNumerator());
			setNumerator(tempNum);
			Rational tempRational = new Rational(getDenominator() * -1, getNumerator());
			return tempRational;
		}
		Rational tempRational = new Rational(getDenominator(), getNumerator());
		return tempRational;
	}

	public void reduce() {
		if (getNumerator() != 0) {
			long gcd = gcd(getNumerator(), getDenominator());
			setNumerator(getNumerator() / gcd);
			setDenominator(getDenominator() / gcd);
		}
	}

	public String toString() {
		if (getDenominator() == 1 || getNumerator() == 0) {
			return String.valueOf(getNumerator());
		}
		reduce();
		String temp = getNumerator() + "/" + getDenominator();
		return temp;
	}

}