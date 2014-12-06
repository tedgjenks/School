package edu.cb.lunch.carter.noah;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	
	}

	@Override
	public String getName() {
		String sandwich = getSandwich().getName();
		String salad = getSalad().getName();
		String drink = getDrink().getName();
		String trioName = (sandwich + "/" + salad + "/" + drink +  " " + "Trio");
		return trioName;
	}
	
	@Override
	public double getPrice() {
		double totalPrice = 0.00;
		double sandwPrice = getSandwich().getPrice();
		double saladPrice = getSalad().getPrice();
		double drinkPrice = getDrink().getPrice();
		if (sandwPrice >= saladPrice && saladPrice >= drinkPrice)
			totalPrice = saladPrice + sandwPrice;
		if (saladPrice >= drinkPrice && drinkPrice >= sandwPrice)
			totalPrice = saladPrice + drinkPrice;
		if (drinkPrice >= sandwPrice && sandwPrice >= saladPrice)
			totalPrice = drinkPrice + sandwPrice;
	return totalPrice;
		
		
		
	}
}


