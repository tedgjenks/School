package edu.ptc.burroughs.trent;

import edu.jenks.dist.ptc.*;
import java.text.*;

public class Coins implements Coinable {

	private int numPennies, numNickels, numDimes, numQuarters;
	private NumberFormat formatter;
	
	public static void main(String[] args) {
		Coins c = new Coins();
		c.setNumDimes(10);
		c.setNumQuarters(2);
		c.setNumPennies(7);
		c.setNumNickels(1);
		System.out.println(c.getDollarsFromDimes());
		System.out.println(c.getDollarsFromQuarters());
		System.out.println(c.getDollarsFromPennies());
		System.out.println(c.getDollarsFromNickels());
		System.out.println(c.getTotalDollars());
	}

	public Coins() {
		formatter = NumberFormat.getCurrencyInstance();
	}

	public Coins(int numP, int numN, int numD, int numQ) {
		numPennies = numP;
		numNickels = numN;
		numDimes = numD;
		numQuarters = numQ;
	}

	//this gets the number of dimes
	public int getNumDimes() {
		return numDimes;
	}

	//this gets the number of nickels
	public int getNumNickels() {
		return numNickels;
	}

	//the gets the number of pennies
	public int getNumPennies() {
		return numPennies;
	}

	//this gets the number of quarters
	public int getNumQuarters() {
		return numQuarters;
	}

	//this sets the number of dimes
	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

	//this sets the number of nickels
	public void setNumNickels(int numNickels) {
		this.numNickels = numNickels;
	}

	//this sets the number of pennies
	public void setNumPennies(int numPennies) {
		this.numPennies = numPennies;
	}

	//this sets the number of quarters
	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	//this gets the amount of dollars from dimes
	public String getDollarsFromDimes() {
		return formatter.format(numDimes * .10);
	}

	//this gets the amount of dollars from nickels
	public String getDollarsFromNickels() {
		return formatter.format(numNickels * .05);
	}

	//this gets the amount of dollars from pennies
	public String getDollarsFromPennies() {
		return formatter.format(numPennies * .01);
	}

	//this gets the amount of dollars from quarters
	public String getDollarsFromQuarters() {
		return formatter.format(numQuarters * .25);
	}

	//this gets the amount of dollars from all currencies
	public String getTotalDollars() {
		return formatter.format((numDimes * .10) + (numNickels * .05) + (numPennies * .01) + (numQuarters * .25));
	}

	//this checks to see if two coin obj have the same values
	public boolean equals(Object obj) {
		if (this.getNumDimes() == ((Coinable) obj).getNumDimes()
				&& this.getNumNickels() == ((Coinable) obj).getNumNickels()
				&& this.getNumPennies() == ((Coinable) obj).getNumPennies()
				&& this.getNumQuarters() == ((Coinable) obj).getNumQuarters()) {
			return true;
		}
		return false;
	}

	public String toString() {
		System.out.println("Quarters: " + numQuarters + "; Dimes: " + numDimes + "; Nickels: " + numNickels
				+ "; Pennies: " + numPennies);
		return ("Quarters: " + numQuarters + "; Dimes: " + numDimes + "; Nickels: " + numNickels
				+ "; Pennies: " + numPennies);
	}

}
