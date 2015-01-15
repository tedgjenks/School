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
		 int sum = 0;
		 for (CookieOrder co : this.orders) {
		 sum += co.getNumBoxes();
		 }
		 return sum;
		} 	

	public int removeVariety1(String cookieVar) {
		 int numBoxesRemoved = 0;
		 for (int i = this.orders.size() - 1; i >= 0; i--) {
		 if (cookieVar.equals(this.orders.get(i).getVariety())) {
		 numBoxesRemoved += this.orders.get(i).getNumBoxes();
		 this.orders.remove(i);
		 }
		 }
		 return numBoxesRemoved;
		}
		
		public int removeVariety(String cookieVar) {
		 int numBoxesRemoved = 0;
		 int i = 0;
		 while (i < this.orders.size()) {
		 if (cookieVar.equals(this.orders.get(i).getVariety())) {
		 numBoxesRemoved += this.orders.get(i).getNumBoxes();
		 this.orders.remove(i);
		 } else {
		 i++;
		 }
		 }
		 return numBoxesRemoved;
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
		int removed = goodies.removeVariety1("Chocolate Chip");
		
		System.out.println("After removing " + removed+ " chocolate chip we have: ");
		System.out.println("goodies");
		int total = 0;
		System.out.println("expected 4 and got " + total);
	    total = goodies.removeVariety1("Brownie");
	    System.out.println("expected 0 and got " + total);
	    System.out.println("The current size of the master order should be 2 and we got " + goodies.orders.size());
	  
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}
}