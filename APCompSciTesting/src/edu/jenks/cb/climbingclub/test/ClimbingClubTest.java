/**
 * 
 */
package edu.jenks.cb.climbingclub.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;

import edu.cb.climbingclub.jenks.ted.ClimbingClubAlphabetical;
import edu.cb.climbingclub.jenks.ted.ClimbingClubChronological;
import edu.jenks.dist.cb.climbingclub.*;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted
 *
 */
public class ClimbingClubTest extends Testable {
	
	private static final Class<?>[] CLIMB_INFO_CONSTRUCTOR_PARAM_TYPES = {String.class, int.class};
	
	private AbstractClimbingClub studentClimbingClubAlphabetical, studentClimbingClubChronological;
	private AbstractClimbingClub solutionClimbingClubAlphabetical = new ClimbingClubAlphabetical();
	private AbstractClimbingClub solutionClimbingClubChronological = new ClimbingClubChronological();
	
	/**
	 * 
	 */
	public ClimbingClubTest() {}
	
	public void testDistinctPeakNames() {
		clearClimbLists();
		addStandardClimbs(studentClimbingClubChronological);
		addStandardClimbs(studentClimbingClubAlphabetical);
		int expected = 3;
		if(studentClimbingClubChronological.distinctPeakNames() == expected && studentClimbingClubAlphabetical.distinctPeakNames() == 3) {
			feedbackLogger.log(Level.INFO, "Passed distinct peak names.");
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.WARNING, "Failed distinct peak names.");
	}
	
	public void testClimbInfo() {
		boolean passed = false;
		try {
			AbstractClimbInfo ci1 = createClimbInfo("Alpha", 100);
			AbstractClimbInfo ci2 = createClimbInfo("Alpha", 200);
			AbstractClimbInfo ci3 = createClimbInfo("Beta", 100);
			if(ci1.compareTo(ci2) == 0 && ci1.compareTo(ci3) < 0 && ci3.compareTo(ci1) > 0)
				passed = true;
		} catch (Exception e) {
			feedbackLogger.log(Level.WARNING, e.getMessage());
		}
		if(passed) {
			feedbackLogger.log(Level.INFO, "Passed ClimbInfo tests.");
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.WARNING, "Failed ClimbInfo tests.");
	}
	
	public void testAddClimbChronological() {
		clearClimbLists();
		addStandardClimbs(solutionClimbingClubChronological);
		addStandardClimbs(studentClimbingClubChronological);
		List<AbstractClimbInfo> expected = solutionClimbingClubChronological.getClimbList(), actual = studentClimbingClubChronological.getClimbList();
		boolean passed = expected.size() == actual.size();
		for(int index = expected.size() - 1; index >= 0 && passed; index--) {
			AbstractClimbInfo expCi = expected.get(index), actCi = actual.get(index);
			if(!expCi.getPeakName().equals(actCi.getPeakName()) || expCi.getClimbTime() != actCi.getClimbTime())
				passed = false;
		}
		if(passed) {
			feedbackLogger.log(Level.INFO, "Passed chronological adds.");
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.WARNING, "Failed chronological adds.");
	}
	
	public void testAddClimbAlphabetical() {
		clearClimbLists();
		addStandardClimbs(solutionClimbingClubAlphabetical);
		addStandardClimbs(studentClimbingClubAlphabetical);
		boolean passed = true;
		List<AbstractClimbInfo> expected = solutionClimbingClubAlphabetical.getClimbList(), actual = studentClimbingClubAlphabetical.getClimbList();
		if(expected.size() == actual.size()) {
			for(int index = expected.size() - 1; index >= 0 && passed; index--) {
				if(!expected.get(index).getPeakName().equals(actual.get(index).getPeakName()))
					passed = false;
			}
		} else
			passed = false;
		if(passed) {
			feedbackLogger.log(Level.INFO, "Passed alphabetical adds.");
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.WARNING, "Failed alphabetical adds.");
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 10;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentPackage + ".ClimbInfo", "edu.jenks.dist.cb.climbingclub.AbstractClimbInfo");
		map.put(studentPackage + ".ClimbingClubAlphabetical", "edu.jenks.dist.cb.climbingclub.AbstractClimbingClub");
		map.put(studentPackage + ".ClimbingClubChronological", "edu.jenks.dist.cb.climbingclub.AbstractClimbingClub");
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
		feedbackLogger.log(Level.INFO, "passed - object creation");
		totalPoints += 6;
	}
	
	private AbstractClimbInfo createClimbInfo(String peakName, int climbTime) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {peakName, climbTime};
		return (AbstractClimbInfo)ReflectionUtil.newInstance(studentPackage + ".ClimbInfo", CLIMB_INFO_CONSTRUCTOR_PARAM_TYPES, args);
	}
	
	private void clearClimbLists() {
		solutionClimbingClubAlphabetical.getClimbList().clear();
		solutionClimbingClubChronological.getClimbList().clear();
		studentClimbingClubAlphabetical.getClimbList().clear();
		studentClimbingClubChronological.getClimbList().clear();
	}
	
	private void addStandardClimbs(AbstractClimbingClub cc) {
		cc.addClimb("Monadnock", 274);
		cc.addClimb("Whiteface", 301);
		cc.addClimb("Algonquin", 225);
		cc.addClimb("Monadnock", 344);
	}
}
