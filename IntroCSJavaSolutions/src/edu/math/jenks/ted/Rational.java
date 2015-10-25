/**
 * 
 */
package edu.math.jenks.ted;

import edu.jenks.dist.math.AbstractRational;

/**
 * @author Ted Jenks
 *
 */
public class Rational extends AbstractRational {

	/**
	 * <p>Create a reduced rational number.</p>
	 * 
	 * <b>precondition</b>: <code>denom</code> is not zero.<br>
	 * <b>postconditions</b>:<br>
	 *  - The numerator designates the sign.<br>
	 *  - The rational number is reduced (greatest common factor of the numerator and denominator is one).<br>
	 * @param numer the numerator
	 * @param denom the denominator
	 * @throws IllegalArgumentException if <code>denom</code> is zero.
	 */
	public Rational (int numer, int denom) throws IllegalArgumentException {
		super(numer, denom);

		// Make the numerator "store" the sign
		if (denom < 0) {
			numer = numer * -1;
			denom = denom * -1;
		}

		setNumerator(numer);
		setDenominator(denom);
		reduce();
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractRational#reciprocal()
	 */
	@Override
	public AbstractRational reciprocal() {
		return new Rational (getDenominator(), getNumerator());
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractRational#add(edu.jenks.dist.math.AbstractRational)
	 */
	@Override
	public AbstractRational add(AbstractRational op2) {
		int commonDenominator = getDenominator() * op2.getDenominator();
		int numerator1 = getNumerator() * op2.getDenominator();
		int numerator2 = op2.getNumerator() * getDenominator();
		int sum = numerator1 + numerator2;
		return new Rational(sum, commonDenominator);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractRational#subtract(edu.jenks.dist.math.AbstractRational)
	 */
	@Override
	public AbstractRational subtract(AbstractRational op2) {
		Rational negatedR = negate(op2);
		return add(negatedR);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractRational#multiply(edu.jenks.dist.math.AbstractRational)
	 */
	@Override
	public AbstractRational multiply(AbstractRational op2) {
		int numer = getNumerator() * op2.getNumerator();
		int denom = getDenominator() * op2.getDenominator();
		return new Rational (numer, denom);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractRational#divide(edu.jenks.dist.math.AbstractRational)
	 */
	@Override
	public AbstractRational divide(AbstractRational op2) {
		return multiply(op2.reciprocal());
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractRational#equals(edu.jenks.dist.math.AbstractRational)
	 */
	@Override
	public boolean equals(AbstractRational rn2) {
		reduce();
		rn2.reduce();
		return getNumerator() == rn2.getNumerator() && getDenominator() == rn2.getDenominator();
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractRational#reduce()
	 */
	@Override
	public void reduce() {
		int numerator = getNumerator();
		int denominator = getDenominator();
		if (numerator != 0) {
			int common = gcd (Math.abs(numerator), denominator);
			numerator = numerator / common;
			setNumerator(numerator);
			denominator = denominator / common;
			setDenominator(denominator);
		}
	}
	
	/** 
	 * <p>Represent the rational number as a String.</p>
	 * 
	 * Integers should be [numerator]<br>
	 * Non-integers should be [numerator]/[denominator]
	 */
	public String toString () {
		reduce();
		int numer = getNumerator();
		StringBuilder result = new StringBuilder().append(numer);
		int denominator = getDenominator();
		if(denominator != 1 && numer != 0)
			result.append("/").append(denominator);
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractRational#decimalTerminates()
	 */
	@Override
	public boolean decimalTerminates() {
		reduce();
		int quotient = getDenominator();
		boolean onlyTerminatingFactors = true; // no factors other than 2 and 5 are found
		if(getNumerator() != 0 && quotient != 1) {
			for(int i = 2; i <= quotient / i && onlyTerminatingFactors; i++) {
				while(quotient % i == 0 && onlyTerminatingFactors) {
					if(i != 2 && i != 5)
						onlyTerminatingFactors = false;
					quotient /= i;
				}
			}
			if(quotient > 1 && quotient != 2 && quotient != 5)
				onlyTerminatingFactors = false;
		}
		return onlyTerminatingFactors;
	}
	
	private Rational negate(AbstractRational r) {
		int negatedNumerator = r.getNumerator() * -1;
		return new Rational(negatedNumerator, r.getDenominator());
	}
	
}
