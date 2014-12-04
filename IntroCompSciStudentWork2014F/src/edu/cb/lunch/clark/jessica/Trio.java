package edu.cb.lunch.clark.jessica;

import edu.jenks.dist.cb.lunch.AbstractTrio;

import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio implements MenuItem {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
		
	
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		Sandwich sandwich = null;
		Salad salad = null;
		Drink drink = null;
		return null;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
