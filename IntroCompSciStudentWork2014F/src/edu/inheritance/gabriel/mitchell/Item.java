package edu.inheritance.gabriel.mitchell;

import edu.jenks.dist.inheritance.*;
public class Item implements Buyable
{
    private boolean itemBulk;
    private double itemWeight;
    private double itemPrice;
    public Item(boolean bulk){
        itemBulk = bulk;
    }
    public double getPrice(){
        return itemPrice;
    }
    public void setPrice(double price){
        itemPrice = price;
    }
    public double getWeight(){
        return itemWeight;
    }
    public void setWeight(double weight){
        itemWeight = weight;
    }
    public double getTax(double baseTaxRate){
        return getPrice() * baseTaxRate;
    }
    public boolean isBulk(){
        return itemBulk;
    }
    public void setBulk(boolean bulk){
        itemBulk = bulk;
    }
    public boolean initBuyable(ItemHandler itemHandler){
        setWeight(itemHandler.getExpectedWeight());
        return false;
    }
}
