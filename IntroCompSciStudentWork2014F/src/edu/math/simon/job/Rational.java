package edu.math.simon.job;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		// TODO Auto-generated constructor stub
		if (getDenominator() < 0) {
			setNumerator (getNumerator() * -1);
			setDenominator (getDenominator() * -1);
		}
		this.reduce();
	}
	
	@Override
	public AbstractRational add(AbstractRational RatNum) {
		// TODO Auto-generated method stub
		this.reduce();
		int Newden = getDenominator() * RatNum.getDenominator();
		int Newnum = getNumerator() * RatNum.getDenominator();
		int Newnum2 =  getDenominator() * RatNum.getNumerator();
		Rational ar = new  Rational(Newnum + Newnum2, Newden);
		return ar;
	}

	@Override
	public AbstractRational divide(AbstractRational RatNum) {
		// TODO Auto-generated method stub
		this.reduce();
		int Newnum = getNumerator() * RatNum.getDenominator();
		int Newden = RatNum.getDenominator() * RatNum.getNumerator();
		Rational ar = new  Rational(Newnum, Newden);

		return null;
	}

	@Override
	public boolean equals(AbstractRational arg0) {
		//  TODO Auto-generated method stub
		this.reduce();
		if (getNumerator() == arg0.getNumerator() && getDenominator() == arg0.getDenominator())
			return true;

		else 
			return false;

	}

	@Override
	public AbstractRational multiply(AbstractRational RatNum) {
		// TODO Auto-generated method stub
		this.reduce();
		int Newnum = getNumerator() * RatNum.getNumerator();
		int Newden = getDenominator() * RatNum.getDenominator();
		Rational ar = new  Rational(Newnum, Newden);

		return ar;
	}

	@Override
	public AbstractRational reciprocal() {
		// TODO Auto-generated method stub
		Rational newrat = new  Rational(getDenominator(), getNumerator());



		return newrat;
	}

	@Override
	public void reduce() {
		// TODO Auto-generated method stub
	
		int num = Math.abs(getNumerator());

		int den = Math.abs(getDenominator());
		if (num !=0) {
		int common = gcd(num,den);

		int result = getNumerator() / common;
		int result2 = getDenominator() / common;	
		setDenominator(result2);
		setNumerator(result);
		}
		else 
			setDenominator(1);
	
}
	@Override
	public AbstractRational subtract(AbstractRational RatNum) {
		// TODO Auto-generated method stub
		this.reduce();
		int Newden = getDenominator() * RatNum.getDenominator();
		int Newnum = getNumerator() * RatNum.getDenominator();
		int Newnum2 =  getDenominator() * RatNum.getNumerator();
		Rational ar = new  Rational(Newnum - Newnum2, Newden);
		return ar;
	}

	@Override
	public boolean decimalTerminates() {
		// TODO Auto-generated method stub
		int Denom = getDenominator() ;
		while (Denom % 2 == 0)
			Denom /= 2;
		while (Denom % 5 == 0)
			Denom /= 5;

		return (Denom == 1);
	}
	public String toString() {
		if (getDenominator() == 1)
			return getNumerator() + "";
		else 
			return (getNumerator() + "/" + getDenominator() );
	}
}
