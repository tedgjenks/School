package edu.cb.masterorder.smithdeal.thomas;

import java.util.Map;

import edu.jenks.dist.cb.masterorder.*;

public class MasterOrder extends AbstractMasterOrder{
	
	public MasterOrder() {
		
		
	
	}

	public static void main(String[] args) {
		MasterOrder goodies = new MasterOrder();
		goodies.getOrders().add(new CookieOrder("Chocolate Chip", 1));
		goodies.getOrders().add(new CookieOrder("Shortbread", 5));
		goodies.getOrders().add(new CookieOrder("Macaroon", 3));
		goodies.getOrders().add(new CookieOrder("Chocolate Chip", 1));
		
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

	public void addOrder(CookieOrder cookieOrder) {
		
		
	}

}
