package edu.inheritance.salter.bella;
import edu.jenks.dist.inheritance.*;
public class SelfCheckout implements ItemHandler
{
    private double TAX_RATE;
    private double totalWeight = 0;
    private double sclWeight = 0;
    private double subTotal = 0;
    private double tax;
    private boolean IdCheck = false;
    public SelfCheckout(double taxRate) {
        TAX_RATE = taxRate;
    }
    public double getScaleWeight() {
        return sclWeight;
    }
    public boolean isCheckID() {
        return IdCheck;
    }
    public void setCheckID(boolean checkID) {
        IdCheck = checkID;
    }
    public double getExpectedWeight() {
        return totalWeight;
    }
    public boolean addItem(Buyable item) {
        addItem(item, item.getWeight());
        return true;
    }
    public boolean addItem(Buyable item, double scaleWeight) {
        sclWeight = scaleWeight;
        item.initBuyable(this);
        if(!item.isBulk()) {
            totalWeight += item.getWeight();
        }
        subTotal += item.getPrice();
        tax += item.getTax(TAX_RATE);
        return true;
    }
    public double getCheckoutTotal() {
        return getSubtotal() + getTax();
    }
    public double getSubtotal() {
        return subTotal;
    }
    public double getTax() {
        return tax;
    }
}
