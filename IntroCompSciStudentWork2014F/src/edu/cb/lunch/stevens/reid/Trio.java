package edu.cb.lunch.stevens.reid;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio{

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	public String getName() {
		return getSandwich().getName() + "/" +
			   getSalad().getName() + "/" +
			   getDrink().getName() + " Trio";
	}

	public double getPrice() {
		double sandwichAndSalad = getSandwich().getPrice() + getSalad().getPrice();
		double saladAndDrink = getSalad().getPrice() + getDrink().getPrice();
		double sandwichAndDrink = getSandwich().getPrice() + getDrink().getPrice();
		
		if (sandwichAndSalad <= saladAndDrink){
			if (sandwichAndSalad <= sandwichAndDrink){
				return sandwichAndSalad;
			}
			else{
				return sandwichAndDrink;
			}
		}
		else{
			if (saladAndDrink <= sandwichAndDrink){
				return saladAndDrink;
			}
			else{
				return sandwichAndDrink;
			}
		}
	}
	
}
