package edu.cb.lunch.tran.duc;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio implements MenuItem {

	

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String sandwich = getSandwich().getName();
		String salad = getSalad().getName();
		String drink = getDrink().getName();
		String order = (sandwich + "/" + salad + "/" + drink + " Trio");
		return order;
	}

	@Override
	public double getPrice() {
		double price1 = getSandwich().getPrice();
		double price2 = getSalad().getPrice();
		double price3 = getDrink().getPrice();
		if(price1 >= price2 && price2 >= price3){
			return price1 + price2;
		}
		if(price2 >= price3 && price3 >= price1){
			return price2 + price3;
		}
		if(price3 >= price1 && price1 >= price2){
			return price3 + price1;
		}
		return 0;
	}

}
