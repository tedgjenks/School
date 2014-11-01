package jenks.unit2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import edu.jenks.payroll.PayrollCalculator;

public class PayrollCalculatorTest {
	private int pointTotal = 0;

	@After
	public void tearDown() throws Exception {
		System.out.println("Total points: " + pointTotal);
	}
	
	@Test
	public void test() {
		System.out.println("Begin Test");
		PayrollCalculator pc = new PayrollCalculator();
		final double FLOAT_DELTA = 0.0001;
		int hours = 30, minutes = 40, seconds = 20;
		double totalHours = pc.convertToHours(hours, minutes, seconds);
		assertEquals(convertToHours(hours, minutes, seconds), totalHours, FLOAT_DELTA);
		System.out.println("Passed convert to hours.");
		pointTotal++;
		double payD = pc.calculatePay(totalHours);
		assertEquals(totalHours * 10.5, payD, FLOAT_DELTA);
		System.out.println("Passed floating pay (standard only).");
		pointTotal++;
		/*String pay = pc.formatPay(payD);
		try {
			assertEquals("$322.06", pay);
			System.out.println("Passed format pay (standard only).");
			pointTotal++;
		} catch(ComparisonFailure cf) {
			System.out.println(cf.getMessage());
		}*/
		hours = 50;
		totalHours = pc.convertToHours(hours, minutes, seconds);
		payD = pc.calculatePay(totalHours);
		assertEquals(40 * 10.5 + ((totalHours - 40) * (10.5 * 1.5)), payD, FLOAT_DELTA);
		System.out.println("Passed floating pay (with overtime).");
		pointTotal++;
		String pay = pc.formatPay(payD);
		assertEquals("$588.09", pay);
		System.out.println("Passed format pay (with overtime).");
		pointTotal += 2;
		System.out.println("End Test");
	}
	
	private double convertToHours(int hours, int minutes, int seconds) {
		return hours + minutes/60.0 + seconds/3600.0;
	}

}
