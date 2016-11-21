/**
 * 
 */
package edu.jenks.dist.barrons.experiment;

/**
 * @author Ted Jenks
 *
 */
public class MechanicalArmImpl implements MechanicalArm {
	private int currentIndex;
	private boolean facingRight;

	/**
	 * 
	 */
	public MechanicalArmImpl() {}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.experiment.MechanicalArm#getCurrentIndex()
	 */
	@Override
	public int getCurrentIndex() {
		return currentIndex;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.experiment.MechanicalArm#isFacingRight()
	 */
	@Override
	public boolean isFacingRight() {
		return facingRight;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.experiment.MechanicalArm#changeDirection()
	 */
	@Override
	public void changeDirection() {
		facingRight = !facingRight;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.experiment.MechanicalArm#moveForward(int)
	 */
	@Override
	public void moveForward(int numLocs) {
		currentIndex = facingRight ? currentIndex + numLocs : currentIndex - numLocs;
	}

}
