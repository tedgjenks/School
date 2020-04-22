package edu.cb.lunch.rhodes.maddux;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio implements MenuItem{

	public static void main(String[] args) {
		Sandwich sandy = new Sandwich("Sandy Man", 1.50);
		Salad sally = new Salad("Sally Man", 1.50);
		Drink drinky = new Drink("Drinky Man", 1.00);
		Trio trio = new Trio(sandy, sally, drinky);
		System.out.println(trio.getName());
		System.out.println(trio.getPrice());
	}
	
	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	public String getName() {
		return getSandwich().getName() + "/" + getSalad().getName() + "/" + getDrink().getName() + " Trio";
	}
	
	public double getPrice() {
		double sandyP = getSandwich().getPrice();
		double saladP = getSalad().getPrice();
		double drinkP = getDrink().getPrice();
		if((sandyP >= saladP) && (saladP >= drinkP)) {
			return sandyP + saladP;
		}else if((sandyP >= drinkP) && (drinkP >= saladP)) {
			return sandyP + drinkP;
		}else if((saladP >= drinkP) && (drinkP >= sandyP)) {
			return saladP + drinkP;
		}else if((saladP >= sandyP) && (sandyP >= drinkP)) {
			return saladP + sandyP;
		}else if((drinkP >= saladP) && (saladP >= sandyP)) {
			return drinkP + saladP;
		}else if((drinkP >= sandyP) && (sandyP >= saladP)) {
			return drinkP + sandyP;
		}
		return 0;
	}
}
