package edu.cb.lunch.page.javin;
import edu.jenks.dist.cb.lunch.*;
public class Trio extends AbstractTrio{

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub
	}
	public double getPrice() {
		double price = 0;
		if(getSandwich().getPrice() > getSalad().getPrice() || getSandwich().getPrice() > getDrink().getPrice()) price += getSandwich().getPrice();
		if(getSalad().getPrice() > getSandwich().getPrice() || getSalad().getPrice() > getDrink().getPrice()) price += getSalad().getPrice();
		if(getDrink().getPrice() > getSandwich().getPrice() || getDrink().getPrice() > getSalad().getPrice()) price += getDrink().getPrice();
		return price;
	}
	public String getName() {
		return getSandwich().getName() + "/" + getSalad().getName() + "/" + getDrink().getName() + " Trio";
	}
}
