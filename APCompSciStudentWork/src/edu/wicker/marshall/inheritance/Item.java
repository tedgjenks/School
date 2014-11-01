package edu.wicker.marshall.inheritance;

import edu.jenks.inheritance.dist.Buyable;

public abstract class Item implements Buyable{
	private double weight;
	private boolean bulk = false;
	public Item() {

	}

	public abstract double getPrice();

	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight){
		if (weight < 0){
			System.out.println("Invalid Weight");
		}
		else{
			this.weight = weight;
		}
	}

	public void setBulk(boolean bulk){
		this.bulk = bulk;
	}

	public boolean isBulk(){
		return bulk;
	}
	
	

	//I decided against putting a comment after every line when I realized how error prone my programs are *shrug*
}
