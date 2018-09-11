/**
 * 
 */
package edu.math.jenks.ted;

import edu.jenks.dist.math.AbstractSeries;

/**
 * @author Ted Jenks
 *
 */
public class Series extends AbstractSeries {

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractSeries#sumArithmetic(int, int, int)
	 */
	@Override
	public int sumArithmetic(int firstTerm, int lastTerm, int commonDifference) {
		int sum = firstTerm;
		for(int term = lastTerm; term != firstTerm; term -= commonDifference)
			sum += term;
		return sum;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractSeries#sumGeometric(float, float, float)
	 */
	@Override
	public double sumGeometric(float firstTerm, float lastTerm, float commonRatio) {
		double sum = firstTerm;
		for(double term = lastTerm; !equalsRelative(firstTerm, term, RELATIVE_DELTA); term /= commonRatio)
			sum += term;
		return sum;
	}

}
