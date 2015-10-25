package edu.math.ball.daniel;

import edu.jenks.dist.math.AbstractRational;



public class Rational extends AbstractRational 


{
	int num = 0;
	int den = 0;
	public Rational(int numerator, int denominator) throws IllegalArgumentException 
	{
		super(numerator, denominator);
		setNumerator(numerator);
		setDenominator(denominator);
		reduce();
		setNumerator(num);
		setDenominator(den);
	}

	@Override
	public AbstractRational add(AbstractRational rational) 
	{
		int num1 = this.getNumerator();
		int num2 = rational.getNumerator();
		int den1 = this.getDenominator();
		int den2 = rational.getDenominator();
		
		int finalDen = 0;
		int finalNum = 0;
		
		if (den1 == den2)
			{
				finalDen = den1;
				finalNum = num1 + num2;
			}
		else
			{
				finalDen = den1 * den2;
				num1 *= den2;
				num2 *= den1;
				finalNum = num1 + num2;
			}
		Rational r = new Rational(finalNum,finalDen);
		r.reduce();
		return r;
	}

	@Override
	public AbstractRational divide(AbstractRational rational) 
	{
		rational.reciprocal();
		int num1 = this.getNumerator();
		int num2 = rational.getNumerator();
		int den1 = this.getDenominator();
		int den2 = rational.getDenominator();
		
		int finalDen = 0;
		int finalNum = 0;
		
		finalNum = num1 * num2;
		finalDen = den1 * den2;
		
		Rational r = new Rational(finalNum,finalDen);
		r.reduce();
		return r;
	}

	@Override
	public boolean equals(AbstractRational rational) 
	{
		this.reduce();
		rational.reduce();
		int num1 = this.getNumerator();
		int num2 = rational.getNumerator();
		int den1 = this.getDenominator();
		int den2 = rational.getDenominator();
		
		if ((num1 == num2) && (den1 == den2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public AbstractRational multiply(AbstractRational rational) 
	{
		int num1 = this.getNumerator();
		int num2 = rational.getNumerator();
		int den1 = this.getDenominator();
		int den2 = rational.getDenominator();
		
		int finalDen = 0;
		int finalNum = 0;
		
		finalNum = num1 * num2;
		finalDen = den1 * den2;
		
		Rational r = new Rational(finalNum,finalDen);
		r.reduce();
		return r;
	}

	@Override
	public AbstractRational reciprocal() 
	{
		this.reduce();
		int num1 = this.getNumerator();
		int den1 = this.getDenominator();
		this.setNumerator(den1);
		this.setDenominator(num1);
		Rational r = new Rational(this.getNumerator(),this.getDenominator());
		r.reduce();
		return r;
	}

	@Override
	public void reduce() 
	{
		num = this.getNumerator();
		den = this.getDenominator();
		int common = 1;
		if (num != 0)
		{
			common = gcd((Math.abs(num)),(Math.abs(den)));
		}
		
		if (num < 0 && den < 0)
			{
			num = -num/common;
			den = -den/common;
			//System.out.println(num);
			//System.out.println(den);
			this.setNumerator(num);
			this.setDenominator(den);
			}
		else
		if (num > 0 && den < 0)
			{
			num = -num/common;
			den = -den/common;
			//System.out.println(num);
			//System.out.println(den);
			this.setNumerator(num);
			this.setDenominator(den);
			}
		else
		if (num > 0 && den > 0)
			{
			num = num/common;
			den = den/common;
			//System.out.println(num);
			//System.out.println(den);
			this.setNumerator(num);
			this.setDenominator(den);
			}
		else
		if (num < 0 && den > 0)
			{
			num = num/common;
			den = den/common;
			//System.out.println(num);
			//System.out.println(den);
			this.setNumerator(num);
			this.setDenominator(den);
			}
	}

	@Override
	public AbstractRational subtract(AbstractRational rational) 
	{
		int num1 = this.getNumerator();
		int num2 = rational.getNumerator();
		int den1 = this.getDenominator();
		int den2 = rational.getDenominator();
		
		int finalDen = 0;
		int finalNum = 0;
		
		if (den1 == den2)
			{
				finalDen = den1;
				finalNum = num1 - num2;
			}
		else
			{
				finalDen = den1 * den2;
				num1 *= den2;
				num2 *= den1;
				finalNum = num1 - num2;
			}
		if (finalNum != 0)
		{
			Rational r = new Rational(finalNum,finalDen);
			r.reduce();
			return r;
		}
		else
		{
			System.out.println("0");
			Rational r = new Rational(0,1);
			return r;
		}
	}

	public static void main(String[] args)
	{
		
	}

	@Override
	public boolean decimalTerminates() 
	{
		int den1 = this.getDenominator();
		while (den1 % 2 == 0)
		{
			den1 /= 2;
		}
		while (den1 % 5 == 0)
		{
			den1 /= 5;
		}
			if (den1 == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
	}
	
	public String toString()
	{
		String returnString = ("");
		int num1 = this.getNumerator();
		int den1 = this.getDenominator();
		if (den1 < 0)
		{
			num1 *= -1;
			den1 *= -1;
		}
			
		if (den1 != 1)
		{
			returnString = (num1 + "/" + den1);
		}
		else
		{
			returnString = ("" + num1);
		}
		return returnString;
	}
	
}

