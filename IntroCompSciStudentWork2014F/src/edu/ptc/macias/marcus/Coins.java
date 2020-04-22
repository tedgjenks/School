package edu.ptc.macias.marcus;

import edu.jenks.dist.ptc.Coinable;
import java.text.NumberFormat;
public class Coins implements Coinable {
	int pennies = 0;
	int nickels = 0;
	int dimes = 0;
	int quarters = 0;
	private NumberFormat formatter;
	public Coins() {
		formatter = NumberFormat.getCurrencyInstance();
	}
	public Coins(int numPennies, int numNickels, int numDimes, int numQuarters) {
		pennies = numPennies;
		nickels = numNickels;
		dimes = numDimes;
		quarters = numQuarters;
	}
	public static void main(String[] args) {
		Coins run = new Coins();
		System.out.println(run.getTotalDollars());
		/*System.out.println(run.getDollarsFromDimes());
		System.out.println(run.getDollarsFromPennies());
		System.out.println(run.getDollarsFromQuarters());
		System.out.println(run.getDollarsFromNickels());*/
		System.out.println(run.toString());
	}
	public boolean equals(Object obj) {
		if((this.getNumQuarters() == ((Coinable)obj).getNumQuarters()) && (this.getNumDimes() == ((Coinable)obj).getNumDimes()) && (this.getNumPennies() == ((Coinable)obj).getNumPennies()) && (this.getNumNickels() == ((Coinable)obj).getNumNickels())) {
			return true;
		}
		return false;
	}
	public String toString() {
		String answer = "Quarters: " + getNumQuarters() + "; Dimes: " + getNumDimes() + "; Nickels: " + getNumNickels() + "; Pennies: " + getNumPennies() ;
		
		return answer;
	}
	public String getDollarsFromDimes() {
		
		double dollars = (getNumDimes() * 10.0) / 100.0;
		return formatter.format(dollars);
	}
	
	public String getDollarsFromNickels() {
		double dollars = (getNumNickels() * 5.0) / 100.0;
		return formatter.format(dollars);
	}
	
	public String getDollarsFromPennies() {
		double dollars = getNumPennies() / 100.0;
		return formatter.format(dollars);
	}
	
	public String getDollarsFromQuarters() {
		double dollars = (getNumQuarters() * 25.0) / 100.0;
		return formatter.format(dollars);
	}
	
	public int getNumDimes() {
		return dimes;
	}
	
	public int getNumNickels() {
		return nickels;
	}
	public int getNumPennies() {
		return pennies;
	}
	
	public int getNumQuarters() {
		return quarters;
	}
	
	public String getTotalDollars() {
		double dollarP = getNumPennies() /100.0;
		double dollarD = (getNumDimes() * 10.0) / 100.0;
		double dollarQ = (getNumQuarters() * 25.0) / 100.0;
		double dollarN = (getNumNickels() * 5.0) / 100.0;
		return formatter.format(dollarP + dollarD + dollarQ + dollarN);
	}
	
	public void setNumDimes(int arg0) {
		dimes = arg0;
	}
	
	public void setNumNickels(int arg0) {
		nickels = arg0;
	}
	
	public void setNumPennies(int arg0) {
		pennies = arg0;
	}
	
	public void setNumQuarters(int arg0) {
		quarters = arg0;
	}

}
