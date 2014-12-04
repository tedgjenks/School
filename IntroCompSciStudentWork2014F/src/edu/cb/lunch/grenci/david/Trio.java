package edu.cb.lunch.grenci.david;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		Drink d = getDrink();
		setDrink(d);
		Sandwich sw = getSandwich();
		setSandwich(sw);
		Salad sd = getSalad();
		setSalad(sd);
		return null;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
