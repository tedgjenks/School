package edu.inheritance.sweezy.kenneth;
import edu.jenks.dist.inheritance.*;

public class BarcodeItem extends Item implements Barcoded {
	private double storedPrice = 0.0;
	private double storedWeight = 0.0;
	private ItemHandler runner = new SelfCheckout(0.0);
	
	public static void main(String[] args) {
		Item testing = new WeighedItem(true, 6.891032994006223);
		System.out.println(testing.getPrice());
	}
	
	public BarcodeItem(boolean bulk, double weight, double price) {
		super(bulk);
		super.setWeight(weight);
		storedPrice = price;
	}

	public double getPrice() {
		return storedPrice * storedWeight;
	}

	public double getTax(double arg0) {
		return 0;
	}
	
	public void setPrice(double price) {
		storedPrice = price;
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
