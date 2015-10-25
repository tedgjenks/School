package edu.math.piland.will;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		if(numerator<0)
			setNumerator(numerator);
		if(denominator < 0){
			setDenominator(denominator * -1);
			setNumerator(numerator * -1);
		}
			
		
		this.reduce();
		this.toString();
	}

	@Override
	public AbstractRational add(AbstractRational toAdd) {
	
		int rng1 = getNumerator() * toAdd.getDenominator();
		int rng2 = getDenominator() * toAdd.getNumerator();
		int newNumerator = rng1 + rng2;
		int newDenominator = getDenominator() * toAdd.getDenominator();
		Rational returnAddValue = new Rational(newNumerator, newDenominator);
		return returnAddValue;
	}

	@Override
	public AbstractRational divide(AbstractRational toDivide) {
	
		int newNumerator = getNumerator() * toDivide.getDenominator();
		int newDenominator = getDenominator() * toDivide.getNumerator();
		Rational returnDivideValue = new Rational(newNumerator, newDenominator);
		return returnDivideValue;
	}

	@Override
	public boolean equals(AbstractRational rn2) {
	
		if (getNumerator() == rn2.getNumerator() && getDenominator() == rn2.getDenominator())
			return true;
		else return false;
			
	
	}

	@Override
	public AbstractRational multiply(AbstractRational toMultiply) {
	
		int newNumerator = getNumerator() * toMultiply.getNumerator();
		int newDenominator = getDenominator() * toMultiply.getDenominator();
		Rational returnMultiplyValue = new Rational(newNumerator, newDenominator);
		return returnMultiplyValue;
	}

	@Override
	public AbstractRational reciprocal() {
	
		int newNumerator = getDenominator();
		int newDenominator = getNumerator();
		Rational returnReciprocalValue = new Rational(newNumerator, newDenominator);
		return returnReciprocalValue;
	}

	@Override
	public void reduce() {
		
		
		if(getNumerator() == 0)
			setDenominator(1);
		else if (getNumerator() != 0) {
			int greatestCommonFactor = gcd(Math.abs(getNumerator()), Math.abs(getDenominator()));
			int reducedNumerator = getNumerator() / greatestCommonFactor;
			int reducedDenominator = getDenominator() / greatestCommonFactor;
			setDenominator(reducedDenominator);
			setNumerator(reducedNumerator);
		}
	}

	@Override
	public AbstractRational subtract(AbstractRational toMinus) {
		 int newNumerator1= getNumerator() * toMinus.getDenominator();
		 int newDenominator1 = getDenominator() * toMinus.getDenominator();
		 int numerator = newNumerator1 - toMinus.getNumerator() * getDenominator();
		 if (newDenominator1 <0){
			 newDenominator1 *= -1;
			 numerator *= -1;
		 }
		 Rational returnMinusValue = new Rational(numerator, newDenominator1);
		return returnMinusValue;
	}

	@Override
	public boolean decimalTerminates() {
		int denom = getDenominator();
		while(denom %2 ==0){
			denom /=2;}
		while(denom %5 ==0){
			denom /= 5;
		}
		return denom == 1;
	}
	public String toString(){
		int numerator = getNumerator();
		int denominator = getDenominator();
		String string = "" + numerator +"/" + denominator;
		if(denominator==1){
			string = "" +numerator;
		}
		return string;
		
	}
}
