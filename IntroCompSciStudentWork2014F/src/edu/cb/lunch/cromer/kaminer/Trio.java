package edu.cb.lunch.cromer.kaminer;

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
			String sandwich = getSandwich().getName();
			String salad = getSalad().getName();
			String drink = getDrink().getName();
			StringBuilder name = new StringBuilder();
			name.append(sandwich + "/").append(salad + "/").append(drink + " Trio");
			return name.toString();
		}
		@Override
		public double getPrice() {
			double price = 0.00d;
			Sandwich sandwich = getSandwich();
			double sandwichPrice = sandwich.getPrice();
			Salad salad = getSalad();
			double saladPrice = salad.getPrice();
			Drink drink = getDrink();
			double drinkPrice = drink.getPrice();
			double price1 = sandwichPrice + saladPrice;
			double price2 = sandwichPrice + drinkPrice;
			double price3 = saladPrice + drinkPrice;
			if (price1 >= price2 && price1 >= price3){
				price = price1;
			}
			if (price2 >= price1 && price2 >= price3){
				price = price2;
			}
			if (price3 >= price2 && price3 >= price1){
				price = price3;
			}
			return price;
		}
	}