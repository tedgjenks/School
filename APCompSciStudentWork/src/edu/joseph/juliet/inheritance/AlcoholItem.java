package edu.joseph.juliet.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;

public class AlcoholItem extends BarcodeItem implements SinTaxable {

	private double sinTax;
	public AlcoholItem(){
		//sinTax =0.0;
	}

	public void setSinTaxRate(double sinTaxRate){
		sinTax = sinTaxRate;
	}
	public double getSinTaxRate(){
		return sinTax;
	}
	public double getTax(double baseTaxRate){
        return getPrice()*baseTaxRate;
    }
	public boolean initBuyable(ItemHandler itemHandler){
        return true;
        
    }
	
}
