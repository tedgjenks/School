package edu.inheritance.salter.bella;
import edu.jenks.dist.inheritance.*;
public class WeighedItem extends Item implements Weighable {
    private double pricePerPound;
    private boolean isBulk;
    public WeighedItem(boolean bulk, double pricePerPound) {
        super(bulk);
        this.pricePerPound = pricePerPound;
    }
    public double getPricePerPound() {
        return pricePerPound;
    }
    public void setPricePerPound(double price) {
        pricePerPound = price;
    }
    public double getPrice() {
        return pricePerPound * getWeight();
    }
    public double getTax(double baseTaxRate) {
        return getPrice() * baseTaxRate;
    }
    public boolean initBuyable(ItemHandler itemHandler) {
        setWeight(itemHandler.getScaleWeight());
        return false;
    }
}
