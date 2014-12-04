package edu.cb.lunch.guareschi.marco;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {
	
	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		
		
	}

	@Override
	public String getName() {
		String trioName = "";
		String Sandwich = getSandwich(). toString();
		String Salad = getSalad(). toString();
		String Drink = getDrink(). toString();
		trioName = (Sandwich + "/" + Salad + "/" + Drink);
		return trioName;
	}

	@Override
	public double getPrice() {
		
		return 0;
	}
}