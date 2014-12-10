package edu.cb.lunch.gottlieb.matthew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich,Salad salad,Drink drink){
		super (sandwich, salad, drink);
	}

	@Override
	public String getName() {
		String sandwich = getSandwich().getName();
		String salad = getSalad().getName();
		String drink = getDrink().getName();
		String trioName= "";
		trioName = (sandwich+"/"+salad+"/"+drink+" Trio");
		return trioName;
	}

	@Override
	public double getPrice() {
		double sandwich = getSandwich().getPrice();
		double salad = getSalad().getPrice();
		double drink = getDrink().getPrice();
		if (sandwich > salad && salad > drink){
			return (sandwich+drink);
		}
		if (salad > drink && drink> sandwich){
			return (salad+drink);
		}
		if (drink > salad && salad> sandwich){
			return (drink + salad);
		}
		if (sandwich > drink && drink > salad){
			return (sandwich+drink);
		}
		if (salad > sandwich && sandwich > drink){
			return (salad+sandwich);
		}
		if (drink > sandwich && sandwich > salad){
			return (drink+sandwich);
		}
		if (sandwich > drink && drink==salad){
			return (sandwich+drink);
		}
		return 0;
	}
}