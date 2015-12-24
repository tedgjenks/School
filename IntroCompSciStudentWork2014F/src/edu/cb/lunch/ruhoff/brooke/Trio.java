package edu.cb.lunch.ruhoff.brooke;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {
	public Trio(Sandwich sandwich, Salad salad, Drink drink){
		super(sandwich, salad, drink);
	}
public String getName(){
	String sandwichName=getSandwich().getName();
	String saladName=getSalad().getName();
	String drinkName=getDrink().getName();
	String name=sandwichName + "/" + saladName + "/" + drinkName + " " + "Trio";
	return name;
}
public double getPrice(){
	double highPrice=0;
	if(highPrice<getSalad().getPrice()){
		highPrice=getSalad().getPrice();
	}
	else if(highPrice<getDrink().getPrice())
		highPrice=getDrink().getPrice();
	else if(highPrice<getSandwich().getPrice()){
		highPrice=getSandwich().getPrice();
	}
	double lowPrice=getSalad().getPrice();
	if(lowPrice>getDrink().getPrice())
		lowPrice=getDrink().getPrice();
	else if(lowPrice>getSandwich().getPrice()){
		lowPrice=getSandwich().getPrice();
	}
	double fullPrice=getSalad().getPrice()+getDrink().getPrice()+getSandwich().getPrice();
	double price=fullPrice-lowPrice;
	return price;
	
}
}