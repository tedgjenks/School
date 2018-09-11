//TODO - use AbstractRational parameters for geometric
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
	 * Relative delta for term comparison.
	 */
	public static final float RELATIVE_DELTA = 0.001f;
	
	/**
	 * @param d1
	 * @param d2
	 * @param delta
	 * @return true if the <b>relative</b> difference is less than or equal to <code>delta</code>
	 */
	public static boolean equalsRelative(double d1, double d2, double delta) {
		return Math.abs(d1 - d2) <= Math.max(Math.abs(d1),  Math.abs(d2)) * delta;
	}

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
	 * @param firstTerm
	 * @param lastTerm
	 * @param commonRatio the nth term divided by the n - 1 term
	 * @return the sum
	 */
	public abstract double sumGeometric(float firstTerm, float lastTerm, float commonRatio);
}
