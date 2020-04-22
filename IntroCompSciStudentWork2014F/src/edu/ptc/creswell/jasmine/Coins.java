package edu.ptc.creswell.jasmine;
import edu.jenks.dist.ptc.*;
import java.text.*;

public class Coins implements Coinable {
	
	private int pennies=0;
	private int nickles=0;
	private int dimes=0;
	private int quarters=0;
	//private NumberFormat fm; 
	
	public Coins() {
		//fm=NumberFormat.getCurrencyInstance();
		
	}
	
	public Coins(int numPennies, int numNickles, int numDimes, int numQuarters) {
		pennies=numPennies;
		nickles=numNickles;
		dimes=numDimes;
		quarters= numQuarters;
		
	}
	
	public static void main(String[] args) {
		Coins test=new Coins(1,2,3,4);
		System.out.println(test.getDollarsFromNickels());
		test.setNumNickels(1);
		System.out.println(test.getDollarsFromNickels());
		System.out.println(test.getDollarsFromPennies());
		System.out.println(test.getTotalDollars()); 
		
	}
	public boolean equals(Object obj) {
		Coinable test = ((Coinable) obj); 
		if (pennies ==test.getNumPennies() && nickles==test.getNumNickels() && dimes==test.getNumDimes() && quarters==test.getNumQuarters()) {
			return true;
		}
		return false;
		
	}
	public String centsToDollars(int c) {
		double cen=c;
		int dollars= (int) (cen/100);
		int cents= (int) (cen%100);
		if (cents>=10) {
			return "$"+ dollars +"." + cents;
		}
		return "$"+ dollars +".0" + cents;
		
	}
	public String toString() {
	//Quarters: numQuarters; Dimes: numDimes; Nickels numNickels; Pennies: numPennies
		return "Quarters: "+ quarters + "; Dimes: " + dimes + "; Nickels: " + nickles + "; Pennies: " + pennies+ ""; 
	}

	public String getDollarsFromDimes() {
		// TODO Auto-generated method stub
		int cents= dimes*10;
		
		return centsToDollars(cents);
		//return fm.format(getNumDimes() * 0.10);
	}

	public String getDollarsFromNickels() {
		// TODO Auto-generated method stub
		int cents=nickles*5;
		return centsToDollars(cents);
	}

	public String getDollarsFromPennies() {
		// TODO Auto-generated method stub
		int cents=pennies*1;
		return centsToDollars(cents);
	}

	public String getDollarsFromQuarters() {
		// TODO Auto-generated method stub
		int cents= quarters*25; 
		return centsToDollars(cents);
	}

	public int getNumDimes() {
		// TODO Auto-generated method stub
		return dimes;
	}

	public int getNumNickels() {
		// TODO Auto-generated method stub
		return nickles;
	}

	public int getNumPennies() {
		// TODO Auto-generated method stub
		return pennies;
	}

	public int getNumQuarters() {
		// TODO Auto-generated method stub
		return quarters;
	}

	public String getTotalDollars() {
		// TODO Auto-generated method stub
		return centsToDollars(getNumPennies()+ getNumNickels()*5 + getNumDimes()*10 + getNumQuarters()*25 );
	}

	public void setNumDimes(int arg0) {
		// TODO Auto-generated method stub
		dimes= arg0;
		
	}

	public void setNumNickels(int arg0) {
		// TODO Auto-generated method stub
		nickles=arg0;
		
	}

	public void setNumPennies(int arg0) {
		// TODO Auto-generated method stub
		pennies=arg0;
		
	}

	public void setNumQuarters(int arg0) {
		// TODO Auto-generated method stub
		quarters=arg0;
		
	}
	
	
	
	

}
