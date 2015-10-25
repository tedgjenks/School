package edu.math.burroughs.lauren;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {
	public Rational(int numer, int denom)throws IllegalArgumentException{
		super(numer , denom);
		this.reduce();
		if(denom < 0){
			setNumerator(numer * -1);
			setDenominator(denom * -1);
		}
	}


	@Override
	public AbstractRational add(AbstractRational rational) {
		int num1 = getNumerator() * rational.getDenominator();
		int num2 = getDenominator() * rational.getNumerator();
		int denom = getDenominator() * rational.getDenominator();
		int finNum = num1 + num2;
		Rational ratio = new Rational(finNum, denom);
		return ratio;
	}

	@Override
	public boolean decimalTerminates() {
		int denom = getDenominator();
		while(denom % 2 == 0)
			denom /= 2;
		while(denom % 5 == 0)
			denom/= 5;
		return denom == 1;
	}

	@Override
	public AbstractRational divide(AbstractRational rational) {
		AbstractRational recip = rational.reciprocal();
		return multiply(recip);
	}

	@Override
	public boolean equals(AbstractRational rational) {
		boolean numerEquals = getNumerator() == rational.getNumerator();
		boolean denomEquals = getDenominator() == rational.getDenominator();
		return numerEquals&&denomEquals;
	}

	@Override
	public AbstractRational multiply(AbstractRational rational) {
		int numer1 = getNumerator();
		int numer2 = rational.getNumerator();
		int denom1 = getDenominator();
		int denom2 = rational.getDenominator();
		int newNum = numer1 * numer2;
		int newDenom = denom1 * denom2;
		Rational product = new Rational(newNum,newDenom);
		return product;
	}

	@Override
	public AbstractRational reciprocal() {
		int Numer = getDenominator();
		int denom = getNumerator();
		Rational flip = new Rational(Numer,denom);
		return flip;
	}

	@Override
	public void reduce() {
		if(getNumerator() == 0)
			setDenominator(1);
		else{
			int numer = getNumerator();
			int denom = getDenominator();
			int great = gcd(Math.abs(numer), Math.abs(denom));
			setNumerator(numer / great);
			setDenominator(denom / great);
		}

	}

	@Override
	public AbstractRational subtract(AbstractRational rational) {
		int num1 = getNumerator() * rational.getDenominator();
		int num2 = getDenominator() * rational.getNumerator();
		int denom = getDenominator() * rational.getDenominator();
		int finNum = num1 - num2;
		Rational ratio = new Rational(finNum, denom);
		return ratio;

	}


	@Override
	public String toString() {
		if(getDenominator() == 1)
			return "" + getNumerator();
		else
			return "" + getNumerator() + "/" + getDenominator();

	}



}
