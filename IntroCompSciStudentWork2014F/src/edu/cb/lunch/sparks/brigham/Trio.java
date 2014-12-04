package edu.cb.lunch.sparks.brigham;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink){
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String sandwichName = getSandwich().toString();
		String saladName = getSalad().toString();
		String drinkName = getDrink().toString();
		String name = sandwichName +"/"+saladName+"/"+drinkName+" Trio";
		return name;
	}

	@Override
	public double getPrice() {
		double price = 0.00;
		return price;
	}
}
