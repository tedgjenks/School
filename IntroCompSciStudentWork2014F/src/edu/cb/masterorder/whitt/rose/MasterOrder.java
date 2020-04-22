package edu.cb.masterorder.whitt.rose;

import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

public class MasterOrder extends AbstractMasterOrder {

	public static void main(String[] args) {
		MasterOrder g = new MasterOrder();
		g.getOrders().add(new CookieOrder("Chocolate Chip", 1));
		g.getOrders().add(new CookieOrder("Shortbread", 5));
		g.getOrders().add(new CookieOrder("Macaroon", 2));
		g.getOrders().add(new CookieOrder("Chocolate Chip", 3));

		System.out.println(g.getTotalBoxes());
		
//		System.out.println(g.removeVariety("Chocolate Chip"));
		
		for (int i = 0; i < g.getOrders().size(); i++) {
			System.out.println(g.getOrders().get(i).getVariety());
		}
		
		
		for (String keys : g.getTotalBoxesByVariety().keySet())  
		{
		   System.out.println(keys + ":"+ g.getTotalBoxesByVariety().get(keys));
		}
	}
	
	public MasterOrder() {
		
	}
	
	@Override
	public int getTotalBoxes() {
		int count = 0;
		for (int i = 0; i < getOrders().size(); i++) {
			count += getOrders().get(i).getNumBoxes();
		}
		return count;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < getOrders().size(); i++) {
			map.put(getOrders().get(i).getVariety(), getNumVariety(getOrders().get(i).getVariety()));
		}
		
		return map;
	}

	@Override
	public int removeVariety(String arg0) {
		int count = 0;
		for (int i = 0; i < getOrders().size(); i++) {
			if (getOrders().get(i).getVariety().equals(arg0)) {
				count += getOrders().get(i).getNumBoxes();
				getOrders().remove(i);
			}
		}
		return count;
	}
	
	public int getNumVariety(String arg0) {
		int count = 0;
		for (int i = 0; i < getOrders().size(); i++) {
			if (getOrders().get(i).getVariety().equals(arg0)) {
				count += getOrders().get(i).getNumBoxes();
			}
		}
		return count;
	}

}
