package edu.cb.masterorder.wilbanks.tanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalBoxes() {
		List<CookieOrder>orders = getOrders();
		int number = 0;
		int current =0;
		for(int index=orders.size()-1; index>0; index--){
			current = getTotalBoxesByVariety().get(index);
			number += current;
		}
		return number;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		
		return null;
	}

	@Override
	public int removeVariety(String kind) {
		List<CookieOrder>orders = getOrders();
		int number = 0;
		for(int index=orders.size()-1; index>0; index--){
			if(kind.equals(orders.get(index))){
				number += getTotalBoxesByVariety().get(index);
			}
		}
		return number;
	}

}
