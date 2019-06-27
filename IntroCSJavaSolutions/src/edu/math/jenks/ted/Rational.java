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
	public Rational (long numer, long denom) throws IllegalArgumentException {
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
		long commonDenominator = getDenominator() * op2.getDenominator();
		long numerator1 = getNumerator() * op2.getDenominator();
		long numerator2 = op2.getNumerator() * getDenominator();
		long sum = numerator1 + numerator2;
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
		long numer = getNumerator() * op2.getNumerator();
		long denom = getDenominator() * op2.getDenominator();
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
		long numerator = getNumerator();
		long denominator = getDenominator();
		if (numerator != 0) {
			long common = gcd (Math.abs(numerator), denominator);
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
		long numer = getNumerator();
		StringBuilder result = new StringBuilder().append(numer);
		long denominator = getDenominator();
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
		long quotient = getDenominator();
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
		long negatedNumerator = r.getNumerator() * -1;
		return new Rational(negatedNumerator, r.getDenominator());
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AbstractRational arg0) {
		long retVal = 0;
		AbstractRational difference = subtract(arg0);
		retVal = difference.getNumerator();
		return (int)Math.min(Math.max(retVal, Integer.MIN_VALUE), Integer.MAX_VALUE);
	}
	
	public static void main(String[] args) {
		System.out.println("start");
		AbstractRational nR1 = new Rational(-33, 2);
		AbstractRational nR2 = new Rational(-19, 2);
		AbstractRational pR1 = new Rational(33, 7);
		AbstractRational pR2 = new Rational(83, 7);
		assert nR1.compareTo(nR2) < 0;
		assert nR2.compareTo(nR1) > 0;
		assert nR1.compareTo(pR1) < 0;
		assert pR1.compareTo(nR1) > 0;
		assert pR1.compareTo(pR2) < 0;
		assert pR2.compareTo(pR1) > 0;
		assert pR1.compareTo(pR1) == 0;
		pR1 = new Rational(143, 343);
		nR1 = new Rational(68, -193);
		System.out.println(pR1.compareTo(nR1));
		System.out.println("end without error");
	}
}
