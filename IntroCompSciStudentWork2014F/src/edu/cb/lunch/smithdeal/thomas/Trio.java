package edu.cb.lunch.smithdeal.thomas;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio{

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super (sandwich, salad, drink);



	}


	@Override
	public String getName() {
		String sandwichName = getSandwich().getName();
		String saladName = getSalad().getName();
		String drinkName = getDrink().getName();
		String orderName = (sandwichName +"/" + saladName +"/" + drinkName  + " Trio");

		return orderName;
	}

	@Override
	public double getPrice() {
		double sandwichPrice = getSandwich().getPrice();
		double saladPrice = getSalad().getPrice();
		double drinkPrice = getDrink().getPrice();
		double orderTotal = 0.0;
		if (sandwichPrice >= saladPrice && saladPrice >= drinkPrice){
			orderTotal = sandwichPrice + saladPrice;
		}	
		if (saladPrice >= drinkPrice && drinkPrice >= sandwichPrice){
			orderTotal = saladPrice + drinkPrice;
		}		
		if (drinkPrice >= sandwichPrice && sandwichPrice >= saladPrice){
			orderTotal = sandwichPrice + drinkPrice;
		}
		return orderTotal;
	}

}
