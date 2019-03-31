package edu.inheritance.creswell.jasmine;
import edu.jenks.dist.inheritance.*;

public class AlcoholItem extends BarcodeItem implements SinTaxable
{
   public double sinTax= 0.0;
   public AlcoholItem(boolean bulk,double weight,double price,double sinTaxRate) {
       super(bulk,weight,price);
       sinTax= sinTaxRate;
    }
   public double getTax(double baseTaxRate) {
       return getPrice()*(sinTax+baseTaxRate);
    }
   public boolean initBuyable(ItemHandler itemHandler) {
       itemHandler.setCheckID(true);
       return true;
    }
   public double getSinTaxRate() {
       return sinTax;
    }
   public void setSinTaxRate(double sinTaxRate) {
       sinTax=sinTaxRate;
    }
}
