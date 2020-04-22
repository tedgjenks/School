package edu.cb.masterorder.rhodes.maddux;

import java.util.*;
import edu.jenks.dist.cb.masterorder.*;

public class MasterOrder extends AbstractMasterOrder{

	public static void main(String[] args) {
		MasterOrder test = new MasterOrder();
		List<CookieOrder> list = new ArrayList<CookieOrder>();
		list.add(new CookieOrder("C", 2));
		list.add(new CookieOrder("O", 3));
		list.add(new CookieOrder("O", 4));
		list.add(new CookieOrder("K", 1));
		list.add(new CookieOrder("I", 2));
		list.add(new CookieOrder("C", 3));
		list.add(new CookieOrder("S", 4));
		test.setOrders(list);
		System.out.println("Total number of boxes in orders: " + test.getTotalBoxes());
		//System.out.println(test.getTotalBoxesSpec("O"));
		System.out.println("Map: " + test.getTotalBoxesByVariety());
		System.out.println("Number of boxes removed: " + test.removeVariety("O"));
	}
	
	public MasterOrder() {
		
	}
	
	public int getTotalBoxes() {
		int total = 0;
		for(CookieOrder c : getOrders()) {
			total += c.getNumBoxes();
		}
		return total;
	}

	public int getTotalBoxesSpec(String var) {
		int total = 0;
		for(CookieOrder c : getOrders()) {
			if(c.getVariety().equals(var)) {
				total += c.getNumBoxes();
			}
		}
		return total;
	}
	
	public Map<String, Integer> getTotalBoxesByVariety() {
		Map<String, Integer> m = new HashMap<String, Integer>();
		for(CookieOrder c : getOrders()) {
			
			m.put(c.getVariety(), getTotalBoxesSpec(c.getVariety()));
		}
		return m;
	}

	public int removeVariety(String cookieVar) {
		int numBoxes = getTotalBoxesSpec(cookieVar);
		for(int i = 0; i < getOrders().size(); i++) {
			if(getOrders().get(i).getVariety().equals(cookieVar)) {
				getOrders().remove(i);
				i--;
			}
		}
		return numBoxes;
	}
}
