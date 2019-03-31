package edu.inheritance.newbold.griffin;
import edu.jenks.dist.inheritance.*;

public class SelfCheckout implements ItemHandler{
	private final double TAX_RATE;
	public double subTotal;
	public double expectedWeight;
	public boolean personCheckID;
	public double totalTax;
	public double totalScaleWeight;
	
	public SelfCheckout(double taxRate){
		TAX_RATE = taxRate;
	}
	
	public boolean addItem(Buyable item){
		addItem(item, 0.00);
		return true;
	}
	
	public boolean addItem(Buyable item, double scaleWeight){
		totalScaleWeight = scaleWeight;
		item.initBuyable(this);
		if(scaleWeight > 0.00){
			double weighedPrice = item.getPrice()*scaleWeight;
			subTotal += weighedPrice;
			totalTax += weighedPrice * TAX_RATE;
		}else{
			subTotal += item.getPrice();
			totalTax += item.getTax(TAX_RATE);
		}
		if(item.initBuyable(this)){
			setCheckID(true);
		}
		if(!item.isBulk() && scaleWeight == 0.00){
			expectedWeight += item.getWeight();
		}else if(!item.isBulk() && scaleWeight > 0.00){
			expectedWeight += scaleWeight;
		}
		return true;
	}
	
	public double getCheckoutTotal(){
		return subTotal + totalTax;
	}
	
	public double getExpectedWeight(){
		return expectedWeight;
	}
	
	public double getScaleWeight(){
		return totalScaleWeight;
	}
	
	public double getSubtotal(){
		return subTotal;
	}
	
	public double getTax(){
		return totalTax;
	}
	
	public boolean isCheckID(){
		return personCheckID;
	}
	public void setCheckID(boolean checkID){
		personCheckID = checkID;
	}
}