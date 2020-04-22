package edu.inheritance.tran.don;

import edu.jenks.dist.*;
import edu.jenks.dist.inheritance.*;

public class AlcoholItem extends BarcodeItem implements SinTaxable{
	
	private double sinTaxRate;
	public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate) {
		super(bulk, weight, price);
		this.sinTaxRate = sinTaxRate;
	}
	public void setPrice(double arg0) {
		super.setPrice(arg0);
		
	}

	@Override
	public double getPrice() {
		return super.getPrice();
	}

	@Override
	public double getTax(double arg0) {
		return (getPrice()*arg0) + (getPrice()*sinTaxRate);
	}

	@Override
	public double getWeight() {
		return super.getWeight();
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		arg0.setCheckID(true);
		return false;
	}

	@Override
	public boolean isBulk() {
		return super.isBulk();
	}

	@Override
	public void setBulk(boolean arg0) {
		super.setBulk(arg0);
		
	}

	@Override
	public void setWeight(double arg0) {
		super.setWeight(arg0);
		
	}

	@Override
	public double getSinTaxRate() {
		return sinTaxRate;
	}

	@Override
	public void setSinTaxRate(double arg0) {
		sinTaxRate = arg0;
		
	}

}
