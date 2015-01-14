/**
 * 
 */
package edu.jenks.cb.masterorder.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author JenksT
 *
 */
public class MasterOrderTest extends Testable {
	
	private AbstractMasterOrder studentMasterOrder;
	private Random random = new Random(System.currentTimeMillis());

	/**
	 * 
	 */
	public MasterOrderTest() {}
	
	public void testGetTotalBoxes() {
		initData1();
		int expV = 11;
		int actV = studentMasterOrder.getTotalBoxes();
		if(expV == actV) {
			totalPoints += 5;
			feedbackLogger.log(Level.INFO, "Passed - getTotalBoxes standard.");
		} else
			feedbackLogger.log(Level.WARNING, "Failed - getTotalBoxes standard.");
		
		List<CookieOrder> orders = studentMasterOrder.getOrders();
		orders.clear();
		int number = getRandomNumberBoxes();
		orders.add(new CookieOrder("A", number));
		expV = number;
		number = getRandomNumberBoxes();
		orders.add(new CookieOrder("B", number));
		expV += number;
		number = getRandomNumberBoxes();
		orders.add(new CookieOrder("C", number));
		expV += number;
		actV = studentMasterOrder.getTotalBoxes();
		if(expV == actV) {
			totalPoints += 5;
			feedbackLogger.log(Level.INFO, "Passed - getTotalBoxes random.");
		} else
			feedbackLogger.log(Level.WARNING, "Failed - getTotalBoxes random.");
	}
	
	private int getRandomNumberBoxes() {
		return random.nextInt(10) + 1;
	}

	private void initData1() {
		List<CookieOrder> orders = studentMasterOrder.getOrders();
		orders.clear();
		orders.add(new CookieOrder("Chocolate Chip", 1));
		orders.add(new CookieOrder("Shortbread", 5));
		orders.add(new CookieOrder("Macaroon", 2));
		orders.add(new CookieOrder("Chocolate Chip", 3));
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 80;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentPackage + ".MasterOrder", "edu.jenks.dist.cb.masterorder.AbstractMasterOrder");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		studentMasterOrder = (AbstractMasterOrder)ReflectionUtil.newInstance(studentPackage + ".MasterOrder");
		totalPoints += 70;
		feedbackLogger.log(Level.INFO, "Passed - object creation.");
	}

}
