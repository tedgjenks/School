package edu.cb.lunch.mariscal.juan;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio {

	public Trio(Sandwich sandwich, Salad salad, Drink drink) {
		super(sandwich, salad, drink);
		// TODO Auto-generated constructor stub

	}
	public String getName(){


		return  getSandwich().getName() + "/" +  getSalad().getName() + "/" + getDrink().getName() + " Trio";
	}
	public double getPrice(){
		Sandwich sa = getSandwich();
		Salad sal = getSalad();
		Drink dr = getDrink();
		if(sa.getPrice() < sal.getPrice() && sa.getPrice() < dr.getPrice()){
			sa.setPrice(0);
		}
		else if(sal.getPrice() < sa.getPrice() && sal.getPrice() < dr.getPrice()){
		
				sal.setPrice(0);
			
		}
		else{dr.setPrice(0);}

		return sa.getPrice() + sal.getPrice() + dr.getPrice();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
