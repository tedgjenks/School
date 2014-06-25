package jenks.unit2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import edu.jenks.unit2.BaseConverter;

public class BaseConverterTest {
	private int pointTotal = 0;
	private final int DECIMAL_PRECISION = 3;

	@After
	public void tearDown() throws Exception {
		System.out.println("Total points: " + pointTotal);
	}

	@Test
	public void test() {
		System.out.println("Begin test");
		String testNumber = "10101";
		if(convertBase(testNumber, 2, 10).equals(BaseConverter.convertBinaryToDecimal(testNumber))) {
			//assertEquals(convertBase(testNumber, 2, 10), BaseConverter.convertBinaryToDecimal(testNumber));
			pointTotal++;
			System.out.println("Convert binary to decimal passed.");
		}
		testNumber = "143";
		if (convertBase(testNumber, 10, 2).equals(BaseConverter.convertDecimalToBinary(testNumber))) {
			//assertEquals(convertBase(testNumber, 10, 2), BaseConverter.convertDecimalToBinary(testNumber));
			pointTotal++;
			System.out.println("Convert decimal to binary passed.");
		}
		testNumber = "a98f";
		int currentRadix = 16, newRadix = 4;
		assertEquals(convertBase(testNumber, currentRadix, newRadix), BaseConverter.convertBase(testNumber, currentRadix, newRadix));
		testNumber = "42462215";
		currentRadix = 7;
		newRadix = 15;
		//System.out.println(BaseConverter.convertBase(testNumber, currentRadix, newRadix));
		assertEquals(convertBase(testNumber, currentRadix, newRadix), BaseConverter.convertBase(testNumber, currentRadix, newRadix));
		pointTotal += 2;
		System.out.println("Convert any base of a whole number passed.");
		testNumber = "1af.90a";
		String expected = convertBaseWithFloat(testNumber, 16, 5), actual = BaseConverter.convertBaseWithFloat(testNumber, 16, 5);
		assertTrue("Expected: " + expected + "; actual: " + actual, floatsEqual(expected, actual));
		testNumber = "122.2012";
		expected = convertBaseWithFloat(testNumber, 3, 11);
		actual = BaseConverter.convertBaseWithFloat(testNumber, 3, 11);
		assertTrue("Expected: " + expected + "; actual: " + actual, floatsEqual(expected, actual));
		//actual = "16.801";
		//assertTrue("Expected: " + expected + "; actual: " + actual, floatsEqual(expected, actual));
		//System.out.println(BaseConverter.convertBaseWithFloat(testNumber, 16, 5));
		pointTotal += 2;
		System.out.println("Convert floating base passed");
	}
	
	private String convertBase(String numberToConvert, int currentRadix, int newRadix) {
		Integer base10Int = Integer.valueOf(numberToConvert, currentRadix);
		return Integer.toString(base10Int, newRadix);
	}
	
	private String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix) {
		return edu.jenks.unit2.BaseConverter.convertBaseWithFloat(numberToConvert, currentRadix, newRadix);
	}
	
	private boolean floatsEqual(String expected, String actual) {
		int expectedDecimalIndex = expected.indexOf('.'), actualDecimalIndex = actual.indexOf('.');
		
		// add zero to trailing or missing decimal
		if(expectedDecimalIndex == expected.length() - 1)
			expected += "0";
		else if(expectedDecimalIndex == -1)
			expected += ".0";
		if(actualDecimalIndex == actual.length() - 1)
			actual += "0";
		else if(actualDecimalIndex == -1)
			actual += ".0";
		expectedDecimalIndex = expected.indexOf('.');
		actualDecimalIndex = actual.indexOf('.');
		
		// test whole parts
		String expectedWhole, actualWhole;
		if(expectedDecimalIndex == 0)
			expectedWhole = "0";
		else
			expectedWhole = expected.substring(0, expectedDecimalIndex);
		if(actualDecimalIndex == 0)
			actualWhole = "0";
		else
			actualWhole = actual.substring(0, actualDecimalIndex);
		boolean testPassed = expectedWhole.equals(actualWhole);
		
		// test floating parts
		if(testPassed) {
			String expectedFloat = expected.substring(expectedDecimalIndex + 1, expected.length());
			String actualFloat = actual.substring(actualDecimalIndex + 1, actual.length());
			int expectedFloatLength = expectedFloat.length(), actualFloatLength = actualFloat.length();
			for(int index = 0; index < expectedFloatLength && testPassed && index < DECIMAL_PRECISION; index++) {
				if(actualFloatLength <= index)
					testPassed = false;
				else if(expectedFloat.charAt(index) != actualFloat.charAt(index))
					testPassed = false;
			}
		}
		return testPassed;
	}
}
