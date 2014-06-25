
package edu.butler.julie.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

public class BarcodeItem extends Item implements Barcoded {
    private double price;
    private double tax;
    public BarcodeItem(){
       
    }
    public BarcodeItem(double weight, boolean bulk, double price){
        super(weight, bulk);
        this.price=price;
        
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price){
        this.price=price;
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

