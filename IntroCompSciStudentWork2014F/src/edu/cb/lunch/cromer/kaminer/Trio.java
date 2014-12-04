package edu.cb.lunch.cromer.kaminer;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		Sandwich sandwich = getSandwich();
		Salad salad = getSalad();
		Drink drink = getDrink();
		return null;
	}

	@Override
	public double getPrice() {
		return 0;
	}

}
