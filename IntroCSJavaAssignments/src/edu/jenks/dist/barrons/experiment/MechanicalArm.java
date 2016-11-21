/**
 * 
 */
package edu.jenks.dist.barrons.experiment;

/**
 * @author Ted Jenks
 *
 */
public interface MechanicalArm {
	/**
	 * @return the index of the current location of the mechanical arm.
	 */
	int getCurrentIndex();
	
	/**
	 * @return true if the mechanical arm is facing right (toward solutions with larger indexes), false if the mechanical arm is facing left (toward solutions with smaller indexes).
	 */
	boolean isFacingRight();
	
	/**
	 * Changes the current directions of the mechanical arm.
	 */
	void changeDirection();
	
	/**
	 * <p>Moves the mechanical arm forward in its current direction by the number of locations specified.</p>
	 * <b>Precondition:</b> <code>numLocs</code> &gt;= 0.
	 * @param numLocs the number of locations to move
	 */
	void moveForward(int numLocs);
}
