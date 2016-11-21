/**
 * 
 */
package edu.jenks.cb.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;

import edu.jenks.dist.cb.masterorder.*;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author JenksT
 *
 */
public class MasterOrderTest extends Testable {
	
	private static List<CookieOrder> expOrdersStandard = new ArrayList<CookieOrder>();
	
	static {
		expOrdersStandard.add(new CookieOrder("Shortbread", 5));
		expOrdersStandard.add(new CookieOrder("Macaroon", 2));
	}
	
	private AbstractMasterOrder studentMasterOrder;
	private Random random = new Random(System.currentTimeMillis());

	/**
	 * 
	 */
	public MasterOrderTest() {}
	
	public void testGetTotalBoxesByVariety() {
		List<CookieOrder> orders = studentMasterOrder.getOrders();
		orders.clear();
		int numberA = 0, numberB = 0;
		int number = getRandomNumberBoxes();
		orders.add(new CookieOrder("A", number));
		numberA += number;
		number = getRandomNumberBoxes();
		orders.add(new CookieOrder("B", number));
		numberB += number;
		number = getRandomNumberBoxes();
		orders.add(new CookieOrder("A", number));
		numberA += number;
		Map<String, Integer> exp = new HashMap<String, Integer>();
		exp.put("A", numberA);
		exp.put("B", numberB);
		Map<String, Integer> act = studentMasterOrder.getTotalBoxesByVariety();
		if(act != null && exp.size() == act.size()) {
			Iterator keys = exp.keySet().iterator();
			boolean pass = true;
			while(pass && keys.hasNext()) {
				Object key = keys.next();
				if(!exp.get(key).equals(act.get(key))) {
					pass = false;
				}
			}
			if(pass) {
				totalPoints += 2;
				feedbackLogger.log(Level.INFO, "Passed - getTotalBoxesByVariety");
			} else
				feedbackLogger.log(Level.WARNING, "Failed - getTotalBoxesByVariety");
		} else
			feedbackLogger.log(Level.WARNING, "Failed - getTotalBoxesByVariety");
	}
	
	public void testRemoveVariety() {
		initData1();
		
		//standard - remove Chocolate Chip
		String variety = "Chocolate Chip";
		int expRemoved = 4;
		int actRemoved = studentMasterOrder.removeVariety(variety);
		if(expRemoved != actRemoved) {
			logExpectedActual(Level.WARNING, "Failed - removeVariety return value; " + variety + " removed", expRemoved, actRemoved);
		} else {
			totalPoints += 2;
			feedbackLogger.log(Level.INFO, "Passed - removeVariety return value; " + variety + " removed");
			if(ordersEqual(expOrdersStandard, studentMasterOrder.getOrders())) {
				totalPoints +=2;
				feedbackLogger.log(Level.INFO, "Passed - removeVariety orders; " + variety + " removed");
			} else
				feedbackLogger.log(Level.INFO, "Failed - removeVariety orders; " + variety + " removed");
		}
		
		//standard - remove Brownie
		variety = "Brownie";
		expRemoved = 0;
		actRemoved = studentMasterOrder.removeVariety(variety);
		if(expRemoved != actRemoved) {
			logExpectedActual(Level.WARNING, "Failed - removeVariety return value; " + variety + " removed", expRemoved, actRemoved);
		} else {
			totalPoints += 2;
			feedbackLogger.log(Level.INFO, "Passed - removeVariety return value; " + variety + " removed");
			if(ordersEqual(expOrdersStandard, studentMasterOrder.getOrders())) {
				totalPoints +=2;
				feedbackLogger.log(Level.INFO, "Passed - removeVariety orders; " + variety + " removed");
			} else
				feedbackLogger.log(Level.INFO, "Failed - removeVariety orders; " + variety + " removed");
		}
		
		List<CookieOrder> orders = studentMasterOrder.getOrders(), expOrders = new ArrayList<CookieOrder>();
		orders.clear();
		
		int number = getRandomNumberBoxes();
		expRemoved = number;
		orders.add(new CookieOrder("A", number));
		
		number = getRandomNumberBoxes();
		CookieOrder cookieOrder = new CookieOrder("B", number);
		orders.add(cookieOrder);
		expOrders.add(cookieOrder);
		
		number = getRandomNumberBoxes();
		expRemoved += number;
		orders.add(new CookieOrder("A", number));
		
		number = getRandomNumberBoxes();
		cookieOrder = new CookieOrder("C", number);
		orders.add(cookieOrder);
		expOrders.add(cookieOrder);
		
		number = getRandomNumberBoxes();
		expRemoved += number;
		orders.add(new CookieOrder("A", number));
		
		//random - remove A
		variety = "A";
		actRemoved = studentMasterOrder.removeVariety(variety);
		if(expRemoved != actRemoved) {
			logExpectedActual(Level.WARNING, "Failed - removeVariety return value; " + variety + " removed", expRemoved, actRemoved);
		} else {
			totalPoints += 2;
			feedbackLogger.log(Level.INFO, "Passed - removeVariety return value; " + variety + " removed");
			if(ordersEqual(expOrders, studentMasterOrder.getOrders())) {
				totalPoints += 2;
				feedbackLogger.log(Level.INFO, "Passed - removeVariety orders; " + variety + " removed");
			} else
				feedbackLogger.log(Level.INFO, "Failed - removeVariety orders; " + variety + " removed");
		}
		
		//standard - remove A (should not be any remaining)
		expRemoved = 0;
		actRemoved = studentMasterOrder.removeVariety(variety);
		if(expRemoved != actRemoved) {
			logExpectedActual(Level.WARNING, "Failed - removeVariety return value; " + variety + " removed", expRemoved, actRemoved);
		} else {
			totalPoints += 3;
			feedbackLogger.log(Level.INFO, "Passed - removeVariety return value; " + variety + " removed");
			if(ordersEqual(expOrders, studentMasterOrder.getOrders())) {
				totalPoints +=3;
				feedbackLogger.log(Level.INFO, "Passed - removeVariety orders; " + variety + " removed");
			} else
				feedbackLogger.log(Level.INFO, "Failed - removeVariety orders; " + variety + " removed");
		}
	}
	
	private boolean ordersEqual(List<CookieOrder> orders1, List<CookieOrder> orders2) {
		boolean equal = orders1.size() == orders2.size();
		if(equal) {
			for(int index = orders1.size() - 1; index >= 0 && equal; index--) {
				CookieOrder cookieOrder1 = orders1.get(index), cookieOrder2 = orders2.get(index);
				if(!cookieOrder1.getVariety().equals(cookieOrder2.getVariety()) || cookieOrder1.getNumBoxes() != cookieOrder2.getNumBoxes())
					equal = false;
			}
		}
		return equal;
	}
	
	public void testGetTotalBoxes() {
		initData1();
		int expV = 11;
		int actV = studentMasterOrder.getTotalBoxes();
		if(expV == actV) {
			totalPoints += 5;
			feedbackLogger.log(Level.INFO, "Passed - getTotalBoxes standard.");
		} else
			logExpectedActual(Level.WARNING, "Failed - getTotalBoxes standard.", expV, actV);
		
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
			logExpectedActual(Level.WARNING, "Failed - getTotalBoxes random.", String.valueOf(expV), String.valueOf(actV));
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
		return 100;
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
