package edu.nguyen.cole.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;

public class AlcoholItem extends BarcodeItem implements SinTaxable  {
	private double setprice;
	private double setweight;
	private double settaxrate;
	private boolean setbulk;
	public AlcoholItem(){
	
}
	@Override
	public void setPrice(double arg0) {
		setprice= arg0;
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return setprice;
	}

	@Override
	public double getTax(double arg0) {
		// TODO Auto-generated method stub
		
		return (arg0+getSinTaxRate())*getPrice() ;
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
	public void setBulk(boolean arg0){
		setbulk = arg0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWeight(double arg0) {
		setweight=arg0;
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSinTaxRate() {
		// TODO Auto-generated method stub
		return settaxrate;
	}

	@Override
	public void setSinTaxRate(double arg0) {
		settaxrate=arg0;
		// TODO Auto-generated method stub
		
	}

}
