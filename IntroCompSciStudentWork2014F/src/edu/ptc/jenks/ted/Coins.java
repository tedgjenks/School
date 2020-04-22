package edu.ptc.jenks.ted;
/**
 * 
 */


import java.text.NumberFormat;

import edu.jenks.dist.ptc.Coinable;

/**
 * @author Ted Jenks
 *
 */
public class Coins implements Coinable {
	private static final int PENNY_VALUE = 1;
	private static final int NICKEL_VALUE = 5;
	private static final int DIME_VALUE = 10;
	private static final int QUARTER_VALUE = 25;
	private static final NumberFormat CUR_INSTANCE = NumberFormat.getCurrencyInstance();
	
	public static void main(String[] args) {
		
	}
	
	private int numPennies, numNickes, numDimes, numQuarters;
	
	public Coins() {}
	
	public Coins(int numPennies, int numNickes, int numDimes, int numQuarters) {
		this.numPennies = numPennies;
		this.numNickes = numNickes;
		this.numDimes = numDimes;
		this.numQuarters = numQuarters;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		Coins arg = (Coins)obj;
		return numPennies == arg.getNumPennies() && 
				numNickes == arg.getNumNickels() && 
				numDimes == arg.getNumDimes() && 
				numQuarters == arg.getNumQuarters();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append("Quarters: ").append(numQuarters).append("; Dimes: ").append(numDimes);
		sb.append("; Nickels: ").append(numNickes).append("; Pennies: ").append(numPennies);
		return sb.toString();
	}

	@Override
	public int getNumPennies() {
		return numPennies;
	}

	@Override
	public int getNumNickels() {
		return numNickes;
	}

	@Override
	public int getNumDimes() {
		return numDimes;
	}

	@Override
	public int getNumQuarters() {
		return numQuarters;
	}

	@Override
	public void setNumPennies(int numPennies) {
		this.numPennies = numPennies;
	}

	@Override
	public void setNumNickels(int numNickels) {
		this.numNickes = numNickels;
	}

	@Override
	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

	@Override
	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}
	
	private String formatCentsToDollars(int cents) {
		double dollars = cents / 100;
		return CUR_INSTANCE.format(dollars);
	}

	@Override
	public String getTotalDollars() {
		int cents = getNumQuarters() * QUARTER_VALUE + getNumDimes() * DIME_VALUE +
				getNumNickels() * NICKEL_VALUE + getNumPennies() * PENNY_VALUE;
		return formatCentsToDollars(cents);
	}

	@Override
	public String getDollarsFromQuarters() {
		int cents = getNumQuarters() * QUARTER_VALUE;
		return formatCentsToDollars(cents);
	}

	@Override
	public String getDollarsFromDimes() {
		int cents = getNumDimes() * DIME_VALUE;
		return formatCentsToDollars(cents);
	}

	@Override
	public String getDollarsFromNickels() {
		int cents = getNumNickels() * NICKEL_VALUE;
		return formatCentsToDollars(cents);
	}

	@Override
	public String getDollarsFromPennies() {
		int cents = getNumPennies() * PENNY_VALUE;
		return formatCentsToDollars(cents);
	}

}
