package edu.manalich.patrick.inheritance;

import  edu.jenks.inheritance.dist.*; 

public class AlcoholItem extends BarcodeItem implements SinTaxable {
	
	private double numSinTaxRate;
	
	public AlcoholItem() {
		
	}
	
	@Override
	public double getSinTaxRate(){
		return numSinTaxRate;
	}
	
	@Override
	public void setSinTaxRate(double sinTaxRate){
		numSinTaxRate = sinTaxRate;
	}
	
	@Override
	public double getTax(double baseTaxRate){
		return super.getTax(baseTaxRate) + (numSinTaxRate * getPrice());
	}
	
	@Override
	public boolean initBuyable(ItemHandler arg0) {
		return true;
	}
	
}
