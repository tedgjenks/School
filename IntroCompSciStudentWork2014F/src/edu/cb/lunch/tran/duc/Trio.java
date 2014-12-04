package edu.cb.lunch.tran.duc;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio implements MenuItem {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String sandwichorder = null ;
		String saladorder = null;
		String drinkorder = null;
		StringBuilder order = new StringBuilder();
		
		return null;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
