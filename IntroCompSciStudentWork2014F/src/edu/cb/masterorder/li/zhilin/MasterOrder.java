package edu.cb.masterorder.li.zhilin;

import java.util.Map;

import edu.jenks.dist.cb.masterorder.*;

public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		}

	@Override
	public int getTotalBoxes() {
		MasterOrder MO = new MasterOrder();
		MO.orders.add(new CookieOrder("ChocolateChip", 1));
		MO.orders.add(new CookieOrder("Shortbread", 5));
		MO.orders.add(new CookieOrder("Macaroon", 2));
		MO.orders.add(new CookieOrder("Chocolate Chip", 3));
		int amount = MO.getTotalBoxes();
		return amount;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeVariety(String arg0) {
		MasterOrder goodies = new MasterOrder();
		goodies.getOrders().add(new CookieOrder("Chocolate Chip", 1));
		goodies.getOrders().add(new CookieOrder("Shortbread", 5));
		goodies.getOrders().add(new CookieOrder("Macaroon", 2));
		goodies.getOrders().add(new CookieOrder("Chocolate Chip", 3));
		int amount = goodies.removeVariety(arg0);
		return amount;
	}

}
