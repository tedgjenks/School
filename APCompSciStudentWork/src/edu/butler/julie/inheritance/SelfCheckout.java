package edu.butler.julie.inheritance;

import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;

public class SelfCheckout implements ItemHandler{
    public final double TAX_RATE;
    private double expectedWeight=0;
    private double total;
    private boolean checkID=false;
    private double scaleWeight;
   
   
    public SelfCheckout(double taxRate){
        TAX_RATE=taxRate;
    }
    public double getRealTaxRate(){
        return 1+TAX_RATE;
    }
    public boolean isCheckID(){
        return checkID;
    }
    public void setCheckID(boolean checkID){
        this.checkID=checkID;
    }
    public double getExpectedWeight(){
        return expectedWeight;
    }
    public double getCheckoutTotal(){
        return total;
       
    }
    public double getTotal(){
        return total;
    }
	@Override
	//Precondition: The Buyable Object must not be an instance of WeighedItem
	public boolean addItem(Buyable arg0) {
		if(arg0 instanceof WeighedItem){
            return false;
        }
        else if(arg0 instanceof AlcoholItem){
            AlcoholItem a = (AlcoholItem)arg0;
            setCheckID(true);
            if(!a.isBulk())
                expectedWeight+=a.getWeight();
            total+=(a.getPrice()+a.getTax(TAX_RATE));
            return true;
        }
        else{
            if(!arg0.isBulk())
                expectedWeight+=arg0.getWeight();
            total+=(arg0.getPrice()+arg0.getTax(TAX_RATE));
            return true;
        }
	}
	@Override
	public boolean addItem(Buyable arg0, double arg1) {
		if(arg1<=0 && arg0 instanceof WeighedItem){
            return false;
        }
        else if(arg0 instanceof WeighedItem){
            WeighedItem a = (WeighedItem)arg0;
            setScaleWeight(arg1);
            a.setScaleWeight(arg1);
            if(!a.isBulk())
                expectedWeight+=getScaleWeight();
            total+=(a.getPrice()+a.getTax(TAX_RATE));
        }
        else if(arg0 instanceof AlcoholItem){
            AlcoholItem a = (AlcoholItem)arg0;
            setCheckID(true);
            if(!a.isBulk())
                expectedWeight+=a.getWeight();
            total+=(a.getPrice()+a.getTax(TAX_RATE));
        }
        else{
            if(!arg0.isBulk())
                expectedWeight+=arg0.getWeight();
            total+=(arg0.getPrice()+arg0.getTax(TAX_RATE));
           
        }
        return true;
	}
	@Override
	public double getScaleWeight() {
		// TODO Auto-generated method stub
		return scaleWeight;
	}
	public void setScaleWeight(double newScaleWeight){
		scaleWeight=newScaleWeight;
	}
	public double getWeight(){
		return expectedWeight;
	}
   

}

