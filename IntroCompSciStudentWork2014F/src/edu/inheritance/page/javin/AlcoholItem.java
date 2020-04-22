package edu.inheritance.page.javin;
import edu.jenks.dist.inheritance.*;
public class AlcoholItem extends BarcodeItem implements SinTaxable{
	private double sin;
	private ItemHandler handler;
	public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate) {
		super(bulk, weight, price);
		sin = sinTaxRate;
	}
	
	@Override
	public double getTax(double arg0) {
		return (arg0 + getSinTaxRate()) * getPrice();
	}

	@Override
	public double getSinTaxRate() {
		// TODO Auto-generated method stub
		return sin;
	}

	@Override
	public void setSinTaxRate(double arg0) {
		sin = arg0;
	}
	public boolean initBuyable(ItemHandler arg0) {
		handler = arg0;
		handler.setCheckID(true);
		return true;
	}
}
