package edu.cb.lunch.higginbotham.andrew;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio implements MenuItem {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);

	}

	public String getName(){
		String sandwich = this.getSandwich().getName();
		String salad = this.getSalad().getName();
		String drink = this.getDrink().getName();
		return sandwich + "/" + salad + "/" + drink + " Trio";
	}
	public double getPrice(){
		double total = 0;
		double sandwichP = this.getSandwich().getPrice();
		double saladP = this.getSalad().getPrice();
		double drinkP = this.getDrink().getPrice();
		if(sandwichP > saladP && sandwichP > drinkP){	
			total+= sandwichP;
			if(saladP >= drinkP)
				total+= saladP;
			else
				total += drinkP;
		}
		else if(saladP > sandwichP && saladP > drinkP){
			total += saladP;
			if(sandwichP >= drinkP)
				total+=sandwichP;
			else
				total+=drinkP;
		}
		else if(drinkP > sandwichP && drinkP > saladP){
			total+= drinkP;
			if(sandwichP >= saladP)
				total+= sandwichP;
			else total+= saladP;
		}
		return total;
	}
}
