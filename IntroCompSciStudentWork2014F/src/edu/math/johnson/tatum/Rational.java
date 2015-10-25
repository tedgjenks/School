package edu.math.johnson.tatum;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational( int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		this.reduce();
		this.toString();
		if (getDenominator() < 0){
			setNumerator (getNumerator() * -1);
			setDenominator (getDenominator() * -1);
		}
	}

	@Override
	public AbstractRational add(AbstractRational toAdd) {
		int rN1 = getNumerator() * toAdd.getDenominator();
		int rN2 = getDenominator() * toAdd.getNumerator();
		int newNumerator = rN1 + rN2;
		int newDenominator = getDenominator() * toAdd.getDenominator();
		return new Rational (newNumerator , newDenominator);
	} 

	@Override
	public AbstractRational divide(AbstractRational toDivide) {
		int newNumerator = getNumerator() * toDivide.getDenominator();
		int newDenominator = getDenominator() * toDivide.getNumerator();
		return new Rational (newNumerator , newDenominator);
	}

	public boolean equals (AbstractRational equalVal) {
		return (getNumerator() == equalVal.getNumerator()) && (getDenominator() == equalVal.getDenominator());
	}

	@Override
	public AbstractRational multiply(AbstractRational toMultiply) {
		int newNumerator = getNumerator() * toMultiply.getNumerator();
		int newDenominator = getDenominator() * toMultiply.getDenominator();
		return new Rational (newNumerator , newDenominator);
	}

	@Override
	public AbstractRational reciprocal() {
		int newNumerator = getDenominator();
		int newDenominator = getNumerator();
		return new Rational (newNumerator , newDenominator);
	}


	@Override
	public AbstractRational subtract(AbstractRational toSubtract) {
		int rN1 = getNumerator() * toSubtract.getDenominator();
		int rN2 = getDenominator() * toSubtract.getNumerator();
		int newNumerator = rN1 - rN2;
		int newDenominator = getDenominator() * toSubtract.getDenominator();
		return new Rational (newNumerator , newDenominator);
	}

	@Override
	public boolean decimalTerminates() {
		int x = getDenominator();
		while(x % 2 == 0)
			x /= 2;
		while(x % 5 == 0)
			x /= 5;
		return x == 1;

	}

	@Override
	public void reduce() {
		if (getNumerator() == 0)
			setDenominator (1);
		else if (getNumerator() != 1){
			int gCF = gcd(getNumerator() , getDenominator());
			int newNum = getNumerator() / gCF;
			int newDen = getDenominator() / gCF;
			setNumerator(newNum);
			setDenominator(newDen);
		}
		else if (getDenominator() == 1){
			setNumerator(getNumerator());
			setDenominator(getDenominator());
		}
	}	
	public String toString(){
		if (getDenominator() == 1)
			return "" + getNumerator(); 
		else 
			return "" + getNumerator() + "/" + getDenominator();
	}


}


