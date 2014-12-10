package edu.cb.lunch.smith.eli;

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
		String name = sandwich + "/" + salad + "/" + drink + " Trio";
		return name;
	}

	@Override
	public double getPrice() {
		double sandwich = getSandwich().getPrice();
		double salad = getSalad().getPrice();
		double drink = getDrink().getPrice();
		double price = sandwich + salad + drink;
		if (drink < salad && drink < sandwich){
			price = price - drink;
		}
		else if (salad < drink && salad < sandwich){
			price = price - salad;
		}
		else if (sandwich < drink && sandwich < salad){
			price = price - sandwich;
		}
		else if (sandwich == drink){
			price = price - sandwich;
		}
		else if (sandwich == salad){
			price = price - sandwich;
		}
		else if (salad == drink){
			price = price - salad;
		}
		else if (salad == drink && salad == sandwich){
			price = price - salad;
		}
			
		return price;
	}

}
