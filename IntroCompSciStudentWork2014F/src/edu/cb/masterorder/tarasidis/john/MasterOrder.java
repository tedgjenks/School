package edu.cb.masterorder.tarasidis.john;

import java.util.Map;

import edu.jenks.dist.cb.masterorder.*;


public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalBoxes() {
		int totalBoxes = 0;
		for (CookieOrder order : getOrders()) {
			int orderBoxNum = order.getNumBoxes();
			totalBoxes = totalBoxes + orderBoxNum;
		}
		return totalBoxes;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeVariety(String arg0) {
		int totNum = 0;
		for (int index = orders.size() - 1; index >= 0; index--){
			if (arg0.equals(this.orders.get(index).getVariety())){       
				totNum += this.orders.get(index).getNumBoxes();
		    	this.orders.remove(index); 
		    }
		} 
		    return totNum; 

	}

}
