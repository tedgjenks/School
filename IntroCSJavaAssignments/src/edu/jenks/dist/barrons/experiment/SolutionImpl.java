/**
 * 
 */
package edu.jenks.dist.barrons.experiment;

/**
 * @author Ted Jenks
 *
 */
public class SolutionImpl implements Solution {
	private int PH;

	/**
	 * 
	 */
	public SolutionImpl(int PH) {
		this.PH = PH;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.experiment.Solution#getPH()
	 */
	@Override
	public int getPH() {
		return PH;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.experiment.Solution#setPH(int)
	 */
	@Override
	public void setPH(int newValue) {
		PH = newValue;
	}

}
