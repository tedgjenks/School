package edu.cb.lunch.tarasidis.john;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio{

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String san = getSandwich().getName();
		String sal = getSalad().getName();
		String dri = getDrink().getName();
		String returnStr =  san + "/" + sal + "/" + dri + " Trio";
		return returnStr;
	}

	@Override
	public double getPrice() {
		List<Double> priceList = new ArrayList<Double>();
		priceList.addAll(Arrays.asList(getSandwich().getPrice(), getSalad().getPrice(), getDrink().getPrice()));
		Collections.sort(priceList);
		return (priceList.get(1) + priceList.get(2));
	}

}
