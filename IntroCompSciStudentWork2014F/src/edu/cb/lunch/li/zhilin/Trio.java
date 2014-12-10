package edu.cb.lunch.li.zhilin;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich,Salad salad,Drink drink){
		super (sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String sandwich = getSandwich().getName();
		String salad = getSalad().getName();
		String drink = getDrink().getName();
		String name = sandwich + "/"  + salad + "/" + drink + " Trio";
		return name;
	}

	@Override
	public double getPrice() {
		double sandwich = getSandwich().getPrice();
		double salad = getDrink().getPrice();
		double drink = getSalad().getPrice();
		double amount = salad + sandwich + drink;
		if (sandwich < salad && sandwich < drink){
			amount = amount - sandwich;
		}
		else if (salad < sandwich && salad < drink){
			amount = amount - salad;
		}
		else if (drink < salad && drink < sandwich){
			amount = amount - drink;
		}
		else if (drink == salad && salad < sandwich && drink < sandwich)
			amount = amount - salad;
		else if (drink == sandwich && drink < salad && sandwich < salad)
			amount = amount - sandwich;
		return amount;
	}

}
