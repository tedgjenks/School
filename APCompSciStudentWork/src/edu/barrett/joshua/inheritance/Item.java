
package edu.barrett.joshua.inheritance;




import edu.jenks.inheritance.dist.Buyable;

public abstract class Item implements Buyable {
	private double weight;
	private boolean bulk;
	public Item() {
		
	}
	public double getWeight() {
		return weight;
	}
	public boolean isBulk() { 
		return bulk;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setBulk(boolean bulk ) {
		this.bulk = bulk;
	}

}
