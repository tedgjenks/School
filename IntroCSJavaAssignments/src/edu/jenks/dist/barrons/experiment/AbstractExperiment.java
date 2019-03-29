/**
 * 
 */
package edu.jenks.dist.barrons.experiment;

import java.util.List;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractExperiment {
	/**
	 * The mechanical arm used to process the solutions.
	 */
	protected MechanicalArm arm = new MechanicalArmImpl();
	
	/**
	 * The list of solutions.
	 */
	protected List<Solution> solutions;
	
	/**
	 * Persists <code>arm</code> and <code>solutions</code>, then calls <code>reset()</code>.
	 * 
	 * @param arm
	 * @param solutions
	 */
	public AbstractExperiment(MechanicalArm arm, List<Solution> solutions) {
		this.arm = arm;
		this.solutions = solutions;
		reset();
	}

	/**
	 * <p>Resets the experiment.</p>
	 * <b>Postcondition:</b><br>
	 * - The mechanical arm has a current index of 0.
	 * - It is facing right.
	 */
	public abstract void reset();
	
	/**
	 * <p>Finds and returns the index of the most acidic solution.</p>
	 * <b>Postcondition:</b><br>
	 *  - The mechanical arm is facing right.
	 *  - Its current index is at the most acidic solution, or at 0 if there are no acidic solutions.
	 * @return index the location of the most acidic solution or -1 if there are no acidic solutions.
	 */
	public abstract int mostAcidic();

}
