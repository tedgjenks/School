/**
 * 
 */
package edu.jenks.cb.climbingclub.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cb.climbingclub.jenks.ted.AbstractClimbInfo;
import edu.cb.climbingclub.jenks.ted.AbstractClimbingClub;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted
 *
 */
public class ClimbingClubTest extends Testable {
	
	private static final Class<?>[] CLIMB_INFO_CONSTRUCTOR_PARAM_TYPES = {String.class, int.class};
	
	private AbstractClimbingClub studentClimbingClubAlphabetical, studentClimbingClubChronological;
	//private AbstractClimbingClub solutionClimbingClubAlphabetical = , solutionClimbingClubChronological
	
	/**
	 * 
	 */
	public ClimbingClubTest() {}
	
	public void testAddClimbChronological() {
		studentClimbingClubChronological.addClimb("Monadnock", 274);
		studentClimbingClubChronological.addClimb("Whiteface", 301);
		studentClimbingClubChronological.addClimb("Algonquin", 225);
		studentClimbingClubChronological.addClimb("Monadnock", 344);
		//List<AbstractClimbInfo>
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 60;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentPackage + ".ClimbInfo", "edu.cb.climbingclub.jenks.ted.AbstractClimbInfo");
		map.put(studentPackage + ".ClimbingClubAlphabetical", "edu.cb.climbingclub.jenks.ted.AbstractClimbingClub");
		map.put(studentPackage + ".ClimbingClubChronological", "edu.cb.climbingclub.jenks.ted.AbstractClimbingClub");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		studentClimbingClubAlphabetical = (AbstractClimbingClub)ReflectionUtil.newInstance(studentPackage + ".ClimbingClubAlphabetical");
		studentClimbingClubChronological = (AbstractClimbingClub)ReflectionUtil.newInstance(studentPackage + ".ClimbingClubChronological");
		createClimbInfo("peak name", 10);
		totalPoints += 60;
	}
	
	private AbstractClimbInfo createClimbInfo(String peakName, int climbTime) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {peakName, climbTime};
		return (AbstractClimbInfo)ReflectionUtil.newInstance("edu.cb.climbingclub.jenks.ted.AbstractClimbInfo", CLIMB_INFO_CONSTRUCTOR_PARAM_TYPES, args);
	}

}
