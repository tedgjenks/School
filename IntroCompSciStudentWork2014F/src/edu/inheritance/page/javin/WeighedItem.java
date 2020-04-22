package edu.inheritance.page.javin;
import edu.jenks.dist.inheritance.*;
public class WeighedItem extends Item implements Weighable {
	private double pricePerPound;
	private ItemHandler handler;
	public WeighedItem(boolean bulk, double pricePerPound) {
		super(bulk);
		this.pricePerPound = pricePerPound;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getPricePerPound() {
		// TODO Auto-generated method stub
		return pricePerPound;
	}
	@Override
	public void setPricePerPound(double arg0) {
		// TODO Auto-generated method stub
		pricePerPound = arg0;
	}

	@Override
	public double getPrice() {
		return getWeight() * pricePerPound;
	}

	@Override
	public double getTax(double baseRate) {
		// TODO Auto-generated method stub
		return getPrice() * baseRate;
	}
	
	public boolean initBuyable(ItemHandler itemH) {
		handler = itemH;
		return true;
	}
}
