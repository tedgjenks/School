package edu.math.hines.jonathan;

import edu.jenks.dist.math.AbstractRational;

public class Rational extends AbstractRational 
{

	public Rational(int numerator, int denominator) throws IllegalArgumentException 
	{
		super(numerator, denominator);
		if (getDenominator() < 0)
		{
			setNumerator(getNumerator() * -1);
			setDenominator(getDenominator() * -1);
		}
		this.reduce();
		this.toString();
	}

	@Override
	public AbstractRational add(AbstractRational rn2) 
	{
		this.reduce();
		int denominator = getDenominator() * rn2.getDenominator();
		int numerator1 = getDenominator() * rn2.getNumerator();
		int numerator2 = rn2.getDenominator() * getNumerator();
		Rational ar = new Rational((numerator1 + numerator2), denominator);
		return ar;
		
	}

	@Override
	public AbstractRational divide(AbstractRational rn2) 
	{
		this.reduce();
		int finNum = getNumerator() * rn2.getDenominator();
		int finDen = getDenominator() * rn2.getNumerator();
		Rational ar = new Rational(finNum, finDen);
		return ar;
	}

	@Override
	public boolean equals(AbstractRational rn2) 
	{
		return (getNumerator()== rn2.getNumerator() && getDenominator() == rn2.getDenominator());
	}

	@Override
	public AbstractRational multiply(AbstractRational rn2) 
	{
		this.reduce();
		int finNum = getNumerator() * rn2.getNumerator();
		int finDen = getDenominator() * rn2.getDenominator();
		Rational ar = new Rational(finNum, finDen);
		return ar;
	}

	@Override
	public AbstractRational reciprocal() 
	{
		this.reduce();
		AbstractRational ar = new Rational(getDenominator() , getNumerator());
		return ar;
	}

	@Override
	public void reduce() 
	{
		if (getNumerator() == 0)
			setDenominator(1);
		else if (getNumerator() != 1)
		{
			int gCF = gcd(Math.abs(getNumerator()), getDenominator());
			int reducedNumerator = getNumerator() / gCF;
			int reducedDenominator = getDenominator() / gCF;
			if (reducedDenominator < 0)
			{
				setNumerator(getNumerator() * -1);
				setDenominator(getDenominator() * -1);
			}
			this.setNumerator(reducedNumerator);
			this.setDenominator(reducedDenominator);
		}
		
	}

	@Override
	public AbstractRational subtract(AbstractRational rn2) 
	{
		int denominator = getDenominator() * rn2.getDenominator();
		int numerator1 = getDenominator() * rn2.getNumerator();
		int numerator2 = rn2.getDenominator() * getNumerator();
		Rational ar = new Rational((numerator2 - numerator1), denominator);
		return ar;
	}

	public String toString()
	{
		if (getDenominator() == 1)
			return "" + getNumerator();
		else 
			return "" + getNumerator() + "/" + getDenominator();
	}	
	
 	@Override
	public boolean decimalTerminates() 
 	{
		int den = getDenominator();
		while(den % 2 == 0)
			den /= 2;
		while(den % 5 == 0)
			den /= 5;
		return (den == 1);
	}

}

	