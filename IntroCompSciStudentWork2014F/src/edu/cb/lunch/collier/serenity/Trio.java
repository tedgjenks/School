package edu.cb.lunch.collier.serenity;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
	}
	public void AbstractTrio(Sandwich sandwich,Salad salad,Drink drink){
}
	public String getName(){
		String Sandwich= getSandwich().getName();
		String Salad= getSalad().getName();
		String Drink= getDrink().getName();
    	String Name= Sandwich+ "/" + Salad + "/" + Drink + " " + "Trio";
    	
		return Name;		
    }
    public double getPrice(){
    	double Sandwich= getSandwich().getPrice();
		double Salad= getSalad().getPrice();
		double Drink= getDrink().getPrice();
    	double lowPrice=Sandwich;
		if(Salad<lowPrice){
			lowPrice=Salad;
		}
		if (Drink<lowPrice){
			lowPrice=Drink;
		}
		double Price=(Sandwich+Salad+Drink)-lowPrice;
		return Price;
    	
    }
    public void Drink(String name,double price){
    	
    }
            
}
