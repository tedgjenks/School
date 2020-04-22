package edu.cb.lunch.whitt.rose;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public static void main(String[] args) {
		Trio t = new Trio(new Sandwich("PB&J", 2.00), new Salad("Caesar", 8.00), new Drink("Sweet Tea", 3.00));
		System.out.println(t.getSalad().getName());
		
	}
	
	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
	}
	
	//the sandwhich name/salad name/drink name/space/trio
	public String getName() {
		return getSandwich().getName() + "/" + getSalad().getName() + "/" + getDrink().getName() + " Trio";
	}

	//the price of the trio is the sum of the two highest-priced
	//menu items in the trio; one item with the lowest price is free
	public double getPrice() {
		double sandwich = getSandwich().getPrice(); //a
		double salad = getSalad().getPrice(); //b
		double drink = getDrink().getPrice(); //c
		
		double lowest = 0;
		if (sandwich <= salad && sandwich <= drink) {
			lowest = sandwich;
			return salad + drink;
		} else if (salad <= drink && salad <= sandwich) {
			lowest = salad;
			return sandwich + drink;
		} else {
			lowest = drink;
			return sandwich + salad;
		}
	}
}
