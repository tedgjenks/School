package edu.butler.julie.inheritance;

import edu.jenks.inheritance.dist.Buyable;

public abstract class Item implements Buyable{
    private double weight;
    private boolean bulk;
    public Item(){
       
    }
    public Item(double weight, boolean bulk){
        this.weight=weight;
        this.bulk=bulk;
    }

    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight){
        this.weight=weight;
    }
    public boolean isBulk(){
        return bulk;
    }
    public void setBulk(boolean bulk){
        this.bulk=bulk;
    }
    //public abstract double getPrice();
      
  
}
