package edu.inheritance.sweezy.kenneth;
import edu.jenks.dist.inheritance.*;

public class WeighedItem extends Item implements Weighable {
	private double storedPrice = 0.0;
	private double storedWeight = 0.0;
	
	public WeighedItem(boolean bulk, double pricePerPound) {
		super(bulk);
		setPricePerPound(pricePerPound);
		setBulk(bulk);
		storedPrice = pricePerPound;
	}

	public static void main(String[] args) {
		
	}

	public double getPricePerPound() {
		return storedPrice;
	}

	public void setPricePerPound(double price) {
		storedPrice = price;
	}

	public double getPrice() {
		return storedPrice * getWeight();
	}

	public double getTax(double arg0) {
		return 0.0;
	}
	
	public boolean initBuyable(ItemHandler itemH) {
		if(itemH != null) {
			storedPrice += getPrice();
			storedWeight += getWeight();
			return true;
		} else {
			return false;
		}
	}

}
