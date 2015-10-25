package edu.math.mathis.justin;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational (int numer, int denom) throws java.lang.IllegalArgumentException {
		super(numer, denom);

		
		
		if (denom < 0){
			denom = denom*-1;
			numer = numer*-1;
			setDenominator(denom);
			setNumerator(numer);
		}
		this.reduce();
	}

	@Override
	public AbstractRational add(AbstractRational rational2) {
		int denom1 = this.getDenominator();
		int denom2 = rational2.getDenominator();
		int numer1 = this.getNumerator();
		int numer2 = rational2.getNumerator();
		int newdenom = denom1*denom2;
		int newnumer = (numer1*denom2)+(numer2*denom1);
		Rational sweg = new Rational(newnumer, newdenom);
		return sweg;

	}

	@Override
	public AbstractRational divide(AbstractRational rational2) {
		rational2 = rational2.reciprocal();
		AbstractRational sweg = this.multiply(rational2);
		return sweg;
	}

	@Override
	public boolean equals(AbstractRational arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AbstractRational multiply(AbstractRational r2) {
		int denom = this.getDenominator();
		int numer = this.getNumerator();
		int denom2 = r2.getDenominator();
		int numer2 = r2.getNumerator();
		int newdenom = denom*denom2;
		int newnumer = numer*numer2;
		Rational sweg = new Rational(newnumer, newdenom);
		return sweg;
	}

	@Override
	public AbstractRational reciprocal() {
		Rational sweg = new Rational(this.getDenominator(),this.getNumerator());
		return sweg;
	}

	@Override
	public void reduce() {
		int denom = this.getDenominator();
		int numer = this.getNumerator();		
		if (numer!=0){
			int gcd = gcd(numer, denom);
			setNumerator(numer/gcd);
			setDenominator(denom/gcd);
		}
		else {
			setDenominator(1);
		}
	}

	@Override
	public AbstractRational subtract(AbstractRational rational2) {
		int denom1 = this.getDenominator();
		int denom2 = rational2.getDenominator();
		int numer1 = this.getNumerator();
		int numer2 = rational2.getNumerator();
		int newdenom = denom1*denom2;
		int newnumer = (numer1*denom2)-(numer2*denom1);
		Rational sweg = new Rational(newnumer, newdenom);
		return sweg;

	}

	@Override
	public boolean decimalTerminates() {
		int denom = this.getDenominator();
		boolean a = false;
		while (denom%5==0)
			denom/=5;
		while (denom%2==0)
			denom/=2;
		if (denom==1)
			a = true;
		return a;
		// true = stops ||| false = keeps going
	}

	public String toString() {
		int numer = this.getNumerator();
		int  denom = this.getDenominator();
		if (numer == 0)
			return ""+0;
		if (denom!=1)
			return numer+"/"+denom;
		else return ""+numer;
	}

	public static void main(String[] args) {
		Rational r = new Rational(1, 2);
		Rational r2 = new Rational(1, -2);
		AbstractRational swag = r.multiply(r2);
		System.out.print(swag);

	}

}
