package edu.math.collier.serenity;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		int denom=denominator;
		int numer=numerator;
		if(denom<0){
			denom=denom*-1;
			numer=numer*-1;
		}
		setNumerator(numer);
		setDenominator(denom);
		reduce();
	}

	@Override
	public AbstractRational add(AbstractRational rn2) {
		int Denom=rn2.getDenominator(); 
		int Numer=rn2.getNumerator();
		int rn2Denom=rn2.getDenominator();
		int rn2Numer=rn2.getNumerator();
		int gcd=Denom*rn2Denom;
		int sumNumer = (Numer*rn2Denom)+( rn2Numer*Denom);
		Rational ends=new Rational(sumNumer, gcd);
		return ends;
	}

	@Override
	public AbstractRational divide(AbstractRational rn2) {
		int quotient=((getDenominator()/getNumerator())*(rn2.getNumerator()/ rn2.getDenominator()));
		int quotientDenom= getNumerator();
		int quotientNumer= getDenominator();
		Rational ends=new Rational(quotient, quotientDenom);
		return ends;
	}

	@Override
	public boolean equals(AbstractRational rn2) {
		if((getNumerator()/(getDenominator())==(rn2.getNumerator()/rn2.getDenominator()))){
		return true;
		}
		else{
			return false; 
		}
	}

	@Override
	public AbstractRational multiply(AbstractRational rn2) {
		int rn2Numerator=rn2.getNumerator();
		int rn2Denominator=rn2.getDenominator();
		int product= ((rn2Numerator*getNumerator())/(rn2Denominator*getDenominator()));
		Rational ends=new Rational(rn2Numerator*getNumerator(), (rn2Denominator*getDenominator()));
		return ends;
	}

	@Override
	public AbstractRational reciprocal() {
		int newNumer=(getDenominator());
		int newDenom=(getNumerator());
		Rational ends=new Rational(getNumerator(), getDenominator());
		return ends;
	}

	@Override
	public void reduce() {
		int numerator= this.getNumerator();
		int denominator= this.getDenominator();
		int reducedNum=gcd(numerator, denominator);
		setNumerator(numerator/reducedNum);
		setDenominator(denominator/reducedNum);
		if (numerator!=0){
			
		}
	}

	@Override
	public AbstractRational subtract(AbstractRational rn2) {
		int Denom=rn2.getDenominator(); 
		int Numer=rn2.getNumerator();
		int rn2Denom=rn2.getDenominator();
		int rn2Numer=rn2.getNumerator();
		int gcd=Denom*rn2Denom;
		int sumNumer = (Numer*rn2Denom)-( rn2Numer*Denom);
		Rational ends=new Rational(sumNumer, gcd);
		return ends;
		
	}

	@Override
	public boolean decimalTerminates() {
		int num= (getNumerator()/getDenominator());
		if (num==getNumerator()/getDenominator()){
			return true; 
		}
		else{
		return false;
		}
	}
	public String toString(){
		
		if (getDenominator()==1){
			return getNumerator() + "";
		}
		else{
			return getNumerator() + "/" + getDenominator(); 
		}
	}
	
	
}
