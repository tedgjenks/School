/**
 * 
 */
package edu.jenks.dist.math;

/**
 * <p>Create rational numbers and perform basic arithmetic with rational numbers.</p>
 * 
 * Rational numbers are written as one integer (numerator) divided by another nonzero integer (denominator).<br>
 * 
 * @author Ted Jenks
 *
 */
public abstract class AbstractRational implements Comparable<AbstractRational> {

	private int numerator, denominator;
	
	/**
	 * @param numerator
	 * @param denominator
	 * @throws IllegalArgumentException if denominator is zero.
	 */
	public AbstractRational(int numerator, int denominator) throws IllegalArgumentException {
		if(denominator == 0)
			throw new IllegalArgumentException("The denominator cannot be zero.");
		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * @param numerator
	 */
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	/**
	 * @param denominator
	 */
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	/**
	 * @return the numerator of this rational number.
	 */
	public int getNumerator ()
	{
		return numerator;
	}

	/**
	 * @return the denominator of this rational number.
	 */
	public int getDenominator ()
	{
		return denominator;
	}

	/**
	 * <b>precondition</b>: numerator is not zero.<br>
	 * @return the reciprocal of this rational number.
	 */
	public abstract AbstractRational reciprocal();

	/**
	 * <p>Adds this rational number to the one passed as a parameter.</p>
	 * 
	 * @param rn2
	 * @return the sum of this rational number and <code>rn2</code>.
	 */
	public abstract AbstractRational add(AbstractRational rn2);

	/**
	 * <p>Subtracts the rational number passed as a parameter from this rational number.</p>
	 * 
	 * @param rn2
	 * @return the difference of this rational number and <code>rn2</code>.
	 */
	public abstract AbstractRational subtract(AbstractRational rn2);

	/**
	 * <p>Multiplies this rational number by the one passed as a parameter.</p>
	 * 
	 * @param rn2
	 * @return the product of this rational number and <code>rn2</code>.
	 */
	public abstract AbstractRational multiply(AbstractRational rn2);

	/**
	 * <p>Divides this rational number by the one passed as a parameter.</p>
	 * 
	 * @param rn2
	 * @return the quotient of this rational number and <code>rn2</code>.
	 */
	public abstract AbstractRational divide(AbstractRational rn2);

	/**
	 * <p>Determines if this rational number is equal to the one passed as a parameter.</p>
	 * 
	 * @param rn2
	 * @return
	 */
	public abstract boolean equals(AbstractRational rn2);

	/**
	 * <p>Reduces this rational number.</p>
	 */
	public abstract void reduce();
	
	/**
	 * @return true if the decimal terminates in base 10, false if it repeats
	 */
	public abstract boolean decimalTerminates();

	/**
	 * <p>Computes and returns the greatest common divisor of the two parameters using Euclid's algorithm.</p>
	 * 
	 * <b>precondition</b>: <code>num1</code> and <code>num2</code> are greater than zero.<br>
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 * @throws IllegalArgumentException
	 */
	public int gcd(int num1, int num2) throws IllegalArgumentException {
		if(num1 == 0 || num2 == 0)
			throw new IllegalArgumentException("Arguments to method gcd must not be zero.");
		if(num1 < 0)
			num1 *= -1;
		if(num2 < 0)
			num2 *= -1;
		while (num1 != num2) {
			if (num1 > num2)
				num1 = num1 - num2;
			else
				num2 = num2 - num1;
		}
		return num1;
	}
}
