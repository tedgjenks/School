package edu.cb.lunch.sweezy.kenneth;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sand, Salad sald, Drink d) {
		super(sand, sald, d);
	}

	public String getName() {
		return getName();
	}

	public double getPrice() {
		double low = Integer.MAX_VALUE;
		double s = 0;
		double[] t = new double[3];
		t[0] = getSalad().getPrice();
		t[1] = getSandwich().getPrice();
		t[2] = getDrink().getPrice();
		for (double d : t) {
			if (d <= low) {
				low = d;
			}
			s += d;
		}
		return s - low;
	}

	public static void main(String[] args) {
		Drink testDrink = new Drink("Drink1", 4.00);
		Salad testSalad = new Salad("Salad1", 13.00);
		Sandwich testSandwich = new Sandwich("Sandwich1", 7.00);
		Trio testTrio = new Trio(testSandwich, testSalad, testDrink);
		System.out.println(testTrio.getPrice());
	}

}
