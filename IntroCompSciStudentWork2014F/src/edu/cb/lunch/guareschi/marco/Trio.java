package edu.cb.lunch.guareschi.marco;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {
	
	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
			
	}

	@Override
	public String getName() {
		String trioName = "";
		String Sandwich = getSandwich(). getName();
		String Salad = getSalad(). getName();
		String Drink = getDrink(). getName();
		trioName = (Sandwich + "/" + Salad + "/" + Drink + " Trio");
		return trioName;
	}

	@Override
	public double getPrice() {
		double sandwich = getSandwich().getPrice();
		double salad = getSalad().getPrice();
		double drink = getDrink().getPrice();
		double price1= sandwich + salad;
		double price2 = salad+ drink;
		double price3= sandwich + drink;
		double priceReturn = 0;
		if (price1 >= price2 && price1>=price3){
			priceReturn+= price1;
		}
		if (price2>= price1 && price2>= price3){
			priceReturn += price2;
		}
		if (price3>= price1 && price3>= price2){
			priceReturn += price3;
		}
		if(sandwich > drink && drink == salad){
			return sandwich + drink;
		}
		return priceReturn;
	}
}