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
		
		String sandwich = getSandwich().getName();
		String salad = getSalad().getName();
		String drink = getDrink().getName();
		String Trio = sandwich + "/" + salad + "/" + drink + "Trio";
		return Trio;
	}

	@Override
	public double getPrice() {
		double price = 0;
		double sandwichPrice = getSandwich().getPrice();
		double saladPrice = getSalad().getPrice();
		double drinkPrice = getDrink().getPrice();
		price = sandwichPrice + saladPrice + drinkPrice;
		if (sandwichPrice < saladPrice && sandwichPrice < drinkPrice);{
			price = price - sandwichPrice;
		}if (saladPrice < sandwichPrice && saladPrice < drinkPrice);{
			price = price - saladPrice;
		}if (drinkPrice < sandwichPrice && drinkPrice < saladPrice);{
			price = price - drinkPrice;
		}
		return 0;
	}

}