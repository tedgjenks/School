package edu.gallman.allison.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;



public class AlcoholItem 
extends BarcodeItem
implements SinTaxable{
	private double baseTaxRate;
	private Object itemHandler;
	private double sinTaxRate;
	public AlcoholItem(){
		
	}
	
	public AlcoholItem(double baseTaxRate, double sinTaxRate, Object itemHandler){
		this.baseTaxRate=baseTaxRate;
		this.sinTaxRate=sinTaxRate;
		this.itemHandler=itemHandler;
	}
	
	public double getTax(double baseTaxRate){
		return getPrice()*(baseTaxRate+getSinTaxRate());
	}

	
	
	public boolean initBuyable(ItemHandler itemHandler){
		return false;
	}
	
	
	
	public double getSinTaxRate(){
		return sinTaxRate;
	}
	
	
	
	public void setSinTaxRate(double sinTaxRate){
		this.sinTaxRate=sinTaxRate;
	}
}//end class.

	