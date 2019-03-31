package edu.inheritance.burroughs.trent;
import edu.jenks.dist.inheritance.*;

public abstract class Item extends Object implements Buyable{

    private boolean bulk;
    private double weight;
    
    public Item(boolean bulk){
        super();
        this.bulk = bulk;
    }
    
    public double getWeight(){
        return weight;
    }
    
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    public boolean isBulk(){
        return bulk;
    }
    
    public void setBulk(boolean bulk){
        this.bulk = bulk;
    }
}
