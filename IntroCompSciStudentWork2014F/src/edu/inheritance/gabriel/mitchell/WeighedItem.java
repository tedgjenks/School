package edu.inheritance.gabriel.mitchell;

import edu.jenks.dist.inheritance.*;
public class WeighedItem extends Item implements Weighable
{
    private double pPLb;
    public WeighedItem(boolean bulk, double pricePerPound){
        super(bulk);
        pPLb = pricePerPound;
    }
    public double getPricePerPound(){
        return pPLb;
    }
    public void setPricePerPound(double pricePerPound){
        pPLb = pricePerPound;
    }
    public double getPrice(){
        return pPLb * getWeight();
    }
    public double getTax(double baseTaxRate){
        return this.getPrice()*baseTaxRate;
    }
    public boolean initBuyable(ItemHandler itemHandler){
        setWeight(itemHandler.getScaleWeight());
        return false;
    }
}
