package edu.inheritance.gabriel.mitchell;
import edu.jenks.dist.inheritance.*;

public class BarcodeItem extends Item implements Barcoded
{
    public BarcodeItem(boolean bulk, double weight, double price){
        super(bulk);
        setPrice(price);
        setWeight(weight);
    }
    public double getTax(double baseTaxRate){
        return getPrice() * baseTaxRate;
    }
    public boolean initBuyable(ItemHandler itemHandler){
        return false;
    }
}
