package edu.math.mariscal.juan;


import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational {
	
	
	
	public Rational (int numer, int denom) throws java.lang.IllegalArgumentException{
		super( numer,  denom);
		
		if (numer == 0){
			this.setNumerator(0);
			
		}
		else if (numer > 0 && denom < 0){
			denom= Math.abs(denom);
			numer= numer * -1;
			this.setNumerator(numer);
			this.setDenominator(denom);
			reduce();
		}
		else if (numer < 0 && denom <0){
			denom = Math.abs(denom);
			numer = Math.abs(numer);
			this.setNumerator(numer);
			this.setDenominator(denom);
			reduce();
		}
		else if (numer > 0 && denom >0){
			denom = Math.abs(denom);
			numer = Math.abs(numer);
			this.setNumerator(numer);
			this.setDenominator(denom);
			reduce();
		}
		else if (numer < 0 && denom >0){
			
			this.setNumerator(numer);
			this.setDenominator(denom);
			reduce();
		}
		this.toString();
	}
	@Override
	public AbstractRational add(AbstractRational rn2) {
		// TODO Auto-generated method stub
		int denom = this.getDenominator();
		int odenom=rn2.getDenominator();
		int cd = denom * odenom;
		int numer = this.getNumerator();
		int onumer = rn2.getNumerator();
		numer= numer * odenom;
		onumer=onumer * denom;
		int newnumer = numer + onumer;
		Rational ha = new Rational(newnumer, cd);
		
		return ha;
	}

	@Override
	public AbstractRational divide(AbstractRational rn2) {
		// TODO Auto-generated method stub
		
		
		AbstractRational ha = this.multiply(rn2.reciprocal());
		
		return ha;
	}

	@Override
	public boolean equals(AbstractRational rn2) {
		// TODO Auto-generated method stub
		
		return (this.getNumerator()==rn2.getNumerator() && this.getDenominator() == rn2.getDenominator());
	}

	@Override
	public AbstractRational multiply(AbstractRational rn2) {
		// TODO Auto-generated method stub
		int denom = this.getDenominator();
		int odenom=rn2.getDenominator();
		int numer = this.getNumerator();
		int onumer = rn2.getNumerator();
		int newnumer= numer * onumer;
		int newdenom=denom*odenom;
		
		Rational ha = new Rational(newnumer, newdenom);
		
		return ha;
	}

	@Override
	public AbstractRational reciprocal() {
		// TODO Auto-generated method stub
		int numer =this.getNumerator();
		int denom=this.getDenominator();
		setNumerator(denom);
		setDenominator(numer);
		return new Rational(this.getNumerator(), this.getDenominator());
	}

	@Override
	public void reduce() {
		// TODO Auto-generated method stub
		
		int numer=getNumerator();
		int denom=getDenominator();
		if(numer != 0){
		int dif = gcd(numer, denom);
		this.setNumerator(numer/dif);
		this.setDenominator(denom/dif);
		}
		else {
			setDenominator(1);
		}
	}

	@Override
	public AbstractRational subtract(AbstractRational rn2) {
		// TODO Auto-generated method stub
		int denom = this.getDenominator();
		int odenom=rn2.getDenominator();
		int cd = denom * odenom;
		int numer = this.getNumerator();
		int onumer = rn2.getNumerator();
		numer= numer * odenom;
		onumer=onumer * denom;
		int newnumer = numer - onumer;
		Rational ha = new Rational(newnumer, cd);
		
		return ha;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rational ra1 = new Rational(-1, 3);
		Rational ra2 = new Rational(1, 1);
		AbstractRational act = ra1.subtract(ra2);
		System.out.print(act);
	}
	@Override
	public boolean decimalTerminates() {
		// TODO Auto-generated method stub
		int denom = this.getDenominator();
		boolean end = false;
		while(denom % 5 ==0)
			denom/=5;
		while (denom%2==0)
			denom/=2;
		if(denom ==1)
		
			end = true;
		
		return end;
	}
	public String toString(){
		int numer = this.getNumerator();
		int denom = this.getDenominator();
		
		String abrat = numer + "/" + denom;
		if (denom==1)
			abrat= numer + "";
		if(numer ==0)
			abrat= "0";
		return abrat;
	}
}
