package edu.jenks.inheritance.test;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;
import edu.jenks.inheritance.dist.Weighable;
import edu.jenks.test.TestPackageList;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

public class InheritanceTest extends Testable {
	
	private static final InheritanceTest SINGLETON = new InheritanceTest();
	private static final double BASE_TAX_RATE = 0.06;
	private static final double SIN_TAX_RATE = 0.02;
	private static final double DELTA = 0.001;
	
	private final Class<?>[] SELFCHECKOUT_CONSTRUCTOR_PARAM_TYPES = {double.class};
	private final Object[] SELFCHECKOUT_CONSTRUCTOR_ARGS = {BASE_TAX_RATE};

	public void testMixedItems() {
		ItemHandler ih = createItemHandler();
		double expectedPrice = 0, expectedWeight = 0, expectedTax = 0, price, weight;
		boolean checkId = false;
		
		//barcoded item
		price = 20.1;
		weight = 14.6;
		Barcoded bi1 = (Barcoded)createBuyable("BarcodeItem", weight);
		bi1.setPrice(price);
		ih.addItem(bi1);
		expectedPrice += price;
		expectedWeight += weight;
		expectedTax += price * BASE_TAX_RATE;
		
		//bulk item
		price = 26.1;
		weight = 914.6;
		Barcoded bi2 = (Barcoded)createBuyable("BarcodeItem", weight, true);
		bi2.setPrice(price);
		ih.addItem(bi2);
		expectedPrice += price;
		expectedTax += price * BASE_TAX_RATE;
		
		//alcohol item
		price = 15.6;
		weight = 4.6;
		SinTaxable ai1 = (SinTaxable)createBuyable("AlcoholItem", weight);
		ai1.setPrice(price);
		ai1.setSinTaxRate(SIN_TAX_RATE);
		ih.addItem(ai1);
		checkId = true;
		expectedPrice += price;
		expectedWeight += weight;
		expectedTax += price * (BASE_TAX_RATE + SIN_TAX_RATE);
		
		//weighed item
		double wi1PricePerPound = 33.3, wi1ScaleWeight = 12.1;
		Weighable wi1 = (Weighable)createBuyable("WeighedItem");
		wi1.setPricePerPound(wi1PricePerPound);
		ih.addItem(wi1, wi1ScaleWeight);
		price = wi1PricePerPound * wi1ScaleWeight;
		expectedPrice += price;
		expectedWeight += wi1ScaleWeight;
		expectedTax += price * BASE_TAX_RATE;
		
		double expectedTotal = expectedPrice + expectedTax;
		boolean validCheckout = verifySelfCheckout(ih, expectedWeight, expectedTotal, checkId);
		if(validCheckout) {
			totalPoints += 2;
			logger.log(Level.FINE, "Pass (2)");
		}
	}
	
	public void testWeighedItems() {
		ItemHandler ih = createItemHandler();
		double wi1PricePerPound = 33.3, wi1ScaleWeight = 12.1;
		Weighable wi1 = (Weighable)createBuyable("WeighedItem");
		wi1.setPricePerPound(wi1PricePerPound);
		ih.addItem(wi1, wi1ScaleWeight);
		double expectedPrice = wi1PricePerPound * wi1ScaleWeight;
		double expectedWeight = wi1ScaleWeight;
		double expectedTax = expectedPrice * BASE_TAX_RATE;
		double expectedTotal = expectedPrice + expectedTax;
		boolean validCheckout = verifySelfCheckout(ih, expectedWeight, expectedTotal, false);
		if(validCheckout) {
			totalPoints++;
			logger.log(Level.FINE, "Pass");
		}
	}
	
	public void testBulkItems() {
		ItemHandler ih = createItemHandler();
		double bi1Price = 20.1, bi1Weight = 14.6;
		Barcoded bi1 = (Barcoded)createBuyable("BarcodeItem", bi1Weight, true);
		bi1.setPrice(bi1Price);
		ih.addItem(bi1);
		double expectedPrice = bi1Price;
		double expectedWeight = 0;
		double expectedTax = expectedPrice * BASE_TAX_RATE;
		double expectedTotal = expectedPrice + expectedTax;
		boolean validCheckout = verifySelfCheckout(ih, expectedWeight, expectedTotal, false);
		if(validCheckout) {
			totalPoints++;
			logger.log(Level.FINE, "Pass");
		}
	}
	
