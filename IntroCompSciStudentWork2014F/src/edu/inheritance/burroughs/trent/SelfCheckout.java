package edu.inheritance.burroughs.trent;
import edu.jenks.dist.inheritance.*;

public class SelfCheckout extends Object implements ItemHandler{
    
    public final double TAX_RATE;
    private double scaleWeight;
    private boolean checkID = false;
    private double expectedWeight;
    private double subTotal;
    private double tax =0.0;
    
    public static void main(String[] args){
        SelfCheckout sc = new SelfCheckout(0.07);
        Item item1 = new BarcodeItem(false, 3.2, 7.5);
        Item item2 = new WeighedItem(false, 2.2);
        //sc.addItem(item1);
        sc.addItem(item2, 456);
        System.out.println(sc.getSubtotal());
    }
    
    public SelfCheckout(double taxRate){
        super();
        TAX_RATE = taxRate;
    }
    
    public boolean addItem(Buyable item){
        addItem(item, 0.0);
        return true;
    }
    
    public boolean addItem(Buyable item,double scaleWeight){
        this.scaleWeight = scaleWeight;
        item.initBuyable(this);
        if(item.initBuyable(this)){
            checkID = true;
        }
        if(!item.isBulk()){
            expectedWeight += item.getWeight();
        }
        subTotal += item.getPrice();
        tax += item.getTax(TAX_RATE);
        return true;
    }
    
    public double getScaleWeight(){
        return scaleWeight;
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
    
    public double getCheckoutTotal(){
        return subTotal + getTax();
    }
    
    public double getSubtotal(){
        return subTotal;
    }
    
    public double getTax(){
        return tax;
    }
}










