package edu.cb.masterorder.scates.collin;

import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		
	}

	public static void main(String[] args) {
		MasterOrder Orders = new MasterOrder();
		Orders.orders.add(new CookieOrder("Chocloate chip", 1));
		Orders.orders.add(new CookieOrder("Short Bread", 5));
		Orders.orders.add(new CookieOrder("Macoon", 2));
		Orders.orders.add(new CookieOrder("Chocloate chip", 3));
	}

	@Override
	public int getTotalBoxes() {
		
		return 0;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		
		return null;
	}

	@Override
	public int removeVariety(String arg0) {
		
		return 0;
	}
}