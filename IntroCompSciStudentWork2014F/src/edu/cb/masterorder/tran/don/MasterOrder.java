package edu.cb.masterorder.tran.don;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.cb.masterorder.*;

public class MasterOrder extends AbstractMasterOrder{

	public static void main(String[] args) {
		MasterOrder mo = new MasterOrder();
		
		List<CookieOrder> co = new ArrayList<CookieOrder>();
		co.add(new CookieOrder("Chocolate Chip", 5));
		co.add(new CookieOrder("Chocolate Chip", 5));
		co.add(new CookieOrder("Chocolate ", 5));
		co.add(new CookieOrder("Chocolate Chip", 5));
		mo.setOrders(co);
		System.out.println(mo.getTotalBoxes());
		//System.out.println(mo.removeVariety("Chocolate Chip"));
		System.out.println(mo.getTotalBoxes());
		Map<String, Integer> m = mo.getTotalBoxesByVariety();
		//System.out.println(m.size());
		for(String s : m.keySet()) {
			System.out.println(s);
		}
		for(Integer i : m.values()) {
			System.out.println(i);
		}
		
	}
	
	public MasterOrder() {
		
	}
	
	public int getTotalBoxes() {
		int count = 0;
		for(int i = 0; i < getOrders().size(); i++) {
			count += getOrders().get(i).getNumBoxes();
		}
		return count;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		Map<String, Integer> map = new HashMap<String,Integer>();
		List<CookieOrder> co = new ArrayList<CookieOrder>();
		//System.out.println(co.size() + " dasdasddsda" + getOrders().size());
		for(int i = 0; i < getOrders().size(); i++) {
			//System.out.println("ffdsf");
			co.add(getOrders().get(i));
			
		}
		//System.out.println(co.size() + " dsda");
		while(getOrders().size() > 0) {
			map.put(getOrders().get(0).getVariety(), removeVariety(getOrders().get(0).getVariety()));
		}
		setOrders(co);
		return map;
	}

	@Override
	public int removeVariety(String cook) {
		int count = 0;
		for(int i = 0; i < getOrders().size(); i++) {
			if(getOrders().get(i).getVariety().equals(cook)) {
				count += getOrders().get(i).getNumBoxes();
				getOrders().remove(i);
				i--;
			}
		}
		return count;
	}

}
