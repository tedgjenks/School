package edu.cb.lunch.deloach.hunter;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {
	
	public Trio(Sandwich sandwich, Salad salad, Drink drink){
		super(sandwich, salad, drink);
		
	}

	@Override
	public String getName() {
		String trioName= "";
		String Sandwich=getSandwich(). getName();
		String Salad= getSalad(). getName();
		String Drink= getDrink(). getName();
		trioName= (Sandwich + "/"+ Salad+ "/"+ Drink + "Trio");
		return trioName;
	}

	@Override
	public double getPrice() {
		double salad= getSalad().getPrice();
		double sandwich= getSandwich().getPrice();
		double drink= getDrink().getPrice();
		double price1= sandwich+salad;
		double price2=salad+drink;
		double price3=sandwich+drink;
		if (price1 >= price2 && price1>= price3){
			return price1;
		}
		if (price2>= price1 && price2>= price3) {
			return price2;
		}
		if (price3>= price2 && price3>= price1) {
			return price3;
		}
		
			
		
		return 0;
	}
}