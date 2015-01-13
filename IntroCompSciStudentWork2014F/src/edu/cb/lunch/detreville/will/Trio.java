package edu.cb.lunch.detreville.will;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		String name = "";
		Sandwich sandwich = getSandwich();
		String SandwichName = sandwich.getName();
		Salad salad= getSalad();
		String SaladName = salad.getName();
		Drink drink = getDrink();
		String DrinkName = drink.getName();
		name = SandwichName + "/"+ SaladName +"/"+ DrinkName +"Trio";
		return name; 
	}

	@Override
	public double getPrice() {
		double price = 0.00d;
		Sandwich sandwich = getSandwich();
		double sandwichprice = sandwich.getPrice();
		Salad salad = getSalad();
		double saladprice = salad.getPrice();
		Drink drink = getDrink();
		double drinkprice = drink.getPrice();
		double price1 = sandwichprice + saladprice;
		double price2 = sandwichprice + drinkprice;
		double price3 = saladprice + drinkprice;
		if (price1 >= price2 && price1 >= price3 ) {
			price1 = price;
		}
		if (price >= price1 && price2 >= price) {
			price = price1;
		}
		if (price >= price && price3 >= price2) {
			price = price3;
		}
				
		return price;
		
	}

}
