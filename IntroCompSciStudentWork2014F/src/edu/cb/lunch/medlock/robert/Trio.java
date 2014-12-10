package edu.cb.lunch.medlock.robert;

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
		StringBuilder combo = new StringBuilder();
		combo.append(sandwich + "/").append(salad + "/").append(drink + " Trio");
		return combo.toString();
	}

	@Override
	public double getPrice() {
		double sandwich = getSandwich().getPrice();
		double salad = getSalad().getPrice();
		double drink = getDrink().getPrice();
		double price = sandwich + salad + drink;
		double lowest = 0;
		
		if (sandwich > salad && sandwich > drink) {
			price = price + 0; 
		} else if (sandwich > salad && sandwich < drink) {
			price = price + 0;
		} else if (sandwich < salad && sandwich > drink) {
			price = price + 0;
		} else { 
			lowest = sandwich;
		}
		if (salad > sandwich && salad > drink) {
			price = price + 0; 
		} else if (salad > sandwich && salad < drink) {
			price = price + 0;
		} else if (salad < sandwich && salad > drink) {
			price = price + 0;
		} else { 
			lowest = salad;
		}
		
		if (drink > salad && drink > sandwich) {
			price = price + 0; 
		} else if (drink > salad && drink < sandwich) {
			price = price + 0;
		} else if (drink > sandwich && drink < salad) {
			price = price + 0;
		} else { 
			lowest = drink; 
		}
			
		price = price - lowest; 
		return price;
	}

}
