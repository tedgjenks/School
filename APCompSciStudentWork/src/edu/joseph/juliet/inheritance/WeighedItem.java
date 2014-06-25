package edu.joseph.juliet.inheritance;
import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;

public class WeighedItem extends Item implements Weighable {
	private double poundPrice;
	private double scaleWght;
		
	public WeighedItem(){
		//poundPrice=0.0;
	}
	public double getScaleWeight(){
        return scaleWght;
    }
    public void setScaleWeight(double newscaleweight){
        scaleWght=newscaleweight;
    }
	public double getPricePerPound(){
		return poundPrice;
	}
	public void setPricePerPound(double pricePerPound){
		poundPrice = pricePerPound;
	}
   public double getPrice() {
        return getPricePerPound()*getScaleWeight();
    }
    
    public double getTax(double baseTaxRate) {
        return baseTaxRate; //Just return the parameter??
     }
   
    public boolean initBuyable(ItemHandler arg0) {
        return false; //what's it supposed to do?
    }

}
	

