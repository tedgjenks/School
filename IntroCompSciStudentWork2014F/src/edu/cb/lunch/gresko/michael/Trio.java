package edu.cb.lunch.gresko.michael;

import edu.jenks.dist.cb.lunch.*;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		
	}

	@Override
	public String getName() {
		String sandwichObj = getSandwich().getName();
		String saladObj = getSalad().getName();
		String drinkObj = getDrink().getName();
		StringBuilder name = new StringBuilder();
		name.append(sandwichObj + "/").append(saladObj + "/").append(drinkObj + " Trio");
		return name.toString();
	}

	@Override
	public double getPrice() {
		double price = 0;
		double sandwichPrice = getSandwich().getPrice();
		double saladPrice = getSalad().getPrice();
		double drinkPrice = getDrink().getPrice();
		double lowestValue = 0;
		price = sandwichPrice + saladPrice + drinkPrice;
		if (sandwichPrice > saladPrice && sandwichPrice > drinkPrice) {
			price = price + 0;
		} else if (sandwichPrice < saladPrice && sandwichPrice > drinkPrice) {
			price = price + 0;
		} else if (sandwichPrice > saladPrice && sandwichPrice < drinkPrice) {
			price = price + 0;
		} else {
			lowestValue = sandwichPrice;
		}
		if (sandwichPrice < saladPrice && saladPrice > drinkPrice) {
			price = price + 0;
		} else if (sandwichPrice < saladPrice && saladPrice < drinkPrice) {
			price = price + 0;
		} else if (sandwichPrice > saladPrice && saladPrice > drinkPrice) {
			price = price + 0;
		} else {
			lowestValue = saladPrice;
		}
		if (drinkPrice > saladPrice && sandwichPrice < drinkPrice) {
			price = price + 0;
		} else if (drinkPrice > saladPrice && sandwichPrice > drinkPrice) {
			price = price + 0;
		} else if (drinkPrice < saladPrice && sandwichPrice < drinkPrice) {
			price = price + 0;
		} else {
			lowestValue = drinkPrice;
		}
		price = price - lowestValue;
		return price;
	}

}
