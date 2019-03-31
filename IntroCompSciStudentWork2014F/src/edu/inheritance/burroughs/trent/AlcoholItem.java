package edu.inheritance.burroughs.trent;
import edu.jenks.dist.inheritance.*;

public class AlcoholItem extends BarcodeItem implements SinTaxable{
    
    private double sinTaxRate;
    
    public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate){
        super(bulk, weight, price);
        this.sinTaxRate = sinTaxRate;
    }
    
    public double getTax(double baseTaxRate){
        return (baseTaxRate * getPrice()) + (getSinTaxRate() * getPrice());
    }
    
    public boolean initBuyable(ItemHandler itemHandler){
        return true;
    }
    
    public double getSinTaxRate(){
        return sinTaxRate;
    }
    
    public void setSinTaxRate(double sinTaxRate){
        this.sinTaxRate = sinTaxRate;
    }
}