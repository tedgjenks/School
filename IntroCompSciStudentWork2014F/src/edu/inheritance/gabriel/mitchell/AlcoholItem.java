package edu.inheritance.gabriel.mitchell;
import edu.jenks.dist.inheritance.*;
public class AlcoholItem extends BarcodeItem implements SinTaxable
{
    private double sinTaxRate;
    private boolean bulk;
    private double price;
    public AlcoholItem(boolean bulk, double weight, double price, double sinTaxR){
        super(bulk,weight,price);
        sinTaxRate = sinTaxR;
    }
    public double getTax(double baseTaxRate){
        return price * (baseTaxRate + sinTaxRate) ;
    }
    public boolean initBuyable(ItemHandler itemHandler){
        itemHandler.setCheckID(true);
        return false;
    }
    public double getSinTaxRate(){
        return sinTaxRate;
    }
    public void setSinTaxRate(double sinTaxR){
        sinTaxRate = sinTaxR;
    }
}
