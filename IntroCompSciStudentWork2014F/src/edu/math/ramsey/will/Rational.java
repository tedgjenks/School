package edu.math.ramsey.will;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {
	

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		if (denominator < 0){
			if (numerator < 0){
				numerator = Math.abs(numerator);
				denominator = Math.abs(denominator);
			}
			else{ 
				numerator *= -1;
				denominator = Math.abs(denominator);
			}
		}
		if(this.getNumerator() != 0){
			int gcd = gcd(numerator,denominator); 
			numerator /= gcd;
			denominator/= gcd;
		}
		this.setDenominator(denominator);
		this.setNumerator(numerator);
	}

	@Override
	public AbstractRational add(AbstractRational arg0) {
		// TODO Auto-generated method stub
		int n1 = this.getNumerator();
		int d1 = this.getDenominator();
		int newN = (n1 * arg0.getDenominator()) + (arg0.getNumerator() * d1);
		int newD = d1 * arg0.getDenominator();
		Rational sum = new Rational(newN,newD);
		return sum;
	}

	@Override
	public AbstractRational divide(AbstractRational arg0) {
		// TODO Auto-generated method stub\
		Rational dividend = new Rational(this.getNumerator(),this.getDenominator());
		arg0 = arg0.reciprocal();
		Rational quotient = (Rational) dividend.multiply(arg0);
		return quotient;
	}

	@Override
	public boolean equals(AbstractRational arg0) {
		// TODO Auto-generated method stub
		boolean equals = false;
		int n1 = this.getNumerator();
		int d1 = this.getDenominator();
		if(arg0.getNumerator() == n1 && arg0.getDenominator() == d1)
			equals = true;
		return equals;
	}

	@Override
	public AbstractRational multiply(AbstractRational arg0) {
		// TODO Auto-generated method stub
		int n1 = this.getNumerator();
		int d1 = this.getDenominator();
		int newN = n1 * arg0.getNumerator();
		int newD = d1 * arg0.getDenominator();
		Rational product = new Rational(newN,newD);
		return product;
	}

	@Override
	public AbstractRational reciprocal() {
		// TODO Auto-generated method stub
		int numerator = this.getDenominator();
		int denominator = this.getNumerator();
		Rational reciprocal = new Rational(numerator, denominator);
		return reciprocal;
	}

	@Override
	public void reduce() {
		if (this.getNumerator() != 0){
		int gcd = gcd(this.getNumerator(),this.getDenominator());
		int newN = this.getNumerator()/gcd;
		int newD = this.getDenominator()/gcd;
		this.setNumerator(newN);
		this.setDenominator(newD);
		}
	}

	@Override
	public AbstractRational subtract(AbstractRational arg0) {
		// TODO Auto-generated method stub
		int newN = arg0.getNumerator() * -1;
		Rational newArg = new Rational(newN, arg0.getDenominator());
		Rational difference = (Rational) this.add(newArg);
		return difference;
	}

	@Override
	public boolean decimalTerminates() {
		// TODO Auto-generated method stub
		boolean terminates = false;
		int check = this.getDenominator();
		while((check %2 == 0 ||  check %5 == 0) && check!=1){
			if(check % 2 == 0)
				check/=2;
			else if(check % 5 == 0)
				check/=5;
		}
		if (check == 1)
			terminates = true;
		return terminates;
	}
	public String toString() {
		String rationalString;
		if (this.getDenominator() == 1)
			rationalString = String.valueOf(this.getNumerator());
		else rationalString = (this.getNumerator() + "/" + this.getDenominator());
		return rationalString;
	}
}
