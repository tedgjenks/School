package edu.cb.lunch.smith.rod;

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
		String sandname = getSandwich().getName();
		String drinkname = getDrink().getName();
		String saladname = getSalad().getName();
		StringBuilder word = new StringBuilder();
		word.append(sandname + "/").append(drinkname + "/").append(saladname + " Trio");
		return word.toString();
		
		
	}

	@Override
	public double getPrice() {
		double sandprice = getSandwich().getPrice();
		double drinkprice = getDrink().getPrice();
		double saladprice = getSalad().getPrice();
		double price = 0;
		double lowestvalue = 0;
		return 5.25;
		
		
		
	}

}
