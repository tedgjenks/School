package edu.cb.masterorder.macias.marcus;

import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

public class MasterOrder extends AbstractMasterOrder {
	public static void main(String[] args) {
		MasterOrder run = new MasterOrder();
		//run.getOrders().add(new CookieOrder("Chocolate Chip", 1));
		//run.getOrders().add(new CookieOrder("Shortbread", 5));
		//run.getOrders().add(new CookieOrder("Macaroon", 2));
		//run.getOrders().add(new CookieOrder("Chocolate Chip", 3));
		//run.test();
		// System.out.println(run.removeVariety("Chocolate Chip"));
		// run.test();
		
		run.getOrders().add(new CookieOrder("Chocolate Chip", 1));
		run.getOrders().add(new CookieOrder("Chocolate Chip", 2));
		run.getOrders().add(new CookieOrder("Chocolate Chip", 3));
		run.getOrders().add(new CookieOrder("Chocolate Chip", 4));
		System.out.println(run.getTotalBoxesByVariety());
	}

	public void test() {
		for (CookieOrder a : getOrders()) {
			System.out.println(a.getVariety() + " : " + a.getNumBoxes());
		}
	}

	public int getTotalBoxes() {
		int totalOrders = 0;
		for(CookieOrder order : getOrders()) {
			totalOrders += order.getNumBoxes();
		}
		return totalOrders;
	}

	public Map<String, Integer> getTotalBoxesByVariety() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (CookieOrder order : getOrders()) {
			if (map.containsKey(order.getVariety())) {
				int temp = map.get(order.getVariety());
				map.put(order.getVariety(), order.getNumBoxes() + temp);
			} else {
				map.put(order.getVariety(), order.getNumBoxes());
			}
		}
		// System.out.println(map.keySet());
		return map;
	}

	public int removeVariety(String cookieVar) {
		int count = 0;
		for (int i = 0; i < this.getOrders().size(); i++) {
			if (getOrders().get(i).getVariety().equals(cookieVar)) {
				count += getOrders().get(i).getNumBoxes();
				getOrders().remove(i);
				i--;
				continue;
			}

		}
		return count;
	}

}
