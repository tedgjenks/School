package edu.inheritance.sweezy.kenneth;
import edu.jenks.dist.inheritance.*;

public class AlcoholItem extends BarcodeItem implements SinTaxable {
	private double storedPrice = 0.0;
	private double storedWeight = 0.0;

	public static void main(String[] args) {

	}
	
	public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate) {
		super(bulk, weight, price);
		setSinTaxRate(sinTaxRate);
	}
	
	public double getSinTaxRate() {
		return 0;
	}

	public void setSinTaxRate(double arg0) {
	
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
