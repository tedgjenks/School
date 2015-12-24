package edu.cb.lunch.ramsey.will;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getName(){
		String name = getSandwich().getName() + "/" + getSalad().getName() + "/" + getDrink().getName() + " Trio";
		return name;
	}
	@Override
	public double getPrice(){
		double price = 0;
		double salad = getSalad().getPrice();
		double sandwich = getSandwich().getPrice();
		double drink = getDrink().getPrice();
		if(salad >= drink && sandwich >= drink)
			price = salad+sandwich;
		else if(drink >= salad && sandwich >= salad)
			price = drink + sandwich;
		else if(salad >= sandwich && drink >= sandwich)
			price = salad + drink;
		return price;
	}
}
