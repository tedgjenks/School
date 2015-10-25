package edu.math.balentine.gryphon;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {
	
	public Rational(int numer, int denom) {
		super(numer, denom);
		if(denom<0){
			denom *= -1;
			numer *= -1;
		}
		setNumerator(numer);
		setDenominator(denom);
		reduce();
	}

	@Override
	public AbstractRational add(AbstractRational op2) {
		int numer1=getNumerator();
		int denom1=getDenominator();
		int numer2=op2.getNumerator();
		int denom2=op2.getDenominator();
		int commonDenom=denom1*denom2;
		int numer3=numer1*denom2;
		int numer4=numer2*denom1;
		int finalNumer=numer3+numer4;
		return new Rational(finalNumer, commonDenom);
	}

	@Override
	public AbstractRational divide(AbstractRational op2) {
		AbstractRational r=op2.reciprocal();
		return multiply(r);
	}

	@Override
	public boolean equals(AbstractRational rn2) {
		int numer1=getNumerator();
		int denom1=getDenominator();
		int numer2=rn2.getNumerator();
		int denom2=rn2.getDenominator();
		if(numer1==numer2 && denom1==denom2)
			return true;
		else return false;
	}

	@Override
	public AbstractRational multiply(AbstractRational op2) {
		int numer1=getNumerator();
		int denom1=getDenominator();
		int numer2=op2.getNumerator();
		int denom2=op2.getDenominator();
		int finalNum=numer1*numer2;
		int finalDen=denom1*denom2;
		return new Rational(finalNum, finalDen);
	}

	@Override
	public AbstractRational reciprocal() {
		int denom=getNumerator();
		int numer=getDenominator();
		return new Rational(numer, denom);
	}

	@Override
	public void reduce() {
		if(getNumerator() != 0){
			int numer=getNumerator();
			int denom=getDenominator();
			int gcd=gcd(numer, denom);
			int reducedNum=numer/gcd;
			int reducedDen=denom/gcd;
			setNumerator(reducedNum);
			setDenominator(reducedDen);
		}
	}

	@Override
	public AbstractRational subtract(AbstractRational op2) {
		int numer1=getNumerator();
		int denom1=getDenominator();
		int numer2=op2.getNumerator();
		int denom2=op2.getDenominator();
		int commonDenom=denom1*denom2;
		int numer3=numer1*denom2;
		int numer4=numer2*denom1;
		int finalNumer=numer3-numer4;
		return new Rational(finalNumer, commonDenom);
	}

	@Override
	public boolean decimalTerminates() {
		boolean terminates;
		int denom=getDenominator();
		while((denom%2)==0)
			denom /= 2;
		while((denom%5)==0)
			denom /= 5;
		if(denom==1)
			terminates=true;
		else
			terminates=false;
		return terminates;
	}
	
	public String toString() {
		String returnString="";
		int numer=getNumerator();
		int denom=getDenominator();
		if(denom!=1)
			returnString=numer + "/" + denom;
		else returnString=numer + "";
		return returnString;
	}
	
	public static void main(String[] args) {
	}

}
