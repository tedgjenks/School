package edu.cb.lunch.burroughs.lauren;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}


	@Override
	public String getName(){
		setDrink(getDrink());
		setSalad(getSalad());
		setSandwich(getSandwich());
		String sand = getSandwich().getName();
		String salad = getSalad().getName();
		String drink = getDrink().getName();
		return sand + "/" + salad + "/" + drink + " Trio";
		
	}
	@Override
	public double getPrice(){
		setDrink(getDrink());
		setSalad(getSalad());
		setSandwich(getSandwich());
		double sand = getSandwich().getPrice();
		double salad = getSalad().getPrice();
		double drink = getDrink().getPrice();
		double returnval;
		if(sand<salad && sand<drink)
			returnval = salad + drink;
		else if(sand > salad && drink > salad)
			returnval = sand + drink;
		else
			returnval = sand + salad;
		
		return returnval;
	}
	
}
