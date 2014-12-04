package edu.cb.lunch.tarasidis.john;
import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio{

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		/*
		String san = sandwich.getName();
		String sal = salad.getName();
		String dri = drink.getName();
		return san + "/" + sal + "/" + dri;
		*/
		return null;
	}

	@Override
	public double getPrice() {
		
		return 0;
	}

}
