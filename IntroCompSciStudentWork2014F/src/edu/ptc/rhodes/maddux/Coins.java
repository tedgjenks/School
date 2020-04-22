package edu.ptc.rhodes.maddux;

import edu.jenks.dist.ptc.Coinable;
import java.text.NumberFormat;

public class Coins implements Coinable {

	private int quarters;
	private int dimes;
	private int nickels;
	private int pennies;
	private NumberFormat formatter;
	
	//constructor that is called to use a currency formatter
	public Coins() {
		formatter = NumberFormat.getCurrencyInstance();
	}
	
	//constructor that is used to take the parameters fed into the class, and set them to variables
	public Coins (int numPennies, int numNickels, int numDimes, int numQuarters) {
		quarters = numQuarters;
		dimes = numDimes;
		nickels = numNickels;
		pennies = numPennies;
		formatter = NumberFormat.getCurrencyInstance();
	}
	
	//used to test
	public static void main(String[] args) {
		//Coins test = new Coins();
		Coins test2 = new Coins(0, 15, 0, 0);
		System.out.println(test2.getDollarsFromDimes());
		System.out.println(test2.getDollarsFromQuarters());
		System.out.println(test2.getDollarsFromNickels());
		System.out.println(test2.getDollarsFromPennies());
		System.out.println(test2.getTotalDollars());
		System.out.println(test2.toString());
		System.out.println(test2.toString());
		//System.out.println(test.equals(test2));
	}
	
	//takes the amount of dimes, and calculates the amount to dollars, then formats it using the currency formatter
	public String getDollarsFromDimes() {
		double dollarsDimes = (getNumDimes() * 0.1);
		return formatter.format(dollarsDimes);
	}
	
	//takes the amount of nickels, and calculates the amount to dollars, then formats it using the currency formatter
	public String getDollarsFromNickels() {
		double dollarsNickels = (getNumNickels() * 0.05);
		return formatter.format(dollarsNickels);
	}
	
	//takes the amount of pennies, and calculates the amount to dollars, then formats it using the currency formatter
	public String getDollarsFromPennies() {
		double dollarsPennies = (getNumPennies() * 0.01);
		return formatter.format(dollarsPennies);	
	}
	
	//takes the amount of quarters, and calculates the amount to dollars, then formats it using the currency formatter
	public String getDollarsFromQuarters() {
		double dollarsQuarters = (getNumQuarters() * 0.25);
		return formatter.format(dollarsQuarters);
	}
	
	//returns the instance variable value from the class of dimes
	public int getNumDimes() {
		return dimes;
	}
	
	//returns the instance variable value from the class of nickels
	public int getNumNickels() {
		return nickels;
	}
	
	//returns the instance variable value from the class of pennies
	public int getNumPennies() {
		return pennies;
	}
	
	//returns the instance variable value from the class of quarters
	public int getNumQuarters() {
		return quarters;
	}

	//takes the values from the "getDollarsFrom..." methods, and adds them together, then formats it using the currency formatter
	public String getTotalDollars() {
		System.out.println(quarters);
		System.out.println(dimes);
		System.out.println(nickels);
		System.out.println(pennies);
		double dollarsFromCoins = (quarters * 0.25) + (dimes * 0.10) + (nickels * 0.05) + (pennies * 0.01);
		System.out.println(formatter.format(dollarsFromCoins));
		return formatter.format(dollarsFromCoins);
	}
	
	//sets the instance variable dimes, to the argument value
	public void setNumDimes(int arg0) {
		dimes = arg0;
	}

	//sets the instance variable nickels, to the argument value
	public void setNumNickels(int arg0) {
		nickels = arg0;
	}

	//sets the instance variable pennies, to the argument value
	public void setNumPennies(int arg0) {
		pennies = arg0;
	}

	//sets the instance variable quarters, to the argument value
	public void setNumQuarters(int arg0) {
		quarters = arg0;
	}
	
	//takes all of the instance variables and returns a string with all of them, for easily read information
	public String toString() {
		return "Quarters: " + quarters + "; " + "Dimes: " + dimes + "; " + "Nickels: " + nickels + "; " + "Pennies: " + pennies;
	}
	
	//compares two objects to see if they have the same amount of coins
	public boolean equals(Object obj) {
		Coinable compare = (Coinable) obj;
		if(compare.getNumQuarters() == this.getNumQuarters() && compare.getNumDimes() == this.getNumDimes() && compare.getNumNickels() == this.getNumNickels() && compare.getNumPennies() == this.getNumPennies()) {
			return true;
		}
		return false;
	}
}