
package edu.butler.julie.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;

public class AlcoholItem extends BarcodeItem implements SinTaxable{
    private double sintaxrate;
    private double tax;
    public AlcoholItem(){
       
    }
   /* public AlcoholItem(double weight, boolean bulk, double price, double sintaxrate){
        super(weight, bulk, price);
        sintaxrate=sintaxrate;
       
    }*/
    public double getSinTaxRate(){
        return sintaxrate;
    }
    public void setSinTaxRate(double newsintaxrate){
        sintaxrate=newsintaxrate;
    }
    public double getTax(double baseTaxRate){
    	return getPrice()*(baseTaxRate+getSinTaxRate());
    }
    public boolean initBuyable(ItemHandler itemHandler){
    	return true;
    	
    }

}