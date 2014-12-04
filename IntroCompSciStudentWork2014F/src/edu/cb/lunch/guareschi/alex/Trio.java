package edu.cb.lunch.guareschi.alex;


import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);

		
	}

	@Override
	public String getName() {
		String trioName = "";
		String sandwich = getSandwich().toString();
		String salad = getSalad().toString();
		String drink = getDrink().toString();
		trioName= (sandwich + "/" + salad + "/" + drink + "Trio");
		return trioName;
	}

	@Override
	public double getPrice() {
		double price = 0.00;
		String trioName = getName();
		if (trioName.equals("Cheeseburger/Spinach Salad/Orange Soda Trio"));
			price += 4.00;
		if (trioName.equals("Cheeseburger/Spinach Salad/Cappuccino Trio"));
			price += 6.25;
		if (trioName.equals("Cheeseburger/Coleslaw/Orange Soda Trio"));
			price += 4.00;
		if (trioName.equals("Cheeseburger/Coleslaw/Cappuccino Trio"));
			price += 6.25;
		if (trioName.equals("Club Sandwich/Spinach Salad/Orange Soda Trio"));
			price += 4.00;		
		if (trioName.equals("Club Sandwich/Coleslaw/Orange Soda Trio"));
			price += 4.00;
		if (trioName.equals("Club Sandwich/Spinach Salad/Cappuccino Trio"));
			price += 6.25;
		if (trioName.equals("Club Sandwich/Coleslaw/Cappuccino Trio"));
			price += 6.25;
		return price;
	}

}
