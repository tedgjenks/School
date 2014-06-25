package edu.joseph.juliet.inheritance;
import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;

public class SelfCheckout implements ItemHandler {

	public final double TAX_RATE;
	private double scaleWght=0; 
	private boolean idCheck =false;
	private double expectedWeight=0.0;
	private double total=0;
	
	public SelfCheckout(double taxRate) {
		TAX_RATE = taxRate;
	}
	public double getTaxRate(){
        return TAX_RATE;
    }
	public double getScaleWeight(){ 
		return scaleWght;
	}
	public void setScaleWeight(double scaleWeight){
		scaleWght = scaleWeight;
	}
	public boolean isCheckID(){
		return idCheck;
	}
	public void setCheckID(boolean checkID){
		idCheck = checkID;
	}
	public double getExpectedWeight(){
		return expectedWeight;
	}
	public boolean addItem(Buyable item){ 
		if(item instanceof WeighedItem){
            return false;
        }
        else if(item instanceof AlcoholItem){
            AlcoholItem a = (AlcoholItem)item;
            setCheckID(true);
            if(!a.isBulk())
                 expectedWeight+=a.getWeight();
            total+=(a.getPrice()+(a.getPrice()*(getTaxRate()+a.getSinTaxRate())));
            return true;
        }
        else{ //item = barcode
            if(!(item.isBulk()))
                 expectedWeight+=item.getWeight();
            total+=(item.getPrice()*(1+getTaxRate()));
            return true;
        }		
	}
	public boolean addItem(Buyable item, double scaleWeight){ 
		 setScaleWeight(scaleWeight);
		 if(scaleWeight<=0 && item instanceof WeighedItem){
			 	return false;    
	     }
		 //weighed item scale weight >=0 otherwise scale weight 0
	     else if(item instanceof WeighedItem){
            WeighedItem wItem = (WeighedItem)item;
            wItem.setScaleWeight(scaleWeight);
            if(wItem.isBulk()==false)
                expectedWeight+=getScaleWeight();
            total+=(wItem.getPrice()*(1+getTaxRate()));
	     }
	     else if(item instanceof AlcoholItem){
            AlcoholItem aItem = (AlcoholItem)item;
            setCheckID(true);
            if(aItem.isBulk()==false)
                expectedWeight+=aItem.getWeight();
            total+=(aItem.getPrice()+(aItem.getPrice()*(TAX_RATE+aItem.getSinTaxRate())));
	     }
	     else if (item instanceof BarcodeItem){//
	    	if(scaleWeight>0 || scaleWeight <0)
	    		return false;
            if(item.isBulk()==false)
                expectedWeight+=item.getWeight();
            total+=(item.getPrice()*(1+getTaxRate()));
	     }
		 return true;
	}
	public double getCheckoutTotal(){
		return total;
	}
}
