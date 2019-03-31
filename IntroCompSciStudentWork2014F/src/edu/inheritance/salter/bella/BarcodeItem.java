package edu.inheritance.salter.bella;
import edu.jenks.dist.inheritance.*;
public class BarcodeItem extends Item implements Barcoded
{
    private double price;
    public BarcodeItem(boolean bulk, double weight, double price) {
        super(bulk);
        this.price = price;
        setWeight(weight);
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public double getTax(double baseTaxRate) {
        return price * baseTaxRate;
    }
    public boolean initBuyable(ItemHandler itemHandler) {
        return false;
    }
}
