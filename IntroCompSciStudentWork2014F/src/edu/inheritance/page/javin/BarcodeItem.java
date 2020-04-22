package edu.inheritance.page.javin;
import edu.jenks.dist.inheritance.*;
public class BarcodeItem extends Item implements Barcoded{
	private double weight;
	private double price;
	private ItemHandler handler;
	public BarcodeItem(boolean bulk, double weight, double price) {
		super(bulk);
		setWeight(weight);
		setPrice(price);
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public double getTax(double baseTaxRate) {
		// TODO Auto-generated method stub
		return baseTaxRate * price;
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		handler = arg0;
		return true;
	}

	@Override
	public void setPrice(double arg0) {
		// TODO Auto-generated method stub
		price = arg0;
	}

}
