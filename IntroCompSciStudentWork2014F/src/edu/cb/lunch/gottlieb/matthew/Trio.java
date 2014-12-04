package edu.cb.lunch.gottlieb.matthew;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich,Salad salad,Drink drink){
		super (sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String sandwich = getSandwich().toString();
		String salad = getSalad().toString();
		String drink = getDrink().toString();
		String trio = "";
		return null;	
	}

	@Override
	public double getPrice() {
		
		return 0;
	 
	}

}
