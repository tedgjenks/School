package edu.cb.masterorder.latham.chase;

import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

///OrderS.orders.add(new CookieOrder("ChocolateChip", 1));
///OrderS.orders.add(new CookieOrder("Short Bread", 3));
///OrderS.orders.add(new CookieOrder("Macaroon", 2));
///OrderS.orders.add(new CookieOrder("ChocolateChip", 5));
public class MasterOrder extends AbstractMasterOrder {
		
	private Object ChocolateChip;
	private Object ShortBread;
	private Object Macaroon;
	
	@Override
	public int getTotalBoxes() {
		CookieOrder ChocolateChip = null;
		CookieOrder ShortBread = null;
		CookieOrder Macaroon = null;
		orders.add(ChocolateChip);
		orders.add(ShortBread);
		orders.add(Macaroon);
		return 0;
	}

	

	private void setOrders(int ChocolateChip, int ShortBread, int Macaroon) {
		orders.add(ChocolateChip, null);ChocolateChip = 1;
		orders.add(ShortBread, null);ShortBread = 2;
		orders.add(Macaroon, null);Macaroon = 4;
		orders.add(ChocolateChip, null);ChocolateChip = 3;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		
		return null;
	}
	@Override
	public int removeVariety(String arg0) {
		orders.remove(ChocolateChip);
		orders.remove(ShortBread);
		orders.remove(Macaroon);
		
		return 0;
	}



	

}
