package edu.cb.lunch.patterson.andrew;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super (sandwich, salad, drink);
		
		
	}

	@Override
	public String getName() {
		String trioname="";
		Sandwich sandwich=getSandwich();
		String sandwichName = sandwich.getName();
		Drink drink = getDrink();
		String drinkName=drink.getName();
		Salad salad=getSalad();
		String saladName = salad.getName();
		trioname= sandwichName + "/" + saladName + "/" + drinkName +" Trio";
		return trioname;
	}

	@Override
	public double getPrice() {
		double price=0.00d;
		Sandwich sandwich=getSandwich();
		double sandwichPrice=sandwich.getPrice();
		Drink drink=getDrink();
		double drinkPrice=drink.getPrice();
		Salad salad=getSalad();
		double saladPrice= salad.getPrice();
		double price1=sandwichPrice + saladPrice;
		double price2=sandwichPrice + drinkPrice;
		double price3=saladPrice+drinkPrice;
		if (price1 >= price2 && price1 >= price3){
			price=price1;
		}
		if (price2 >= price1 && price2 >= price3){
			price=price2;
		}
		if (price3 >= price1 && price3 >= price2){
			price=price3;
		}
		return price;
	}

}
