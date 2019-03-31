package edu.cb.lunch.newbold.griffin;
import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio implements MenuItem
{
	private Sandwich sandwich;
	private Salad salad;
	private Drink drink;
	public Trio(Sandwich sand, Salad sal, Drink dr){
		super(sand, sal, dr);
		this.sandwich = sand;
		this.salad = sal;
		this.drink = dr;
	}
	public String getName(){
		return sandwich.getName() + "/" + salad.getName() + "/" + drink.getName() + " Trio";
	}
	public double getPrice(){
		if(sandwich.getPrice() < salad.getPrice() && sandwich.getPrice() < drink.getPrice()){
			return salad.getPrice() + drink.getPrice();
		}else if(salad.getPrice() < sandwich.getPrice() && salad.getPrice() < drink.getPrice()){
			return sandwich.getPrice() + drink.getPrice();
		}else{
			return sandwich.getPrice() + salad.getPrice();
		}
	}
}
