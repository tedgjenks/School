package edu.cb.lunch.wicker.marshall;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
	}
	
	public String getName(){
		return this.getSandwich() .getName()+ "/" + this.getSalad().getName() + "/" + this.getDrink().getName() + " Trio";
	}
	
	public double getPrice(){
		double price = 0;
		double sandwichPrice = this.getSandwich().getPrice();
		double saladPrice = this.getSalad().getPrice();
		double drinkPrice = this.getDrink().getPrice();
		
		if (sandwichPrice <= saladPrice && sandwichPrice <= drinkPrice)
			price = saladPrice + drinkPrice;
		else if (saladPrice <= drinkPrice && saladPrice <= sandwichPrice)
			price = drinkPrice + sandwichPrice;
		else if (drinkPrice <= sandwichPrice && drinkPrice <= saladPrice)
			price = sandwichPrice + saladPrice;
		
		return price;
	}

}
