/**
 * 
 */
package edu.ptc.jenks.ted;

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
	
	private int numPennies, numNickels, numDimes, numQuarters;
	
	public Coins() {}
	
	public Coins(int numPennies, int numNickels, int numDimes, int numQuarters) {
		this.numPennies = numPennies;
		this.numNickels = numNickels;
		this.numDimes = numDimes;
		this.numQuarters = numQuarters;
	}
	
	

	/**
	 * true if pennies, nickels, dimes, and quarters are all the same.
	 * 
	 * @param obj must be of type <code>Coinable</coin>
	 */
	@Override
	public boolean equals(Object obj) {
		Coinable arg = (Coinable)obj;
		return numPennies == arg.getNumPennies() && 
				numNickels == arg.getNumNickels() && 
				numDimes == arg.getNumDimes() && 
				numQuarters == arg.getNumQuarters();
	}

	/**
	 * Quarters: <code>numQuarters</code>; Dimes: <code>numDimes</code>; Nickels <code>numNickels</code>; Pennies: <code>numPennies</code>
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append("Quarters: ").append(numQuarters).append("; Dimes: ").append(numDimes);
		sb.append("; Nickels: ").append(numNickels).append("; Pennies: ").append(numPennies);
		return sb.toString();
	}

	@Override
	public int getNumPennies() {
		return numPennies;
	}

	@Override
	public int getNumNickels() {
		return numNickels;
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
		this.numNickels = numNickels;
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
		double dollars = cents / 100.0;
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
