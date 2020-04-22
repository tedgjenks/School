package edu.inheritance.page.javin;
import edu.jenks.dist.inheritance.*;
public abstract class Item extends java.lang.Object implements Buyable{
	private boolean bulk;
	private double weight;
	public Item(boolean bulk) {
		this.bulk = bulk;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public boolean isBulk() {
		// TODO Auto-generated method stub
		return bulk;
	}

	@Override
	public void setBulk(boolean arg0) {
		bulk = arg0;
		
	}

	@Override
	public void setWeight(double arg0) {
		weight = arg0;
	}

}
