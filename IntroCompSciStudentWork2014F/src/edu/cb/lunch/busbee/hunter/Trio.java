package edu.cb.lunch.busbee.hunter;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;

import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;


public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);

	}
	public String getName(){
		String trioName = "" + getSandwich().getName() + "/" + getSalad().getName() + "/" + getDrink().getName() + " Trio";
		return trioName;

	}
	public double getPrice(){
		double trioPrice = getSandwich().getPrice() + getSalad().getPrice() + getDrink().getPrice();
		if(getSandwich().getPrice() < getSalad().getPrice() && getSandwich().getPrice() < getDrink().getPrice()){
			trioPrice -= getSandwich().getPrice();
		}
		else if(getDrink().getPrice() < getSalad().getPrice() && getDrink().getPrice() < getSandwich().getPrice()){
			trioPrice -= getDrink().getPrice();
		}
		else{
			trioPrice -= getSalad().getPrice();
		}
		return trioPrice;

	}

}
