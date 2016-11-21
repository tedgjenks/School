/**
 * 
 */
package edu.barrons.jenks.ted;

import java.util.List;

import edu.jenks.dist.barrons.experiment.AbstractExperiment;
import edu.jenks.dist.barrons.experiment.MechanicalArm;
import edu.jenks.dist.barrons.experiment.Solution;

/**
 * @author Ted Jenks
 *
 */
public class Experiment extends AbstractExperiment {

	/**
	 * @param arm
	 * @param solutions
	 */
	public Experiment(MechanicalArm arm, List<Solution> solutions) {
		super(arm, solutions);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.experiment.AbstractExperiment#reset()
	 */
	@Override
	public void reset() {
		if(arm.isFacingRight()) // face arm left
			arm.changeDirection();
		arm.moveForward(arm.getCurrentIndex()); // move arm to index 0
		arm.changeDirection(); // face arm right
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.experiment.AbstractExperiment#mostAcidic()
	 */
	@Override
	public int mostAcidic() {
		reset();
		int mostAcidicIndex = 0, mostAcidicPH = solutions.get(mostAcidicIndex).getPH();
		for(int curIndex = solutions.size(); curIndex >= 0; curIndex--) {
			int curPH = solutions.get(curIndex).getPH();
			if(curPH < 7 && curPH < mostAcidicPH) {
				mostAcidicPH = curPH;
				mostAcidicIndex = curIndex;
			}
				
		}
		arm.moveForward(mostAcidicIndex);
		return mostAcidicIndex;
	}

}
