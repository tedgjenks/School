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
		Sandwich sandwichObj = getSandwich();
		Salad saladObj = getSalad();
		Drink drinkObj = getDrink();
		StringBuilder name = new StringBuilder();
		name.append(sandwichObj + "/");
		name.append(saladObj + "/");
		name.append(drinkObj);
		return name.toString();
	}

	@Override
	public double getPrice() {
		int price = 0;
		String[] name = getName().split("/");
		String sandwich = name[0];
		String salad = name[1];
		String drink = name[2];
		String sChoiceOne = "Cheeseburger";
		if (sandwich.equals(sChoiceOne)) { 
			//price = price + 2.75;
		}
		return price;
	}

}
