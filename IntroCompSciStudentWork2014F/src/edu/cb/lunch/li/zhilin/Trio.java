package edu.cb.lunch.li.zhilin;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich,Salad salad,Drink drink){
		super (sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String sandwich = getSandwich().getName();
		String salad = getSalad().getName();
		String drink = getDrink().getName();
		return getName();
	}

	@Override
	public double getPrice() {
		
		return getPrice();
	}

}
