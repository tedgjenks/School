/**
 * 
 */
package edu.jenks.inheritance.test;

//import static java.lang.System.out;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import edu.jenks.dist.inheritance.*;

import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class SelfCheckoutTest extends Testable {
	private static final Class<?>[] CONSTRUCTOR_TYPE_WEIGHED = {boolean.class, double.class};
	private static final Class<?>[] CONSTRUCTOR_TYPE_CHECKOUT = {double.class};
	private static final Class<?>[] CONSTRUCTOR_TYPE_ALCOHOL = {boolean.class, double.class, double.class, double.class};
	private static final Class<?>[] CONSTRUCTOR_TYPE_BARCODED = {boolean.class, double.class, double.class};
	private static final double RELATIVE_DELTA = 0.0001;
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	
	private String alcoholCN, barcodeCN, itemCN, selfCheckoutCN, weighedCN;

	/**
	 * 
	 */
	public SelfCheckoutTest() {}
	
	// 8 points
	public void test01AddBarcodedItem() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // price and weight
		boolean pass = true;
		final String message = "AddBarcodedItem";
		final int points = 8;
		ItemHandler ih = createSelfCheckout(0);
		double expStagingWeight = 5*RANDOM.nextDouble() + 3;
		double expSubtotal = 10*RANDOM.nextDouble() + 3;
		Barcoded barcoded = createBarcodedItem(false, expStagingWeight, expSubtotal);
		ih.addItem(barcoded);
		if(itemHandlerPass(message, points, ih, false, expStagingWeight, expSubtotal))
			totalPoints += points;
		else
			pass = false;
		
		if(pass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	private boolean itemHandlerPass(String message, int points, ItemHandler ih, boolean expCheckID, double expStagingWeight, double expSubtotal, double expTax) {
		boolean pass = itemHandlerPass(message, points, ih, expCheckID, expStagingWeight, expSubtotal);
		if(!MathUtil.equalsRelative(expTax, ih.getTax(), RELATIVE_DELTA)) {
			logFail(message + " - tax", expTax, ih.getTax(), points);
			pass = false;
		}
		double expCheckout = expSubtotal + expTax;
		if(!MathUtil.equalsRelative(expCheckout, ih.getCheckoutTotal(), RELATIVE_DELTA)) {
			logFail(message + " - checkout total", expCheckout, ih.getCheckoutTotal(), points);
			pass = false;
		}
		return pass;
	}
	
	private boolean itemHandlerPass(String message, int points, ItemHandler ih, boolean expCheckID, double expStagingWeight, double expSubtotal) {
		boolean pass = true;
		if(ih.isCheckID() != expCheckID) {
			logFail(message + " - check ID", expCheckID, ih.isCheckID(), points);
			pass = false;
		}
		if(!MathUtil.equalsRelative(expStagingWeight, ih.getExpectedWeight(), RELATIVE_DELTA)) {
			logFail(message + " - staging weight", expStagingWeight, ih.getExpectedWeight(), points);
			pass = false;
		}
		if(!MathUtil.equalsRelative(expSubtotal, ih.getSubtotal(), RELATIVE_DELTA)) {
			logFail(message + " - subtotal", expSubtotal, ih.getSubtotal(), points);
			pass = false;
		}
		return pass;
	}
	
	// 8 points
	public void test02AddAlcoholItem() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // check ID
		boolean pass = true;
		final String message = "AddAlcoholItem";
		final int points = 8;
		ItemHandler ih = createSelfCheckout(0);
		double expStagingWeight = 5*RANDOM.nextDouble() + 3;
		double expSubtotal = 10*RANDOM.nextDouble() + 3;
		boolean expCheckID = true;
		Barcoded barcoded = createAlcoholItem(false, expStagingWeight, expSubtotal, 0);
		ih.addItem(barcoded);
		if(itemHandlerPass(message, points, ih, expCheckID, expStagingWeight, expSubtotal))
			totalPoints += points;
		else
			pass = false;
		
		if(pass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	// 8 points
	public void test03AddWeighedItem() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // price and weight
		boolean pass = true;
		final String message = "AddWeighedItem";
		final int points = 8;
		ItemHandler ih = createSelfCheckout(0);
		double expStagingWeight = 5*RANDOM.nextDouble() + 3;
		double pricePerPound = 5*RANDOM.nextDouble() + 1;
		double expSubtotal = expStagingWeight * pricePerPound;
		Weighable weighable = createWeighedItem(false, pricePerPound);
		ih.addItem(weighable, expStagingWeight);
		if(itemHandlerPass(message, points, ih, false, expStagingWeight, expSubtotal))
			totalPoints += points;
		else
			pass = false;
		
		if(pass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	// 5 points
	public void test04AddBulkItem() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // exclude from expected weight
		boolean pass = true;
		final String message = "AddBulkItem";
		final int points = 5;
		ItemHandler ih = createSelfCheckout(0);
		double expStagingWeight = 0;
		double expSubtotal = 10*RANDOM.nextDouble() + 3;
		double weight = 5*RANDOM.nextDouble() + 3;
		Barcoded barcoded = createBarcodedItem(true, weight, expSubtotal);
		ih.addItem(barcoded);
		if(itemHandlerPass(message, points, ih, false, expStagingWeight, expSubtotal))
			totalPoints += points;
		else
			pass = false;
		
		if(pass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	// 5 points
	public void test05StandardTax() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean pass = true;
		final String message = "StandardTax";
		final int points = 5;
		final double standardTaxRate = 0.06 * RANDOM.nextDouble() + .02;
		ItemHandler ih = createSelfCheckout(standardTaxRate);
		double expStagingWeight = 5*RANDOM.nextDouble() + 3;
		double price = 10*RANDOM.nextDouble() + 3;
		double expTax = price * standardTaxRate;
		Barcoded barcoded = createBarcodedItem(false, expStagingWeight, price);
		ih.addItem(barcoded);
		if(itemHandlerPass(message, points, ih, false, expStagingWeight, price, expTax))
			totalPoints += points;
		else
			pass = false;
		
		if(pass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	// 8 points
	public void test06SinTax() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean pass = true;
		final String message = "SinTax";
		final int points = 8;
		final double standardTaxRate = 0.06 * RANDOM.nextDouble() + .02;
		final double sinTaxRate = 0.03 * RANDOM.nextDouble() + .01;
		ItemHandler ih = createSelfCheckout(standardTaxRate);
		double expStagingWeight = 5*RANDOM.nextDouble() + 3;
		double expSubtotal = 10*RANDOM.nextDouble() + 3;
		double expTax = expSubtotal * (standardTaxRate + sinTaxRate);
		boolean expCheckID = true;
		SinTaxable item = createAlcoholItem(false, expStagingWeight, expSubtotal, sinTaxRate);
		ih.addItem(item);
		if(itemHandlerPass(message, points, ih, expCheckID, expStagingWeight, expSubtotal, expTax))
			totalPoints += points;
		else
			pass = false;
		
		if(pass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	// 8 points
	public void test07Checkout() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean pass = true;
		final String message = "Checkout";
		final int points = 8;
		final double standardTaxRate = 0.06 * RANDOM.nextDouble() + .02;
		ItemHandler ih = createSelfCheckout(standardTaxRate);
		double weight, price, sinTaxRate, pricePerPound;
		double expStagingWeight = 0, expSubtotal = 0, expTotalTax = 0;
		boolean expCheckID = true;
		
		//Barcode 1
		weight = 5*RANDOM.nextDouble() + 3;
		price = 10*RANDOM.nextDouble() + 3;
		ih.addItem(createBarcodedItem(false, weight, price));
		expStagingWeight += weight;
		expSubtotal += price;
		expTotalTax += price * standardTaxRate;
		
		//Barcode 2
		weight = 5*RANDOM.nextDouble() + 3;
		price = 10*RANDOM.nextDouble() + 3;
		ih.addItem(createBarcodedItem(true, weight, price));
		expSubtotal += price;
		expTotalTax += price * standardTaxRate;
		
		//Alcohol 3
		weight = 5*RANDOM.nextDouble() + 3;
		price = 10*RANDOM.nextDouble() + 3;
		sinTaxRate = 0.03 * RANDOM.nextDouble() + .01;
		ih.addItem(createAlcoholItem(false, weight, price, sinTaxRate));
		expStagingWeight += weight;
		expSubtotal += price;
		expTotalTax += price * (standardTaxRate + sinTaxRate);
		
		//Alcohol 4
		weight = 5*RANDOM.nextDouble() + 3;
		price = 10*RANDOM.nextDouble() + 3;
		sinTaxRate = 0.03 * RANDOM.nextDouble() + .01;
		ih.addItem(createAlcoholItem(true, weight, price, sinTaxRate));
		expSubtotal += price;
		expTotalTax += price * (standardTaxRate + sinTaxRate);
		
		//Weighed 5
		weight = 5*RANDOM.nextDouble() + 3;
		pricePerPound = 3*RANDOM.nextDouble() + 1;
		price = pricePerPound * weight;
		ih.addItem(createWeighedItem(false, pricePerPound), weight);
		expStagingWeight += weight;
		expSubtotal += price;
		expTotalTax += price * standardTaxRate;
		
		//Weighed 6
		weight = 5*RANDOM.nextDouble() + 3;
		pricePerPound = 3*RANDOM.nextDouble() + 1;
		price = pricePerPound * weight;
		ih.addItem(createWeighedItem(true, pricePerPound), weight);
		expSubtotal += price;
		expTotalTax += price * standardTaxRate;
		
		if(itemHandlerPass(message, points, ih, expCheckID, expStagingWeight, expSubtotal, expTotalTax))
			totalPoints += points;
		else
			pass = false;
		
		if(pass)
			logPass(message);
		else
			continueTesting = false;
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
		alcoholCN = studentPackage + ".AlcoholItem";
		barcodeCN = studentPackage + ".BarcodeItem";
		itemCN = studentPackage + ".Item";
		selfCheckoutCN = studentPackage + ".SelfCheckout";
		weighedCN = studentPackage + ".WeighedItem";
		map.put(alcoholCN, barcodeCN);
		map.put(barcodeCN, itemCN);
		map.put(itemCN, "java.lang.Object");
		map.put(selfCheckoutCN, "java.lang.Object");
		map.put(weighedCN, itemCN);
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		createSelfCheckout(0);
		createAlcoholItem(false, 0, 0, 0);
		createWeighedItem(false, 0);
		createBarcodedItem(false, 0, 0);
		totalPoints += 50;
	}
	
	private ItemHandler createSelfCheckout(double taxRate) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {taxRate};
		ItemHandler instance = (ItemHandler)ReflectionUtil.newInstance(selfCheckoutCN, CONSTRUCTOR_TYPE_CHECKOUT, args);
		return instance;
	}
	
	private SinTaxable createAlcoholItem(boolean bulk, double weight, double price, double sinTaxRate) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {bulk, weight, price, sinTaxRate};
		SinTaxable instance = (SinTaxable)ReflectionUtil.newInstance(alcoholCN, CONSTRUCTOR_TYPE_ALCOHOL, args);
		return instance;
	}
	
	private Weighable createWeighedItem(boolean bulk, double pricePerPound) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {bulk, pricePerPound};
		Weighable instance = (Weighable)ReflectionUtil.newInstance(weighedCN, CONSTRUCTOR_TYPE_WEIGHED, args);
		return instance;
	}
	
	private Barcoded createBarcodedItem(boolean bulk, double weight, double price) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {bulk, weight, price};
		Barcoded instance = (Barcoded)ReflectionUtil.newInstance(barcodeCN, CONSTRUCTOR_TYPE_BARCODED, args);
		return instance;
	}

}
