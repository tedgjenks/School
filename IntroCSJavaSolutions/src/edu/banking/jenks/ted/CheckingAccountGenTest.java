package edu.banking.jenks.ted;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGenTest {
	
	public static final double DELTA = 0.001;

	@Test
	public void testDeposit() {
		CheckingAccount ca = new CheckingAccountGen(100);
		ca.deposit(50);
		double expV = 150;
		double actV = ca.getBalance();
		assertEquals(expV, actV, DELTA);
	}

	@Test
	public void testTransfer() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWithdraw() {
		CheckingAccount ca = new CheckingAccountGen(100);
		ca.withdraw(50); // current balance = 50
		double expV = 50;
		double actV = ca.getBalance();
		assertEquals(expV, actV, DELTA);
		double actWa = ca.withdraw(100);
		double expWa = 0;
		actV = ca.getBalance();
		assertEquals(expV, actV, DELTA);
		assertEquals(expWa, actWa, DELTA);
	}

}
