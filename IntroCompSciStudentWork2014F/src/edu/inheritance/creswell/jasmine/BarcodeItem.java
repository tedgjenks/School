package edu.inheritance.creswell.jasmine;
import edu.jenks.dist.inheritance.*;

public class BarcodeItem extends Item implements Barcoded
{
    
    private double itemPrice;
    private boolean itemBulk;
    
    public BarcodeItem(boolean bulk,double weight,double price) {
        super(bulk);
        setWeight(weight);
        itemPrice=price;
        setBulk(bulk);
    }
    public void setPrice(double price) {
        itemPrice=price;
    }
    public double getPrice() {
        return itemPrice;
    }
    public double getTax(double baseTaxRate) {
        return itemPrice*baseTaxRate;
    }
    public boolean initBuyable(ItemHandler itemHandler) {
        return true;
    }
}
