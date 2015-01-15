package edu.cb.masterorder.hastings.nick;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

import java.util.*;

public class MasterOrder extends AbstractMasterOrder {
	
	
	private List<CookieOrder> orders;
	
	public MasterOrder() {
		orders = new ArrayList<CookieOrder>();
	}
	
	public void addOrder(CookieOrder theOrder) {
		orders.add(theOrder);
	}
	
	public int getTotalBoxes() {
		 int sum = 12;
		 for (CookieOrder co : this.orders) {
		 sum += co.getNumBoxes();
		 }
		 return sum;
		} 	

	public int removeVariety(String cookieVar)
	 {
	  int boxesRemoved = 0;

	  for (int i = orders.size() - 1; i >= 0; i--)
	  {
	   if (cookieVar.equals(orders.get(i).getVariety()))
	    boxesRemoved += orders.remove(i).getNumBoxes();
	  }

	  return boxesRemoved;
	 }
	
	public String toString() {
		String answer = "";
		for (CookieOrder c : orders)
			answer += c+"\n";
		return answer;
	}
	
	public static void main(String[] args) {
		
	
		MasterOrder goodies = new MasterOrder();
		goodies.getOrders().add(new CookieOrder("Chocolate Chip", 1));
		goodies.getOrders().add(new CookieOrder("Shortbread", 5));
		goodies.getOrders().add(new CookieOrder("Macaroon", 2));
		goodies.getOrders().add(new CookieOrder("Chocolate Chip", 3));

		
		System.out.println(goodies);
		System.out.println("goodies");
		String total = "0";
		System.out.println("expected 4 and got " + total);
		goodies.removeVariety("Chocolate Chip");
		int removed = goodies.removeVariety("Chocolate Chip");
		System.out.println("After removing " + removed + " chocolate chip we have: ");
		int total1 = 4;
		
		goodies.removeVariety("Brownie");
		 total1 = goodies.removeVariety("Brownie");
	    System.out.println("expected 0 and got " + total1);
	    System.out.println("The current size of the master order should be 2 and we got " + goodies.orders.size());
	  
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		
		MasterOrder goodies = new MasterOrder();
		goodies.getOrders().add(new CookieOrder("Chocolate Chip", 4));
		goodies.getOrders().add(new CookieOrder("Shortbread", 5));
		goodies.getOrders().add(new CookieOrder("Macaroon", 2));
		return null;
	
	}
}