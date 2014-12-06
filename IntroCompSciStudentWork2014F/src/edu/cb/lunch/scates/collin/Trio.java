package edu.cb.lunch.scates.collin;
import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio implements MenuItem {
	private Sandwich sandwich;
	private Salad salad;
	private Drink drink;

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		this.sandwich = sandwich;
		this.salad = salad;
		this.drink = drink;
		
	}

	@Override
	public String getName(){
		Sandwich Sandwich = new Sandwich ("Cheeseburger", 2.00);
		Salad Salad = new Salad ("Cesar", 1.00);
		Drink Drink = new Drink ("Soft Drink", 1.00);
		return getName();
	}
	
	@Override
	public double getPrice() {
		sandwich.getPrice();
		salad.getPrice();
		drink.getPrice();
		return 0;
	}
	

	public static void main(String[] args) {

	}

}