	public void test1BarcodedItems() {
		ItemHandler ih = createItemHandler();
		double bi1Price = 10, bi1Weight = 1;
		Barcoded bi1 = (Barcoded)createBuyable("BarcodeItem", bi1Weight);
		bi1.setPrice(bi1Price);
		double bi2Price = 20, bi2Weight = 2;
		Barcoded bi2 = (Barcoded)createBuyable("BarcodeItem", bi2Weight);
		bi2.setPrice(bi2Price);
		ih.addItem(bi1);
		ih.addItem(bi2);
		double expectedPrice = bi1Price + bi2Price;
		double expectedWeight = bi1Weight + bi2Weight;
		double expectedTax = expectedPrice * BASE_TAX_RATE;
		double expectedTotal = expectedPrice + expectedTax;
		boolean validCheckout = verifySelfCheckout(ih, expectedWeight, expectedTotal, false);
		if(validCheckout) {
			totalPoints++;
			logger.log(Level.FINE, "Pass");
		} else
			continueTesting = false;
	}
	
	public void testAlcoholItems() {
		ItemHandler ih = createItemHandler();
		double ai1Price = 15.5, ai1Weight = 5.2;
		SinTaxable ai1 = (SinTaxable)createBuyable("AlcoholItem", ai1Weight);
		ai1.setPrice(ai1Price);
		ai1.setSinTaxRate(SIN_TAX_RATE);
		double ai2Price = 22.3, ai2Weight = 3.1;
		SinTaxable ai2 = (SinTaxable)createBuyable("AlcoholItem", ai2Weight);
		ai2.setPrice(ai2Price);
		ai2.setSinTaxRate(SIN_TAX_RATE);
		ih.addItem(ai1);
		ih.addItem(ai2);
		double expectedPrice = ai1Price + ai2Price;
		double expectedWeight = ai1Weight + ai2Weight;
		double expectedTax = expectedPrice * (BASE_TAX_RATE + SIN_TAX_RATE);
		double expectedTotal = expectedPrice + expectedTax;
		boolean validCheckout = verifySelfCheckout(ih, expectedWeight, expectedTotal, true);
		if(validCheckout) {
			totalPoints++;
			logger.log(Level.FINE, "Pass");
		}	
	}
	
	private boolean verifySelfCheckout(ItemHandler ih, double expectedExpectedWeight, double expectedTotal, boolean expectedCheckID) {
		boolean retVal = true;
		if(!MathUtil.equalsRelative(expectedExpectedWeight, ih.getExpectedWeight(), DELTA)) {
			retVal = false;
			logger.log(Level.INFO, "Fail - expected weight");
		}
		if(!MathUtil.equals(expectedTotal, ih.getCheckoutTotal(), DELTA)) {
			retVal = false;
			logger.log(Level.INFO, "Fail - checkout total");
		}
		if(expectedCheckID != ih.isCheckID()) {
			retVal = false;
			logger.log(Level.INFO, "Fail - check ID");
		}
		return retVal;
	}
	
	private ItemHandler createItemHandler() {
		ItemHandler ih = null;
		try {
			ih = (ItemHandler)ReflectionUtil.newInstance(studentPackage + ".SelfCheckout", SELFCHECKOUT_CONSTRUCTOR_PARAM_TYPES, SELFCHECKOUT_CONSTRUCTOR_ARGS);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.log(Level.WARNING, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
		return ih;
	}
	
	private Buyable createBuyable(String className) {
		return createBuyable(className, 0, false);
	}
	
	private Buyable createBuyable(String className, double weight) {
		return createBuyable(className, weight, false);
	}
	
	private Buyable createBuyable(String className, double weight, boolean bulk) {
		Buyable item = null;
		try {
			item = (Buyable)ReflectionUtil.newInstance(studentPackage + "." + className);
			item.setWeight(weight);
			item.setBulk(bulk);
		} catch (ClassNotFoundException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException e) {
			logger.log(Level.WARNING, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
		return item;
	}

	/*@Override
	public String[] getTestMethods() {
		String[] testMethods = {"testBarcodedItems", "testAlcoholItems", "testBulkItems", "testWeighedItems", "testMixedItems"};
		return testMethods;
	}*/

	@Override
	public String getLogFilePath() {
		return LOG_FILE_PATH_START + "inheritance/test/log.txt";
	}
	
	/**
	 * @param args
	 */
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
