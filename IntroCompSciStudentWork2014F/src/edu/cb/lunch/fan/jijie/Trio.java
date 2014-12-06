package edu.cb.lunch.fan.jijie;



import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio implements MenuItem {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		Sandwich sandwich= getSandwich();
		Salad salad = getSalad();
		Drink drink = getDrink(); 
		StringBuilder name = new StringBuilder(); 
		name.append(sandwich + "/").append(salad + "/").append(drink + "Trio");
		
		return name.toString();
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	
	public static void main(String [] args){
		Sandwich sandwich = "PBJ";
		
	}
	*/
}
