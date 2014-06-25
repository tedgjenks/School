package edu.jenks.comparison.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import edu.jenks.comparison.dist.AbstractEnemy;
import edu.jenks.comparison.dist.AbstractWeapon;
import edu.jenks.test.TestPackageList;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class EnemyTest extends Testable {
	
	private static final EnemyTest SINGLETON = new EnemyTest();
	
	private final Class<?>[] WEAPON_CONSTRUCTOR_PARAM_TYPES = {String.class, int.class};
	private final Class<?>[] ENEMY_CONSTRUCTOR_PARAM_TYPES = {String.class, int.class, AbstractWeapon.class};
	boolean continueTesting = true;
	
	private AbstractWeapon gun5;
	private AbstractWeapon gun5Copy;
	private AbstractWeapon gun10;
	private AbstractWeapon knife1;
	private AbstractWeapon knife2;
	
	private AbstractEnemy sniper10;
	private AbstractEnemy sniper5;
	private AbstractEnemy infantry1;
	private AbstractEnemy infantry1Copy;
	private AbstractEnemy commando15Knife1;
	private AbstractEnemy commando15Knife2;
	
	public void init() {
		try {
			Object[] gun5Args = {"Gun", 5};
			gun5 = (AbstractWeapon)ReflectionUtil.newInstance(studentPackage + ".Weapon", WEAPON_CONSTRUCTOR_PARAM_TYPES, gun5Args);
			gun5Copy = (AbstractWeapon)ReflectionUtil.newInstance(studentPackage + ".Weapon", WEAPON_CONSTRUCTOR_PARAM_TYPES, gun5Args);
			Object[] gun10Args = {"Gun", 10};
			gun10 = (AbstractWeapon)ReflectionUtil.newInstance(studentPackage + ".Weapon", WEAPON_CONSTRUCTOR_PARAM_TYPES, gun10Args);
			Object[] knife1Args = {"Knife", 1};
			knife1 = (AbstractWeapon)ReflectionUtil.newInstance(studentPackage + ".Weapon", WEAPON_CONSTRUCTOR_PARAM_TYPES, knife1Args);
			Object[] knife2Args = {"Knife", 2};
			knife2 = (AbstractWeapon)ReflectionUtil.newInstance(studentPackage + ".Weapon", WEAPON_CONSTRUCTOR_PARAM_TYPES, knife2Args);
			
			Object[] sniper10Args = {"Sniper", 10, gun10};
			sniper10 = (AbstractEnemy)ReflectionUtil.newInstance(studentPackage + ".Enemy", ENEMY_CONSTRUCTOR_PARAM_TYPES, sniper10Args);
			Object[] sniper5Args = {"Sniper", 5, gun10};
			sniper5 = (AbstractEnemy)ReflectionUtil.newInstance(studentPackage + ".Enemy", ENEMY_CONSTRUCTOR_PARAM_TYPES, sniper5Args);
			Object[] infantry1Args = {"Infantry", 1, gun5};
			infantry1 = (AbstractEnemy)ReflectionUtil.newInstance(studentPackage + ".Enemy", ENEMY_CONSTRUCTOR_PARAM_TYPES, infantry1Args);
			infantry1Copy = (AbstractEnemy)ReflectionUtil.newInstance(studentPackage + ".Enemy", ENEMY_CONSTRUCTOR_PARAM_TYPES, infantry1Args);
			Object[] commando15Knife1Args = {"Commando", 15, knife1};
			commando15Knife1 = (AbstractEnemy)ReflectionUtil.newInstance(studentPackage + ".Enemy", ENEMY_CONSTRUCTOR_PARAM_TYPES, commando15Knife1Args);
			Object[] commando15Knife2Args = {"Commando", 15, knife2};
			commando15Knife2 = (AbstractEnemy)ReflectionUtil.newInstance(studentPackage + ".Enemy", ENEMY_CONSTRUCTOR_PARAM_TYPES, commando15Knife2Args);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.log(Level.WARNING, "Fail - object creation; testing aborted: " + e.getMessage());
		}
	}
	
	/*@Override
	public String[] getTestMethods() {
		String[] testMethods = {"testEquals", "testHashCode", "testClone", "testToString", "testCompareTo"};
		return testMethods;
	}*/

	@Override
	public void test() {
		init();
		if(!continueTesting)
			return;
		try {
			totalPoints += testEquals();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		
		if(totalPoints > 0) {
			try {
				totalPoints += testHashCode();
			} catch(Exception e) {
				logger.log(Level.WARNING, e.getMessage());
			}
		} else
			logger.log(Level.INFO, "hashCode() not tested due to failed equals test."); 
		
		try {
			totalPoints += testClone();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		
		try {
			totalPoints += testToString();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		
		try {
			totalPoints += testCompareTo();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		
	}
	
	private int testEquals() {
		int points = 0;
		boolean passed = true;
		if(!gun5.equals(gun5Copy) || gun5.equals(gun10)) {
			passed = false;
			logger.log(Level.INFO, "Failed test on Weapon.");
		}
		if(!infantry1.equals(infantry1Copy) || sniper5.equals(sniper10) || commando15Knife1.equals(commando15Knife2)) {
			passed = false;
			logger.log(Level.INFO, "Failed test on Enemy.");
		}
		if(passed) {
			points++;
			logger.log(Level.FINE, "Passed");
		}
		return points;
	}
	
	private int testHashCode() {
		int points = 0;
		boolean passed = true;
		if(gun5.hashCode() != gun5Copy.hashCode()) {
			passed = false;
			logger.log(Level.INFO, "Failed test on equal Weapons.");
		}
		if(infantry1.hashCode() != infantry1Copy.hashCode()) {
			passed = false;
			logger.log(Level.INFO, "Failed test on equal Enemys.");
		}
		if(gun5.hashCode() == gun10.hashCode() && gun10.hashCode() == knife1.hashCode() && knife1.hashCode() == knife2.hashCode()) {
			passed = false;
			logger.log(Level.INFO, "Fail - all Weapon hash codes the same.");
		}
		if(sniper10.hashCode() == sniper5.hashCode() && sniper5.hashCode() == infantry1.hashCode() && 
				infantry1.hashCode() == commando15Knife1.hashCode() && commando15Knife1.hashCode() == commando15Knife2.hashCode()) {
			passed = false;
			logger.log(Level.INFO, "Fail - all Enemy hash codes the same.");
		}
		if(passed) {
			points++;
			logger.log(Level.FINE, "Passed");
		}
		return points;
	}
	
	private int testClone() {
		int points = 0;
		boolean passed = true;
		AbstractWeapon gun10Copy = (AbstractWeapon)gun10.clone();
		if(gun10Copy == gun10) {
			passed = false;
			logger.log(Level.INFO, "Fail - Weapon clone points to the same object.");
		} else if(!gun10Copy.equals(gun10)){
			passed = false;
			logger.log(Level.INFO, "Fail - Weapon clone is not equal.");
		}
		
		AbstractEnemy sniper5Clone = (AbstractEnemy)sniper5.clone();
		if(sniper5Clone == sniper5) {
			passed = false;
			logger.log(Level.INFO, "Fail - Enemy clone points to the same object.");
		} else if(!sniper5Clone.equals(sniper5)) {
			passed = false;
			logger.log(Level.INFO, "Fail - Enemy clone is not equal.");
		}
		if(passed) {
			points++;
			logger.log(Level.FINE, "Passed");
		}
		return points;
	}
	
	private int testToString() {
		int points = 0;
		boolean passed = true;
		if(!gun5.toString().equals("Gun 5")) {
			passed = false;
			logger.log(Level.INFO, "Fail weapon");
		} else if(!sniper5.toString().equals("Sniper 5.  Weapon: Gun 10")) {
			passed = false;
			logger.log(Level.INFO, "Fail enemy");
		}
		if(passed) {
			points++;
			logger.log(Level.FINE, "Passed");
		}
		return points;
	}
	
	private int testCompareTo() {
		int points = 0;
		boolean passed = true;
		if(infantry1.compareTo(infantry1Copy) != 0) {
			passed = false;
			logger.log(Level.INFO, "Fail - equal objects not zero");
		} else if(commando15Knife1.compareTo(commando15Knife2) != 0){
			passed = false;
			logger.log(Level.INFO, "Fail - equal objects not zero");
		} else {
			List<AbstractEnemy> enemies = new LinkedList<AbstractEnemy>();
			enemies.add(sniper10);
			enemies.add(sniper5);
			enemies.add(infantry1);
			enemies.add(commando15Knife1);
			Collections.sort(enemies);
			Iterator<AbstractEnemy> iterator = enemies.iterator();
			if(commando15Knife1 != iterator.next() || sniper10 != iterator.next() || sniper5 != iterator.next() || infantry1 != iterator.next()) {
				passed = false;
				logger.log(Level.INFO, "Fail - enemies did not sort correctly");
			}
		}
		if(passed) {
			points++;
			logger.log(Level.FINE, "Passed");
		}
		return points;
	}

	@Override
	public String getLogFilePath() {
		return LOG_FILE_PATH_START + "comparison/test/log.txt";
	}
	
	public static void main(String[] args) {
		System.out.println("Begin main");
		TestPackageList.testPackages(SINGLETON, SINGLETON.getLogFilePath());
		System.out.println("End main");
	}

	@Override
	public Testable getSingleton() {
		return SINGLETON;
	}
}
