package edu.cb.lunch.fan.jijie;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio implements MenuItem {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		
		
		String sandwich= getSandwich().getName();

		
		String salad = getSalad().getName();
		String drink = getDrink().getName();
		
		StringBuilder n = new StringBuilder(); 
		StringBuilder name= n.append(sandwich + "/").append(salad + "/").append(drink + " Trio");
		
		return name.toString();
	}
		

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		Double sandwich= getSandwich().getPrice();
		Double salad = getSalad().getPrice();
		Double drink = getDrink().getPrice();
		
		if(sandwich>=salad && salad>=drink)
			return sandwich+salad;	
		
		if(sandwich>=drink && drink>=salad)
			return sandwich+drink;
		
		if(salad >=sandwich && sandwich >=drink)
			return salad+sandwich;
		
		if(salad>=drink && drink >= sandwich)
			return salad+drink;
		
		if(drink>= sandwich && sandwich >= salad)
			return drink+sandwich;
		
		if(drink >= salad && salad >= sandwich )
			return drink+salad;
		
		return 0;
		}
}
