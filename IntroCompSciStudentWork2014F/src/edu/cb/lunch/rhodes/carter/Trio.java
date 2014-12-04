package edu.cb.lunch.rhodes.carter;

import edu.jenks.dist.cb.lunch.AbstractTrio;

import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;
import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio implements MenuItem {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		
	}

	@Override
	public String getName() {
		Sandwich sandwich = null;
		Salad salad = null;
		Drink drink = null;
		String name = ((sandwich)+"/"+(salad)+"/"+(drink)+" Trio");
		return name;
	}

	@Override
	public double getPrice() { 
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub

	}

}
