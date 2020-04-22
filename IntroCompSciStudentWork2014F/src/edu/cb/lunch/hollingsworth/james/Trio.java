package edu.cb.lunch.hollingsworth.james;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {
	private double price;
	private String name;

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		name = sandwich.getName() + "/" + salad.getName() + "/" + drink.getName() + " Trio";
		double lowest = Integer.MAX_VALUE;
		if(sandwich.getPrice() < lowest) lowest = sandwich.getPrice();
		if(salad.getPrice() < lowest) lowest = salad.getPrice();
		if(drink.getPrice() < lowest) lowest = drink.getPrice();
		price = sandwich.getPrice() + salad.getPrice() + drink.getPrice() - lowest;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}

	public static void main(String[] args) {

	}

}
