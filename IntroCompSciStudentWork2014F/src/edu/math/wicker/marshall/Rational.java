package edu.math.wicker.marshall;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {
	
	private int operNum, operDenom;
	

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		if (denominator < 0){
			this.setDenominator(-1 * denominator);
			this.setNumerator(-1 * numerator);
		}
		this.reduce();
	}

	@Override
	public AbstractRational add(AbstractRational toAdd) {
		operNum = this.getNumerator() * toAdd.getDenominator();
		operDenom = this.getDenominator() * toAdd.getDenominator();
		toAdd.setNumerator(toAdd.getNumerator() * this.getDenominator());
		Rational returnVal = new Rational(operNum + toAdd.getNumerator(), operDenom);
		returnVal.reduce();
		return returnVal;
	}

	@Override
	public AbstractRational subtract(AbstractRational toSubtract) {
		toSubtract.setNumerator(-1 * toSubtract.getNumerator());
		AbstractRational returnVal = this.add(toSubtract);
		return returnVal;
	}

	@Override
	public AbstractRational divide(AbstractRational toDivide) {
		AbstractRational recip = toDivide.reciprocal();
		return multiply(recip);
	}

	@Override
	public AbstractRational multiply(AbstractRational toMultiply) {
		operNum = this.getNumerator() * toMultiply.getNumerator();
		operDenom = this.getDenominator() * toMultiply.getDenominator();
		Rational returnVal = new Rational(operNum, operDenom);
		returnVal.reduce();
		return returnVal;
	}

	@Override
	public boolean equals(AbstractRational testValue) {
		return this.getNumerator() == testValue.getNumerator() && this.getDenominator() == testValue.getDenominator();
	}

	@Override
	public AbstractRational reciprocal() {
		return new Rational(this.getDenominator(), this.getNumerator());
	}

	@Override
	public void reduce() {
		if (this.getNumerator() != 0){
			int divisor = this.gcd(Math.abs(this.getNumerator()), this.getDenominator());
			this.setNumerator(this.getNumerator() / divisor);
			this.setDenominator(this.getDenominator() / divisor);
		}
		else{
			this.setDenominator(1);
		}
	}

	@Override
	public boolean decimalTerminates() {
		operDenom = this.getDenominator();
		
		while (operDenom % 2 == 0){
			operDenom /= 2;
		}
		
		while (operDenom % 5 == 0){
			operDenom /= 5;
		}
		
		return operDenom == 1;
	}

	@Override
	public String toString() {
		return this.getDenominator() == 1 ? "" + this.getNumerator() : this.getNumerator() + "/" + this.getDenominator();
	}

	
}
