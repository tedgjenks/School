package edu.cb.masterorder.guareschi.alex;
import java.util.*;

import edu.jenks.dist.cb.masterorder.*;
public class MasterOrder extends AbstractMasterOrder{

	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalBoxes() {
		
	    int totalBoxes = 0;
	    List <CookieOrder> cookieOrder = getOrders();
	    for (int i=0; i< orders.size(); i++){
	      totalBoxes += this.orders.get(i).getNumBoxes();
	    }
	    return totalBoxes;
	  }
	  

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeVariety(String cookieVariety){
		int removedTotal = 0;
			for (int i = orders.size() - 1; i >= 0; i--){
				if (cookieVariety.equals(this.orders.get(i).getVariety())){       
					removedTotal += this.orders.get(i).getNumBoxes();
			    	this.orders.remove(i); 
			    }
			} 
			    return removedTotal; 

	}

}