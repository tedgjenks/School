package edu.inheritance.sweezy.kenneth;
import edu.jenks.dist.inheritance.*;

public abstract class Item implements Buyable {
	private double storedWeight = 0.0;
	private boolean storedBulk = false;
	private double storedPrice = 0.0;
	private double storedPricePerPound = 0.0;
	
	public static void main(String[] args) {
		/* 
		Item testItem = new BarcodeItem(true, 12.91, 6.9);
		ItemHandler test = new SelfCheckout(0.1);
		Barcoded test2 = new BarcodeItem(false, 12.911, 6.9);
		System.out.println(testItem.getPrice());
		((BarcodeItem) testItem).setPrice(45.00);
		System.out.println(testItem.getPrice());
		System.out.println(testItem.isBulk());
		testItem.setBulk(!testItem.isBulk());
		System.out.println(testItem.isBulk());
		*/
	}
	
	public Item(boolean bulk) {
		setBulk(bulk);
	}

	public double getWeight() {
		return storedWeight;
	}

	public boolean isBulk() {
		return storedBulk;
	}

	public void setBulk(boolean bulk) {
		storedBulk = bulk;
	}

	public void setWeight(double weight) {
		storedWeight = weight;
	}
	
	public double getPrice() {
		return storedPrice;
	}
	
	public void setPricePerPound(double pPR) {
		storedPricePerPound = pPR;
	}
	
	public double getPricePerPound() {
		return storedPricePerPound;
	}
	
}
