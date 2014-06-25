package edu.shearer.courtney.inheritance;

import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;

public class SelfCheckout
extends java.lang.Object
implements ItemHandler {
	private double subtotal;
	private boolean checkID;
	private double totalTax;
	private double expectedWeight;
	private double scaleWeight;
	public final double TAX_RATE;
	
	public SelfCheckout(double taxRate){
		TAX_RATE = taxRate;
	}
	public double getScaleWeight(){
			return scaleWeight;
			
	}
	public void setScaleWeight(double scaleWeight){
		this.scaleWeight = scaleWeight;
		
	}
	public boolean isCheckID(){
		return checkID;
		
	}
	public void setCheckID(boolean checkID){
		this.checkID = checkID;
		
	}
	public double getExpectedWeight(){
		
		return expectedWeight;
		
	}

	public boolean addItem(Buyable item){
		return addItem(item, 0);
	}

	public boolean addItem(Buyable item,
            double scaleWeight){
		this.scaleWeight = scaleWeight;
		double taxRate = TAX_RATE;
		if( item instanceof WeighedItem){
			WeighedItem wi = (WeighedItem)item;
			wi.setScaleWeight(scaleWeight);
			if(wi.getScaleWeight() == 0.0){
				 throw new IllegalArgumentException();
				}
			
			if(!wi.isBulk()){
				expectedWeight += wi.getScaleWeight();
			}
			
			totalTax = wi.getPrice() * TAX_RATE;
			subtotal += wi.getPrice() + totalTax;
		}
		else{//must be BarcodeItem
			
			if(!item.isBulk()){
				expectedWeight += item.getWeight();
			}
			if(item instanceof AlcoholItem){
				checkID = true;
				taxRate =TAX_RATE + ((AlcoholItem)item).getSinTaxRate();
			}
			
			
			totalTax = item.getPrice() * taxRate;
			subtotal += item.getPrice() + totalTax;
			
		}
		
		
		return true;
		
		
		
		
		
	}
	public double getCheckoutTotal(){
		return subtotal;
		
	}



}
