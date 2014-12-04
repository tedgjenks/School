package edu.cb.lunch.patterson.andrew;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super (sandwich, salad, drink);
		
		
	}

	@Override
	public String getName() {
		String sandwich = getSandwich().toString();
		String drink = getDrink().toString();
		String salad = getSalad().toString();
		String trioname= sandwich + "/" + drink + "/" + salad +" Trio";
		return trioname;
	}

	@Override
	public double getPrice() {
		
		return 0;
	}

}
