package edu.math.jenks.ted.test;

import edu.jenks.dist.math.Calculates;
import edu.math.jenks.ted.BasicCalculator;

public class BasicCalculatorAssertions {

	public static void main(String[] args) {
		System.out.println("Begin BasicCalculator");
		Calculates bc = new BasicCalculator();
		assert "3".equals(bc.performCalculation("3")) : 10;
		assert "-3.0".equals(bc.performCalculation("-3")) : 20;
		assert "8.0".equals(bc.performCalculation("2 ^ 3")) : 30;
		assert "-8.0".equals(bc.performCalculation("-2 ^ 3")) : 35;
		assert "0.25".equals(bc.performCalculation("4 ^ -1")) : 40 + " " + bc.performCalculation("4 ^ -1");
		assert "12.0".equals(bc.performCalculation("3 * 4")) : 50;
		assert "12.0".equals(bc.performCalculation("36 / 3")) : 60;
		assert "12.0".equals(bc.performCalculation("8 + 4")) : 70;
		assert "12.0".equals(bc.performCalculation("16 - 4")) : 80;
		assert "12.0".equals(bc.performCalculation("16 + -4")) : 90;
		assert "37.0".equals(bc.performCalculation("7 + 4 * 6 - 75 / 25 + 3 ^ 2")) : 100;
		assert "23.0".equals(bc.performCalculation("-7 + 4 * 6 - 75 / 25 + 3 ^ 2")) : 110;
		assert "1".equals(bc.performCalculation("(1)")) : 120;
		assert "1.0".equals(bc.performCalculation("(3 - 2)")) : 130;
		assert "5.0".equals(bc.performCalculation("5 * (3 - 2)")) : 140;
		assert "1".equals(bc.performCalculation("((1))")) : 150;
		assert "-8.0".equals(bc.performCalculation("2 * (1 - (4 - 3) * 5)")) : 160;
		assert "45.0".equals(bc.performCalculation("(3 + 2) - 10 * (1 - (4 - 3) * 5)")) : 170;
		assert "-16.0".equals(bc.performCalculation("-2 ^ 4")) : 180;
		assert "5.0".equals(bc.performCalculation("-20 / -4")) : 190;
		assert "-5.0".equals(bc.performCalculation("-1 - +4")) : 190;
		assert "16.0".equals(bc.performCalculation(".25 ^ -2")) : 200;
		assert "0.5".equals(bc.performCalculation("3.0 - 2.0 * (-1.0 / (-1.0 * 1.0 + (1.0 - 1.0 * 2.0)) / -2.0) + -3.0")) : 210;
		assert "-14.5".equals(bc.performCalculation("(-2.0 - -3.0) ^ 2 / ((1.0 + -2.0) / (-3.0 * (-2.0 - 3.0))) + (-1.0 / (-3.0 + 1.0))")) : 220;
		assert bc.performCalculation("(5").startsWith(Calculates.ERROR_CODE_UNMATCHED_PARENTHESIS) : 230;
		assert bc.performCalculation("5)").startsWith(Calculates.ERROR_CODE_UNMATCHED_PARENTHESIS) : 240;
		assert "52.0".equals(bc.performCalculation("(4 + 9)(5 - 1)")) : 250 + ": " + bc.performCalculation("(4 + 9)(5 - 1)");
		System.out.println("End BasicCalculator without error");
	}

}
