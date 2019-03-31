package edu.inheritance.burroughs.trent;
import edu.jenks.dist.inheritance.*;

public class WeighedItem extends Item implements Weighable{
    
    private double pricePerPound;
    
    public WeighedItem(boolean bulk,double pricePerPound){
        super(bulk);
        this.pricePerPound = pricePerPound;
    }
    
    public double getPricePerPound(){
        return pricePerPound;
    }
    
    public void setPricePerPound(double pricePerPound){
        this.pricePerPound = pricePerPound;
    }
    
    public double getPrice(){
        double price = getWeight() * pricePerPound;
        return price;
    }
    
    public double getTax(double baseTaxRate){
        return getPrice() * baseTaxRate;
    }
    
    public boolean initBuyable(ItemHandler itemHandler){
        setWeight(itemHandler.getScaleWeight());
        return false;
    }
}
