package edu.inheritance.creswell.jasmine;
import edu.jenks.dist.inheritance.*;


public abstract class Item extends java.lang.Object implements Buyable
{
    private double itemWeight=0.0;
    private boolean itemBulk;
    public Item(boolean bulk) {
        itemBulk=bulk;
    }
    public double getWeight() {
        return itemWeight;
    }
    public void setWeight(double weight) {
        itemWeight=weight;
    }
    public boolean isBulk() {
        return itemBulk;
    }
    public void setBulk(boolean bulk) {
        itemBulk=bulk;
    }
    public boolean initBuyable(ItemHandler itemHandler) {
        return false;
    }
    
}
