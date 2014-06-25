package edu.butler.julie.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;

public class WeighedItem extends Item implements Weighable{
    private double priceperpound,scaleweight;
   
    public WeighedItem(){
       
    }
    public WeighedItem(boolean bulk, double priceperpound, double scaleweight){
        super(0,bulk);
        this.priceperpound=priceperpound;
        this.scaleweight=scaleweight;
    }
    public double getPricePerPound(){
        return priceperpound;
    }
    public void setPricePerPound(double newpriceperpound){
        priceperpound=newpriceperpound;
    }
    public double getScaleWeight(){
        return scaleweight;
    }
    public void setScaleWeight(double newscaleweight){
        scaleweight=newscaleweight;
    }
    @Override
    public double getPrice() {
        // TODO Auto-generated method stub
        return getPricePerPound()*getScaleWeight();
    }
	@Override
	public double getTax(double arg0) {
		// TODO Auto-generated method stub
		return getPrice()*arg0;
	}
	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
