package edu.inheritance.gabriel.mitchell;
import edu.jenks.dist.inheritance.*;
public class SelfCheckout implements ItemHandler
{
    private double expectedWeight;
    private double subTotal;
    private double taxTot;
    private boolean checkID;
    private double scale;
    public final double TAX_RATE;
    public SelfCheckout(double taxR){
        TAX_RATE = taxR;
    }
    public double getScaleWeight(){
        return scale;
    }
    public boolean isCheckID(){
        return checkID;
    }
    public void setCheckID(boolean checkID){
        this.checkID = checkID;
    }
    public double getExpectedWeight(){
        return expectedWeight;
    }
    public boolean addItem(Buyable item){
        return this.addItem(item, 0.0);
    }
    public boolean addItem(Buyable item, double scaleWeight){
        scale = scaleWeight;
        item.initBuyable(this);
        subTotal += item.getPrice();
        taxTot += item.getTax(TAX_RATE);
        if (!item.isBulk()){
            expectedWeight += item.getWeight();
        }
        return false;
    }
    public double getCheckoutTotal(){
        return getSubtotal() + getTax();
    }
    public double getSubtotal(){
        return subTotal;
    }
    public double getTax(){
        return taxTot;
    }
}
