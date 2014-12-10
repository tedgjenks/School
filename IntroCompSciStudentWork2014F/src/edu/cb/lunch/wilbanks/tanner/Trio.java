package edu.cb.lunch.wilbanks.tanner;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {


	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		
	}

	@Override
	public String getName() {
		String sandname = getSandwich().getName();
		String drinkname = getDrink().getName();
		String saladname = getSalad().getName();
		String name = sandname + "/" + saladname + "/" + drinkname + " Trio";
		return name;
	}

	@Override
	public double getPrice() {
		double price;
		double sandprice = getSandwich().getPrice();
		double drinkprice = getDrink().getPrice();
		double saladprice = getSalad().getPrice();
		//drink
		if (sandprice+drinkprice < sandprice+saladprice){
			price = sandprice + saladprice;
		}
		//salad
		else if (sandprice+drinkprice > sandprice+saladprice){
			price = sandprice + drinkprice;
		}
		//drink
		else if (saladprice+drinkprice < sandprice+drinkprice){
			price = sandprice + drinkprice;
		}
		//sandwich
		else if (saladprice+drinkprice > sandprice+drinkprice){
			price = saladprice + drinkprice;
		}
		//sand and drink
		else if (saladprice == drinkprice && sandprice > drinkprice){
			price = sandprice + drinkprice;
		}
		//salad and drink
		else if (saladprice == sandprice && drinkprice > sandprice){
			price = saladprice + drinkprice;
		}
		else if(saladprice == sandprice && drinkprice == sandprice){
			price = 2 * saladprice;
		}
		else{
			price = 0.00;
		}
		
		return price;
	}

}
