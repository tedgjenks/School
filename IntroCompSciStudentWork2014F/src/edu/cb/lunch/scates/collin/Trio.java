package edu.cb.lunch.scates.collin;
import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		String[] sandwich1 = {"Cheeseburger, Clubsandwich"};
		String [] salad1 = {"Spinach salad, Coleslaw"};
		String [] drink1 = {"Orange Soda, Cappuccino"};
	}

	@Override
	public String getName(){
		return null;
	}
	
	@Override
	public double getPrice() {
		String cheeseburger = "$2.75";
		String Clubsandwich = "$2.75";
		String Spinachsalad = "$1.25";
		String Coleslaw = "$1.25";
		String Orangesoda = "$1.25";
		String Cappuccino = "$3.50";
		return 0;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
