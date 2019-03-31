package edu.inheritance.creswell.jasmine;
import edu.jenks.dist.inheritance.*;


public class WeighedItem extends Item implements Weighable
{
    private double itemPricePerPound;
    private boolean itemBulk;
    public WeighedItem(boolean bulk,double pricePerPound) {
        super(bulk);
        itemPricePerPound=pricePerPound;
        itemBulk=bulk;
    }
    public double getPricePerPound() {
        return itemPricePerPound;
    }
    public void setPricePerPound(double pricePerPound) {
        itemPricePerPound=pricePerPound;
    }
    public double getPrice() {
        return getWeight()*itemPricePerPound;
    }
    public double getTax(double baseTaxRate) {
        return getPrice()*baseTaxRate;
    }
    public boolean initBuyable(ItemHandler itemHandler) {
        setWeight(itemHandler.getScaleWeight());
        return true;
    }
}
