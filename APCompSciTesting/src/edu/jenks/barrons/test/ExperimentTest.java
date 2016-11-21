package edu.jenks.barrons.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.barrons.experiment.AbstractExperiment;
import edu.jenks.test.Testable;

/**
 * @author Ted Jenks
 *
 */
public class ExperimentTest extends Testable {
	

	/**
	 * 
	 */
	public ExperimentTest() {}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 100;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentPackage + ".Experiment", AbstractExperiment.class.getName());
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub

	}

}
