package edu.rogers.payton.inheritance;

import edu.jenks.inheritance.dist.*;

public class SelfCheckout implements ItemHandler{
	public double taxRate = 0;
	public double totalWeight = 0;
	public double normalTaxTotal = 0;
	public double sinTaxTotal = 0;
	public double subtotal = 0;
	public double scaleWeight = 0;
	public boolean IDCheck = false;
	
	
	public SelfCheckout(double taxRate) {
		this.taxRate = taxRate;
	}
	
	
	@Override
	public boolean addItem(Buyable item) {
		
		double price = item.getPrice();
		double weight = item.getWeight();
		boolean bulk = item.isBulk();
		double scaleWeight = this.getScaleWeight();
		
		this.scaleWeight = 0;

		
		if (item instanceof AlcoholItem) {
			this.IDCheck = true;
			subtotal += price;
			sinTaxTotal += (price * ((AlcoholItem) item).getSinTaxRate());
			normalTaxTotal += (price * taxRate);
			if (!bulk) totalWeight += weight;
			
		} else if (item instanceof BarcodeItem) {
			subtotal += price;
			normalTaxTotal += (price * taxRate);
			if (!bulk) totalWeight += weight;
			
		} else if (item instanceof WeighedItem) {
			double PPP = ((WeighedItem) item).getPricePerPound();
			subtotal += PPP * scaleWeight;
			normalTaxTotal += (PPP * scaleWeight * taxRate);
			if (!bulk) totalWeight += scaleWeight;
			
		} else if (item instanceof Item) {
			subtotal += price;
			normalTaxTotal += (price * taxRate);
			if (!bulk) totalWeight += weight;
		}
		return true;
	}

	@Override
	public boolean addItem(Buyable item, double scaleWeight) {
		
		boolean bulk = item.isBulk();
		double price = item.getPrice();
		
		this.scaleWeight = scaleWeight;
		
		
		if (item instanceof AlcoholItem) {
			this.IDCheck = true;
			subtotal += price;
			if (!bulk) totalWeight += scaleWeight;
			sinTaxTotal += (price * ((AlcoholItem) item).getSinTaxRate());
			normalTaxTotal += (price * taxRate);
			
		} else if (item instanceof BarcodeItem) {
			subtotal += price;
			if (!bulk) totalWeight += scaleWeight;
			normalTaxTotal += (price * taxRate);
			
		} else if (item instanceof WeighedItem) {
			double PPP = ((WeighedItem) item).getPricePerPound();
			double subprice = PPP * scaleWeight;
			subtotal += subprice;
			if (!bulk) totalWeight += scaleWeight;
			normalTaxTotal += (subprice * taxRate);
			
		} else if (item instanceof Item) {
			subtotal += price;
			if (!bulk) totalWeight += scaleWeight;
			normalTaxTotal += (price * taxRate);
			
		}
		return true;
	}

	@Override
	public double getCheckoutTotal() {
		return subtotal + sinTaxTotal + normalTaxTotal;
	}

	@Override
	public double getExpectedWeight() {
		return totalWeight;
	}


	@Override
	public boolean isCheckID() {
		return this.IDCheck;
	}

	@Override
	public void setCheckID(boolean IDCheck) {
		this.IDCheck = IDCheck;
		
	}

	@Override
	public double getScaleWeight() {
		return this.scaleWeight;
	}


}
