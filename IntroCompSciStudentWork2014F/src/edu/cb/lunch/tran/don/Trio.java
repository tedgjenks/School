package edu.cb.lunch.tran.don;
import edu.jenks.dist.cb.lunch.*;



public class Trio extends AbstractTrio implements MenuItem{
	public static void main(String[] args) {
		Trio a = new Trio(new Sandwich("Chipotle", 3.0), new Salad("Caesar", 5.0), new Drink("Orange", 3.0));
		System.out.println(a.getName());
		System.out.println(a.getPrice());
	}
	
	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
	}

	@Override
	public String getName() {
		
		return getSandwich().getName() + "/" + getSalad().getName() + "/" + getDrink().getName() + " Trio";
	}

	@Override
	public double getPrice() {
		if(getSandwich().getPrice() < getSalad().getPrice() && getSandwich().getPrice() < getDrink().getPrice()) {
			return getSalad().getPrice() + getDrink().getPrice();
		}
		if(getSalad().getPrice() < getSandwich().getPrice() && getSalad().getPrice() < getDrink().getPrice()) {
			return getSandwich().getPrice() + getDrink().getPrice();
		}
		if(getDrink().getPrice() < getSalad().getPrice() && getDrink().getPrice() < getSandwich().getPrice()) {
			return getSalad().getPrice() + getSandwich().getPrice();
		}
		if(getSandwich().getPrice() == getSalad().getPrice()) {
			return getSandwich().getPrice() + getDrink().getPrice();
		}
		if(getSalad().getPrice() == getDrink().getPrice()) {
			return getSandwich().getPrice() + getDrink().getPrice();
		}
		if(getSandwich().getPrice() == getDrink().getPrice()) {
			return getSalad().getPrice() + getDrink().getPrice();
		}
		return getSandwich().getPrice() + getSalad().getPrice();
	}

}
