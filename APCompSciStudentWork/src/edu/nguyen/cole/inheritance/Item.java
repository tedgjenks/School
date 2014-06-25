package edu.nguyen.cole.inheritance;

import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;

public class Item implements Buyable {
	private double setprice;
	private double setweight;
	private boolean setbulk;
	public Item(){
		
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return setprice;
	}

	@Override
	public double getTax(double arg0) {
		// TODO Auto-generated method stub
		return getPrice()*arg0;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return setweight;
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBulk() {
		// TODO Auto-generated method stub
		return setbulk;
	}

	@Override
	public void setBulk(boolean arg0) {
		setbulk=arg0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWeight(double arg0) {
		setweight=arg0;
		// TODO Auto-generated method stub
		
	}

}
