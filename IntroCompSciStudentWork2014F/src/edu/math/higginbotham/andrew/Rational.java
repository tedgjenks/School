package edu.math.higginbotham.andrew;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		this.reduce();
		this.toString();
	}

	@Override
	public AbstractRational add(AbstractRational rn2) {
		int numerator = ((getNumerator() * rn2.getDenominator()) + (rn2.getNumerator() * getDenominator()));
		int denominator = (getDenominator() * rn2.getDenominator());
		Rational ar = new Rational(numerator, denominator);
		return ar;
	}
	
	@Override
	public AbstractRational subtract(AbstractRational rn2) {
		int numerator = ((getNumerator() * rn2.getDenominator()) - (rn2.getNumerator() * getDenominator()));
		int denominator = (getDenominator() * rn2.getDenominator());
		Rational ar = new Rational(numerator, denominator);
		return ar;
	}
	
	@Override
	public AbstractRational multiply(AbstractRational rn2) {
		int numerator = getNumerator() * rn2.getNumerator();
		int denominator = getDenominator() * rn2.getDenominator();
		Rational ar = new Rational(numerator, denominator);
		return ar;
	}
	
	@Override
	public AbstractRational divide(AbstractRational rn2) {
		int invnum = getDenominator();
		int invden = getNumerator();
		int numerator = invnum * rn2.getNumerator();
		int denominator = invden * rn2.getDenominator();
		Rational ar = new Rational(denominator, numerator);
		return ar;
	}

	@Override
	public boolean equals(AbstractRational rn2) {
		boolean result = false;
		if(getDenominator() == rn2.getDenominator() && getNumerator() == rn2.getNumerator())
			result = true;
		return result;
	}
	
	@Override
	public AbstractRational reciprocal() {
		Rational newRational = new Rational(getDenominator(), getNumerator());
		return newRational;
	}

	@Override
	public void reduce() {
		
		if(getNumerator() == 0)
			setDenominator(1);
		else if(Math.abs(getNumerator()) != 1 ){
			int factor = gcd(Math.abs(getNumerator()) , getDenominator());
			int redNumerator = getNumerator() / factor;
			int redDenominator = getDenominator() / factor;
			if(redDenominator < 0)
			{
				redDenominator *= -1;
				redNumerator *= -1; 
			}
			setNumerator(redNumerator);
			setDenominator(redDenominator);
		}
	}

	@Override
	public boolean decimalTerminates() {
		int denominator = getDenominator();
		while(denominator % 2 == 0)
			denominator /= 2;
		while(denominator % 5 == 0)
			denominator /= 5;
		return (denominator == 1);
	}
	
	@Override
	public java.lang.String toString() {
		if(getDenominator() == 1)
			return "" + getNumerator();
		else
			return "" + getNumerator() + "/" + getDenominator();
	}
}
