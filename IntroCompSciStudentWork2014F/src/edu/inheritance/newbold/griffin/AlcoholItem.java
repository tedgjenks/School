package edu.inheritance.newbold.griffin;
import edu.jenks.dist.inheritance.*;

public class AlcoholItem extends BarcodeItem implements SinTaxable{
	
	private double sinTaxRate;
	private boolean checkID = true;
	
	public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate){
		super(bulk, weight, price);
		this.sinTaxRate = sinTaxRate;
	}
	
	public double getSinTaxRate(){
		return sinTaxRate;
	}
	
	public double getTax(double baseTaxRate){
		return super.getTax(baseTaxRate) + (price*sinTaxRate);
	}
	
	public void setSinTaxRate(double sinTaxRate){
		this.sinTaxRate = sinTaxRate;
	}
	
	public boolean initBuyable(ItemHandler itemHandler){
		return true;
	}
}
