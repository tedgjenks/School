package edu.inheritance.burroughs.trent;
import edu.jenks.dist.inheritance.*;

public class BarcodeItem extends Item implements Barcoded{

    public double price;
    
    public BarcodeItem(boolean bulk, double weight, double price){
        super(bulk);
        setWeight(weight);
        this.price = price;
        setBulk(bulk);
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public double getPrice(){
        return price;
    }
    
    public double getTax(double baseTaxRate){
        double tax = getPrice() * baseTaxRate; 
        return tax;
    }
    
    public boolean initBuyable(ItemHandler itemHandler){
        
        return false;
    }
}
