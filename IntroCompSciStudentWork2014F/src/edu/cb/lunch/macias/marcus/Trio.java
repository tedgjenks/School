package edu.cb.lunch.macias.marcus;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio{
	public static void main(String[] args) {
		Sandwich sandwich = new Sandwich("Cheesburger",2.75);
		Salad salad = new Salad("Spinach Salad",1.25);
		Drink drink = new Drink("Orange Soda",1.25);
		Trio run = new Trio(sandwich,salad,drink);
		System.out.println(run.getName());
		System.out.println(run.getPrice());
		sandwich = new Sandwich("Club Sandwich",2.75);
		salad = new Salad("Coleslaw",1.25);
		drink = new Drink("Cappuccino", 3.50);
		run = new Trio(sandwich,salad,drink);
		System.out.println(run.getName());
		System.out.println(run.getPrice());
		
	}
	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		
	}
	
	public String getName() {
		String sandwich = this.getSandwich().getName();
		String salad = this.getSalad().getName();
		String drink = this.getDrink().getName();
		
		return sandwich+"/"+salad+"/"+drink+" Trio";
	}

	public double getPrice() {
		double[] prices = {getSandwich().getPrice(),this.getSalad().getPrice(),getDrink().getPrice()};
		if(prices[0] >= prices[1] && prices[1] >= prices[2]) {
			return prices[0] + prices[1];
		}
		if(prices[0] >= prices[1] && prices[2] >= prices[0]) {
			return prices[0] + prices[2];
		}else {
			return prices[2] + prices[1];
		}
		
	}
}
