/**
 * 
 */
package edu.jenks.dist.math;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractSeries {

	/**
	 * <p>Sums the number from <code>firstTerm</code> to <code>lastTerm</code></p>
	 * @param firstTerm
	 * @param lastTerm
	 * @param commonDifference the nth term minus the n - 1 term
	 * @return the sum
	 */
	public abstract int sumArithmetic(int firstTerm, int lastTerm, int commonDifference);
	
	/**
	 * <p>Sums the number from <code>firstTerm</code> to <code>lastTerm</code></p>
	 * <b>precondition:</b> All terms are integers.
	 * @param firstTerm
	 * @param lastTerm
	 * @param commonRatio the nth term divided by the n - 1 term
	 * @return the sum
	 */
	public abstract int sumGeometric(int firstTerm, int lastTerm, int commonRatio);
}
