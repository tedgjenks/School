package edu.math.jha.anish;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {

	public Rational(int numer, int denom) throws IllegalArgumentException {
		
		super( numer, denom );
		
		if (numer == 0)
			this.setNumerator(0);
	
		else if (numer > 0 && denom < 0){
			
			denom = Math.abs(denom);
			
			numer = numer * -1;
		
			this.setNumerator(numer);
			
			this.setDenominator(denom);
		
			reduce();
		
		}
		
		else if (numer < 0 && denom < 0){
				
			denom = Math.abs(denom);
			
			numer = Math.abs(numer);
		
			this.setNumerator(numer);
			
			this.setDenominator(denom);
		
			reduce();
		
		}
		
		else if (numer > 0 && denom > 0){
				
				denom = Math.abs(denom);
				
				numer = Math.abs(numer);
			
				this.setNumerator(numer);
				
				this.setDenominator(denom);
			
				reduce();
		}		
		
		else if (numer < 0 && denom > 0){
				
				this.setNumerator(numer);
					
				this.setDenominator(denom);
				
				reduce();
		
		} 
		this.toString();
		
	}

	
	@Override
	public AbstractRational add(AbstractRational rn2) {
		
		int numer = this.getNumerator();
		
		int denom = this.getDenominator();
		
		int numer2 = rn2.getNumerator();
		
		int denom2 = rn2.getDenominator();
	
		int cnm = numer * denom2;
		
		int cnm2 = numer2 * denom;

		int addns = cnm + cnm2;
		
		int cd = denom * denom2;
		
		Rational narn = new Rational(addns, cd);
		
		return narn;
		
	}

	
	@Override
	public AbstractRational divide(AbstractRational rn2) {
		
		AbstractRational r = rn2.reciprocal();
				
		return multiply(r);
	}

	
	@Override
	public boolean equals(AbstractRational rn2) {
		
		return false;
	}

	
	@Override
	public AbstractRational multiply(AbstractRational rn2) {
		
		int numer = this.getNumerator();
		
		int denom = this.getDenominator();
		
		int numer2 = rn2.getNumerator();
		
		int denom2 = rn2.getDenominator();
		
		int mn = numer * numer2;
		
		int md = denom * denom2; 
		
		Rational nrn = new Rational(mn, md);
		 
		return nrn;
	}

	
	@Override
	public AbstractRational reciprocal() {
		
		int numer2 = this.getNumerator();
		
		int denom2 = this.getDenominator();
		
		System.out.println(denom2+"/"+numer2);
		
		return null;
	}

	
	@Override
	public void reduce() {
		
		int numer = getNumerator();
		
		int denom = getDenominator();
		
		int diff = gcd(numer, denom);
		
		if(numer != 0){
		
		this.setNumerator(numer/diff);
	
		this.setDenominator(denom/diff);

	}
		else {
			setDenominator(1);
		}
}

	
	@Override
	public AbstractRational subtract(AbstractRational rn2) {
			
		int numer = this.getNumerator();
		
		int denom = this.getDenominator();
		
		int numer2 = rn2.getNumerator();
		
		int denom2 = rn2.getDenominator();
		
		int cnm = numer * denom2;
		
		int cnm2 = numer2 * denom;

		int subns = cnm - cnm2;
		
		int cd = denom * denom2;
		
		Rational srn = new Rational(subns , cd);
		
		return srn;
	}


	public String toString(){
	
		int numer = this.getNumerator();
		
		int denom = this.getDenominator();
		
		String rn = numer+"/"+denom;
		
		if (denom==1)
			
			rn = numer + "";
		
		if (numer == 0)
			
			rn = "0";
		
		return rn;
		
	}

	
	@Override
	public boolean decimalTerminates() {
		
		int numer = this.getNumerator();
		
		int denom = this.getDenominator();
		
		boolean eop = false;
		
		while(denom % 5 == 0)
			  
			  denom/=5;
		
		while(denom % 2 == 0)
			  denom/=2;
		
		if (denom == 1)
			
			eop = true;
		
		return eop;
	}
	
	
	public static void main(String[] args){
		
		Rational r1 = new Rational(-5,2);
		
		Rational r2 = new Rational(5,2);
		
		AbstractRational rn3 = r1.subtract(r2);
		
		System.out.println(r1);
	}
	
}
