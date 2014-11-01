package edu.joseph.juliet.inheritance;
import edu.jenks.inheritance.dist.Buyable;


public abstract class Item implements Buyable {
	private double itemWeight;
	private boolean bulkB;
	public Item(){
		//itemWeight = 0.0; CAN YOU JUST LEAVE EMPTY CONSTUCTOR WITH NO CODE??
	}
	public Item (double weight, boolean bulk){
        itemWeight=weight;
        bulkB=bulk;
    }
	
	public double getWeight(){
		return itemWeight;	
	}
	
	public void setWeight(double weight){
		itemWeight = weight;
	}
	
	public boolean isBulk(){
		return bulkB;
	}	
	
	public void setBulk(boolean bulk){
		bulkB = bulk;
	}
	
	public abstract double getPrice();
	
}
