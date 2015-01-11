package edu.cb.lunch.latham.chase;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio implements MenuItem {

	
	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		
	}

	@Override
	public String getName() {
		String sandwichName = getSandwich().getName();
		String saladName = getSalad().getName();
		String drinkName = getDrink().getName();
		String orderName =(sandwichName + "/" +saladName + "/" +drinkName + "/" + "Trio");		
		return orderName;
	}

	@Override
	public double getPrice() {
		//List<Double> priceList = new ArrayList<Double>();
		//priceList.add(getSandwich().getPrice());
		//priceList.add(getSalad().getPrice());
		//priceList.add(getDrink().getPrice());
		double sandwichPrice = getSandwich().getPrice();
		double saladPrice = getSalad().getPrice();
		double drinkPrice = getDrink().getPrice();
		double price = sandwichPrice + saladPrice + drinkPrice;
		return price;
	}
	

}
