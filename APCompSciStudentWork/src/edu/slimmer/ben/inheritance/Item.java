package edu.slimmer.ben.inheritance;

import edu.jenks.inheritance.dist.Buyable;

public abstract class Item
extends java.lang.Object
implements Buyable {
	private double Weight=0;
	private boolean Bulk=false;
	
	public Item() {
	}
	
	public double getWeight(){
		return Weight;
	}
	
	public void setWeight(double weight){
		Weight=weight;
	}
	
	public boolean isBulk(){
		return Bulk;
	}
	
	public void setBulk(boolean bulk) {
		Bulk=bulk;
	}

}
