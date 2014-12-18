package edu.cb.lunch.smith.rod;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio implements MenuItem {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String sandname = getSandwich().getName();
		String saladname = getSalad().getName();
		String drinkname = getDrink().getName();
		String word = (sandname + "/"+ saladname + "/" + drinkname + " Trio");
		return word;
		
		
	}

	@Override
	public double getPrice() {
		double sandprice = getSandwich().getPrice();
		double saladprice = getSalad().getPrice();
		double drinkprice = getDrink().getPrice();		
		double price = 0;
		if (sandprice >= saladprice && saladprice >= drinkprice) {
			price = sandprice + saladprice;
		}
		if (saladprice >= drinkprice && drinkprice >= sandprice) {
			price = drinkprice + saladprice;
		}
		if (drinkprice >= sandprice && sandprice >= saladprice) {
			price = drinkprice + sandprice;
		}
		return price  ;
		
		
		
	}

}
