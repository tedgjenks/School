package edu.math.slimmer.ben;

import edu.jenks.dist.math.AbstractRational;

public class Rational
extends AbstractRational {
	
	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		super(numerator,denominator);
		if(denominator ==0)
			throw new IllegalArgumentException();
		if (denominator<0){
			this.setNumerator(numerator*-1);
			this.setDenominator(denominator*-1);
		}
		reduce();
	}

	@Override
	public AbstractRational add(AbstractRational arg0) {
		int newdenom= arg0.getDenominator()*this.getDenominator();
		int newnum= (this.getNumerator()*(newdenom/this.getDenominator()))+(arg0.getNumerator()*(newdenom/arg0.getDenominator()));
		Rational returnrat= new Rational(newnum,newdenom);
		returnrat.reduce();
		return returnrat;
	}

	@Override
	public AbstractRational divide(AbstractRational arg0) {
		int newnum= this.getNumerator()*arg0.getDenominator();
		int newdenom= this.getDenominator()*arg0.getNumerator();
		Rational returnrat= new Rational(newnum,newdenom);
		returnrat.reduce();
		return returnrat;
	}

	@Override
	public boolean equals(AbstractRational arg0) {
		this.reduce();
		arg0.reduce();
		if(this.getNumerator()==arg0.getNumerator()&&this.getDenominator()==arg0.getDenominator())
			return true;
		else
			return false;
	}

	@Override
	public AbstractRational multiply(AbstractRational arg0) {
		int newnum= this.getNumerator()*arg0.getNumerator();
		int newdenom= this.getDenominator()*arg0.getDenominator();
		Rational returnrat= new Rational(newnum,newdenom);
		returnrat.reduce();
		return returnrat;
	}

	@Override
	public AbstractRational reciprocal() {
		int tempnum=this.getNumerator();
		int tempdenom=this.getDenominator();
		Rational returnrat= new Rational(tempdenom,tempnum);
		returnrat.reduce();
		return returnrat;
	}

	@Override
	public void reduce() {
		boolean negate=false; 
		if(getNumerator()<0){
			negate=true;
			setNumerator(getNumerator()*-1);
		}
		int factor;
		if(getNumerator()==0)
			setDenominator(1);
		else {
			while(gcd(getNumerator(),getDenominator())>1){
				factor= gcd(getNumerator(), getDenominator());
				this.setNumerator(getNumerator()/factor);
				this.setDenominator(getDenominator()/factor);
			}
			if(negate==true)
				this.setNumerator(getNumerator()*-1);
		}
	}

	@Override
	public AbstractRational subtract(AbstractRational arg0) {
		int newdenom= arg0.getDenominator()*this.getDenominator();
		int newnum= (this.getNumerator()*(newdenom/this.getDenominator()))-(arg0.getNumerator()*(newdenom/arg0.getDenominator()));
		Rational returnrat= new Rational(newnum,newdenom);
		returnrat.reduce();
		return returnrat;
	}

	@Override
	public boolean decimalTerminates() {
		int denom= getDenominator();
		while (denom%2==0)
			denom/=2;
		while (denom%5==0)
			denom/=5;
		return denom==1;
	}
	
	public String toString(){
		if (getDenominator()==1)
			return  ""+getNumerator();
		else
			return getNumerator()+"/"+getDenominator();
	}

}
