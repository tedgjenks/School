package edu.inheritance.creswell.jasmine;
import edu.jenks.dist.inheritance.*; 

public class SelfCheckout extends java.lang.Object implements ItemHandler
{
    private boolean ID=false;
    private double sWeight=0.0;
    private double totalWeight=0.0;
    private double total=0.0;
    private double tax=0.0;
    public final double TAX_RATE;
    
    public SelfCheckout(double taxRate) {
        TAX_RATE = taxRate;
    }
    public double getScaleWeight() {
        return sWeight;
    }
    public boolean isCheckID() {
        return ID; 
    }
    public void setCheckID(boolean checkID) {
        ID=checkID;
    }
    public double getExpectedWeight() {
        return totalWeight;
    }
    public boolean addItem(Buyable item) {
        
        return addItem(item, 0.0); 
    }
    public boolean addItem(Buyable item,double scaleWeight) {
       sWeight=scaleWeight;
       item.initBuyable(this);
       total+=item.getPrice();
       tax+= item.getTax(TAX_RATE);
       if (!item.isBulk()) {
           totalWeight+=item.getWeight();
       }
       return true;
    }
    public double getCheckoutTotal() {
        return total+tax;
    }
    public double getSubtotal() {
        return total;
    }
    public double getTax() {
        return tax;
    }
}
