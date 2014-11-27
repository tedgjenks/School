/**
 * 
 */
package edu.cb.lunch.jenks.ted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

/**
 * @author Ted
 *
 */
public class Trio extends AbstractTrio {
	
	private static final String NAME_SEPARATOR = "/";

	/**
	 * @param sandwich
	 * @param salad
	 * @param drink
	 */
	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.lunch.MenuItem#getName()
	 */
	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder(50);
		sb.append(getSandwich().getName()).append(NAME_SEPARATOR);
		sb.append(getSalad().getName()).append(NAME_SEPARATOR);
		sb.append(getDrink().getName()).append(" ");
		sb.append("Trio");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.lunch.MenuItem#getPrice()
	 */
	@Override
	public double getPrice() {
		List<Double> prices = new ArrayList<Double>(3);
		prices.add(getSandwich().getPrice());
		prices.add(getSalad().getPrice());
		prices.add(getDrink().getPrice());
		Collections.sort(prices);
		return prices.get(2) + prices.get(1);
	}

}
