package edu.math.ruhoff.brooke;

import edu.jenks.dist.math.AbstractRational;

public  class Rational extends AbstractRational {

	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator, denominator);
		int denom=getDenominator();
		int numer=getNumerator();
		if (denom<0){
			denom=getDenominator()*-1;
			numer=getNumerator()*-1;
		}
		setNumerator(numer);
		setDenominator(denom);
		reduce();
		
	}

	@Override
	public AbstractRational add(AbstractRational rn2) {
		int numer=getNumerator();
		int denom=getDenominator();
		int rn2Denom=rn2.getDenominator();
		int rn2Numer=rn2.getNumerator();
		int gcd=denom * rn2Denom;
		int sumNumer = (numer*rn2Denom) + (rn2Numer * denom);
		Rational end= new Rational(sumNumer, gcd);
		return end;
	}


	@Override
	public AbstractRational divide(AbstractRational rn2) {
		int quotient=((getDenominator()/getNumerator()) * (rn2.getNumerator() / rn2.getDenominator()));
		int quotientDenom=getNumerator();
		Rational end= new Rational(quotientDenom, quotient);
		return end;
	}

	@Override
	public boolean equals(AbstractRational rn2) {
		if((getNumerator()/getDenominator())==rn2.getNumerator()/rn2.getDenominator()){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public AbstractRational multiply(AbstractRational rn2) {
		int rn2Denominator=rn2.getDenominator();
		int rn2Numerator=rn2.getNumerator();
		int product=((rn2Numerator*getNumerator())/(rn2Denominator*getDenominator()));
		Rational end= new Rational((rn2Numerator*getNumerator()), (rn2Denominator*getDenominator()));
		return end;
	}


	@Override
	public AbstractRational reciprocal() {
		int newNumer=(getDenominator());
		int newDenom=(getNumerator());
		Rational end= new Rational(newNumer, newDenom);
		return end;
	}

	@Override
	public void reduce() {
		int numerator=this.getNumerator();
		int denominator=this.getDenominator();
		if (getNumerator()!=0){
			int reducedNum=gcd(numerator, denominator); 
		setNumerator(numerator/reducedNum);
		setDenominator(denominator/reducedNum);
		}
		
	}
	@Override
	public AbstractRational subtract(AbstractRational rn2) {
		int numer=getNumerator();
		int denom=getDenominator();
		int rn2Denom=rn2.getDenominator();
		int rn2Numer=rn2.getNumerator();
		int gcd=denom * rn2Denom;
		int difNumer = (numer*rn2Denom) - (rn2Numer * denom);
		Rational end= new Rational(difNumer, gcd);
		return end;
	}
	@Override
	public boolean decimalTerminates() {
		if((getDenominator()%5==0)||(getDenominator()%2==0))
			return true;
		else return false;
	}
	public String toString(){
		if (getNumerator()==0){
			return "0";
		}
		else if (getDenominator()==1){
			return getNumerator() + "";
		}
		else{
			return getNumerator() + "/" + getDenominator();
		}
		
	}
	
}