package edu.cb.lunch.scates.collin;
import java.util.ArrayList;
import java.util.List;

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
	public String getName(){
		String SandwichName = getSandwich().getName();
		String SaladName = getSalad().getName();
		String DrinkName = getDrink().getName();
		String orderName = (SandwichName + "/" + SaladName + "/" + DrinkName + " Trio");
		return orderName;
	}
	
	@Override
	public double getPrice() {
		List<Double> priceList = new ArrayList<Double>();
		priceList.add(getSandwich().getPrice());
		priceList.add(getSalad().getPrice());
		priceList.add(getDrink().getPrice());
		return 0;
	}

	public static void main(String[] args) {

	}
}
