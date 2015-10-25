package edu.math.busbee.hunter;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		this.reduce();
		this.toString();
	}

	@Override
	public AbstractRational add(AbstractRational toAdd) {
		int rN1 = getNumerator() * toAdd.getDenominator();
		int rN2 = getDenominator() * toAdd.getNumerator();
		int newNumerator = rN1 + rN2;
		int newDenominator = toAdd.getDenominator() * getDenominator();
		Rational returnedValue = new Rational(newNumerator, newDenominator);
		return returnedValue;

	}

	@Override
	public AbstractRational divide(AbstractRational toDivide) {
		return (toDivide.multiply(reciprocal())).reciprocal();
	}

	@Override
	public boolean equals(AbstractRational theNumber) {
		boolean answer = false;
		if(getNumerator() == theNumber.getNumerator()){
			if(getDenominator() == theNumber.getDenominator())
				answer = true;
		}
		return answer;
	}

	@Override
	public AbstractRational multiply(AbstractRational toMultiply) {
		int numerator = getNumerator() * toMultiply.getNumerator();
		int denominator = getDenominator() * toMultiply.getDenominator();
		Rational answer = new Rational(numerator, denominator);
		answer.reduce();
		return answer;
	}

	@Override
	public AbstractRational reciprocal() {
		AbstractRational reciprocalNumber = new Rational(getDenominator(), getNumerator());
		return reciprocalNumber;
	}

	@Override
	public void reduce() {
		if(getNumerator() == 0)
			setDenominator(1);
		int greatestCommonFactor = gcd(Math.abs(getNumerator()) , Math.abs(getDenominator()));
		int reducedNumerator = getNumerator() / greatestCommonFactor;
		int reducedDenominator = getDenominator() / greatestCommonFactor;
		if(reducedDenominator < 0){
			reducedNumerator *= -1; 
			reducedDenominator *= -1;
		}
		setNumerator(reducedNumerator);
		setDenominator(reducedDenominator);
	}



	@Override
	public AbstractRational subtract(AbstractRational toSubtract) {
		int numer1 = getNumerator() * toSubtract.getDenominator();
		int numer2 = getDenominator() * toSubtract.getNumerator();
		int numerAnswer = numer1 - numer2;
		int denomAnswer = toSubtract.getDenominator() * getDenominator();
		Rational returnedValue = new Rational(numerAnswer, denomAnswer);
		return returnedValue;
	}

	@Override
	public boolean decimalTerminates() {
		int denominator = getDenominator();
		while(denominator % 2 == 0){
			denominator /= 2;
		}
		while(denominator % 5 == 0){
			denominator /= 5;
		}
		return denominator == 1;
	}

	public String toString(){
		String answer;
		if(getDenominator() == 1)
			answer = "" + getNumerator();
		else
			answer = "" + getNumerator() + "/" + getDenominator();

		return answer;
	}

}
