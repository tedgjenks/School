package edu.cb.lunch.jenkst.ted.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cb.lunch.jenks.ted.Trio;
import edu.jenks.dist.cb.lunch.*;

public class TrioTest {
	
	private static final double DELTA = 0.001;

	@Test
	public void test() {
		Sandwich sandwich = new Sandwich("Cheeseburger", 2.75);
		Salad salad = new Salad("Spinach salad", 1.25);
		Drink drink = new Drink("Orange soda", 1.25);
		Trio trio = new Trio(sandwich, salad, drink);
		String expName = "Cheeseburger/Spinach salad/Orange soda Trio";
		double expPrice = 4;
		assertEquals("Name fail", expName, trio.getName());
		assertEquals("Price fail", expPrice, trio.getPrice(), DELTA);
		sandwich = new Sandwich("Club sandwich", 2.75);
		salad = new Salad("Coleslaw", 1.25);
		drink = new Drink("Cappuccino", 3.5);
		trio = new Trio(sandwich, salad, drink);
		expName = "Club sandwich/Coleslaw/Cappuccino Trio";
		expPrice = 6.25;
		assertEquals("Name fail", expName, trio.getName());
		assertEquals("Price fail", expPrice, trio.getPrice(), DELTA);
	}

}
