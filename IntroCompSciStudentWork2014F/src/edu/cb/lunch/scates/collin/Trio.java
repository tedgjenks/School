package edu.cb.lunch.scates.collin;
import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio implements MenuItem {
	private Sandwich sandwich;
	private Salad salad;
	private Drink drink;

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);		
	}

	@Override
	public String getName(){
		String SandwichName = getSandwich().getName();
		String SaladName = getSalad().getName();
		String DrinkName = getDrink().getName();
		String orderName = (SandwichName + "/" + SaladName + "/" + DrinkName + "/" + "Trio");
		return orderName;
	}
	
	@Override
	public double getPrice() {
		List<Double> pricelist = new ArrayList<Double>();
		double SandwichPrice = getSandwich().getPrice();
		double SaladPrice = getSalad().getPrice();
		double DrinkPrice = getDrink().getPrice();
		String orderlist = (SandwichPrice + "/" + SaladPrice + "/" + DrinkPrice + "/" + "Trio");
		return getPrice();
	}

	public static void main(String[] args) {

	}

}
