package edu.gallman.allison.inheritance;

import edu.jenks.inheritance.dist.Buyable;

import edu.jenks.inheritance.dist.ItemHandler;

public class SelfCheckout
extends java.lang.Object
implements ItemHandler{
	private boolean checkID;
	//private Object item;
	private double scaleWeight;
	private double totalCheckout;
	private double totalWeight;
	private double TAX_RATE;
	public SelfCheckout(double TAX_RATE){
		this.TAX_RATE=TAX_RATE;
	}
	
	public SelfCheckout(boolean checkID, double scaleWeight){
		this.checkID=checkID;
		this.scaleWeight=scaleWeight;
	}
	
	
	public double getScaleWeight(){
		return scaleWeight;
	}
	
	
	public boolean isCheckID(){
		if(checkID == true){
		return true;
		}else
			return false;
		
	}
	
	
	public void setCheckID(boolean checkID){
		this.checkID=checkID;
	}
	
	
	public double getExpectedWeight(){
		return totalWeight;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public double getCheckoutTotal(){
		return totalCheckout;
	}
	
	public void setScaleWeight(double scaleWeight){
		this.scaleWeight=scaleWeight;
	}


	
	public boolean addItem(Buyable item) {
		/*if(item instanceof WeighedItem){
			return false;
		}else if(item instanceof BarcodeItem){
			if(item.isBulk()==false){
				totalWeight+=item.getWeight();
			}
		BarcodeItem a = (BarcodeItem) item;
		totalCheckout+=a.getPrice()+a.getTax(TAX_RATE);
		}else if(item instanceof AlcoholItem){
			AlcoholItem b = (AlcoholItem) item;
			setCheckID(true);
			if(b.isBulk()==false){
				totalWeight+=b.getWeight();
			}
			totalCheckout+=b.getPrice()+b.getTax(TAX_RATE);
		}
		return true;*/
		return addItem(item, 0);
	}


	
	public boolean addItem(Buyable item, double scaleWeight) {
		if(scaleWeight <= 0 && item instanceof WeighedItem){
			return false;
		}
		if(item instanceof WeighedItem){
			WeighedItem c =(WeighedItem) item;
			setScaleWeight(scaleWeight);
			c.setScaleWeight(scaleWeight);
			if(c.isBulk()==false){
				totalWeight+=c.getScaleWeight();
			}
			totalCheckout+=c.getPrice()+c.getTax(TAX_RATE);
		}else if(item instanceof BarcodeItem){
			if(item.isBulk()==false){
				totalWeight+=item.getWeight();
			}
			BarcodeItem a = (BarcodeItem) item;
			totalCheckout+=a.getPrice()+a.getTax(TAX_RATE);
		}else if(item instanceof AlcoholItem){
			AlcoholItem b = (AlcoholItem) item;
			setCheckID(true);
			if(b.isBulk()==false){
				totalWeight+=b.getWeight();
			}
			totalCheckout+=b.getPrice()+b.getTax(TAX_RATE);
		}
		return true;
		
	}//Why does it not know that Item is a class that I am calling on?


}//end class.
