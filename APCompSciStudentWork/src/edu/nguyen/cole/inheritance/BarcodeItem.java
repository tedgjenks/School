package edu.nguyen.cole.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

public class BarcodeItem extends Item implements Barcoded {
	
	private double setprice;
	private double setweight;
	private boolean setbulk;
	public BarcodeItem(){
		
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
		// TODO Auto-generated method stub
		setbulk = arg0;
	}

	@Override
	public void setWeight(double arg0) {
		setweight=arg0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPrice(double arg0) {
		setprice=arg0;
		// TODO Auto-generated method stub
		
	}

}
