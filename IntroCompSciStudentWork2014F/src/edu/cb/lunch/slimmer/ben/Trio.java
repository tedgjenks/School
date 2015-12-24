package edu.cb.lunch.slimmer.ben;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio
extends AbstractTrio{

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}
	
	public String getName(){
		return this.getSandwich().getName()+"/"+this.getSalad().getName()+"/"+this.getDrink().getName()+" Trio";
	}
	
	public double getPrice(){
		double price1= this.getSandwich().getPrice();
		double price2= this.getSalad().getPrice();
		double price3= this.getDrink().getPrice();
		double higher;
		double middle;
		if(price1>price2){
			higher=price1;
			middle=price2;
		}
		else{
			higher=price2;
			middle=price1;
		}
		if(price3>middle)
			middle=price3;
		return higher+middle;
	}

}
