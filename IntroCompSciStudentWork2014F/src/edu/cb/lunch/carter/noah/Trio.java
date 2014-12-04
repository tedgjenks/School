package edu.cb.lunch.carter.noah;

import edu.jenks.dist.cb.lunch.*;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;
import edu.jenks.dist.cb.lunch.SingleMenuItem;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	
	}

	@Override
	public String getName() {
		 String drinkName = getDrink().getName();
		 String saladName = getSalad().getName();
		 String sandwName = getSandwich().getName();
		 return(drinkName+"/"+saladName+"/"+sandwName);
	}

	@Override
	public double getPrice() {
		
		return 0;
	}

}
