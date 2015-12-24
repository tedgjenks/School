package edu.cb.lunch.shearer.richard;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}	
	public String getName(){
		String SandwichName = getSandwich().getName();
		String SaladName = getSalad().getName();
		String DrinkName = getDrink().getName();
		return SandwichName + "/" + SaladName + "/" + DrinkName + " " + "Trio";
	}
	public double getPrice(){
		double totalcost = 0;
		double SandwichPrice = getSandwich().getPrice();
		double SaladPrice = getSalad().getPrice();
		double DrinkPrice = getDrink().getPrice();
		if (SaladPrice >= SandwichPrice && DrinkPrice >= SandwichPrice){
			SandwichPrice = 0;}
		else if (SandwichPrice >= SaladPrice && DrinkPrice >= SaladPrice){
			SaladPrice = 0;}
		else if (SaladPrice >= DrinkPrice && SandwichPrice >= DrinkPrice){
			DrinkPrice = 0;}
		totalcost = SandwichPrice + SaladPrice + DrinkPrice;
		return totalcost;
	}
}