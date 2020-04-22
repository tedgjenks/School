package edu.ptc.salter.bella;
import edu.jenks.dist.ptc.*;
import java.text.*;

public class Coins implements Coinable {
	final double PENNY_VAL = 0.01;
	final double NICKEL_VAL = 0.05;
	final double DIME_VAL = 0.1;
	final double QUARTER_VAL = 0.25;
	int numPennies, numNickels, numDimes, numQuarters;
	
	public static void main(String[] args) {
		Coins tester = new Coins(2, 3, 4, 0);
		Object tester2 = new Coins(2,3,4,0);
		System.out.println(tester.equals(tester2));
	}
	public Coins() {
		numPennies = 0;
		numNickels = 0;
		numDimes = 0;
		numQuarters = 0;
	}
	public Coins(int numP, int numN, int numD, int numQ) {
		numPennies = numP;
		numNickels = numN;
		numDimes = numD;
		numQuarters = numQ;
	}
	public boolean equals(Object obj) {
		if(!(obj instanceof Coinable)) {
			return false;
		}
		Coinable coinOBJ = (Coinable)obj;
		if(coinOBJ.getNumPennies() == numPennies && coinOBJ.getNumNickels() == numNickels && coinOBJ.getNumDimes() == numDimes && coinOBJ.getNumQuarters() == numQuarters) {
			return true;
		}else {
			return false;
		}
	}
	public String getTotalDollars() {
		double dollarsFromPennies = numPennies * PENNY_VAL;
		double dollarsFromNickels = numNickels * NICKEL_VAL;
		double dollarsFromDimes = numDimes * DIME_VAL;
		double dollarsFromQuarters = numQuarters * QUARTER_VAL;
		double numDollars = dollarsFromPennies + dollarsFromNickels + dollarsFromDimes + dollarsFromQuarters;
		String dollars = NumberFormat.getCurrencyInstance().format(numDollars);
		return dollars;
	}
	
	public String getDollarsFromPennies() {
		double dollarsFromPennies = numPennies * PENNY_VAL;
		String pennyDollars = NumberFormat.getCurrencyInstance().format(dollarsFromPennies);
		return pennyDollars;
	}
	public String getDollarsFromNickels() {
		double dollarsFromNickels = numNickels * NICKEL_VAL;
		String dollars = NumberFormat.getCurrencyInstance().format(dollarsFromNickels);
		return dollars;
	}
	public String getDollarsFromDimes() {
		double dollarsFromDimes = numDimes * DIME_VAL;
		String dollars = NumberFormat.getCurrencyInstance().format(dollarsFromDimes);
		return dollars;
	}
	public String getDollarsFromQuarters() {
		double dollarsFromQuarters = numQuarters * QUARTER_VAL;
		String dollars = NumberFormat.getCurrencyInstance().format(dollarsFromQuarters);
		return dollars;
	}
	public int getNumPennies() {
		return numPennies;
	}
	public int getNumNickels() {
		return numNickels;
	} 
	public int getNumDimes() {
		return numDimes;
	}
	public int getNumQuarters() {
		return numQuarters;
	}
	public void setNumPennies(int numP) {
		numPennies = numP;
	}
	public void setNumNickels(int numN) {
		numNickels = numN;
	}
	public void setNumDimes(int numD) {
		numDimes = numD;
	}
	public void setNumQuarters(int numQ) {
		numQuarters = numQ;
	}
	public String toString() {
		String str = "";
		str = "Quarters: " + numQuarters + "; Dimes: " + numDimes + "; Nickels: " + numNickels + "; Pennies: " + numPennies;
		return str;
	}
}
