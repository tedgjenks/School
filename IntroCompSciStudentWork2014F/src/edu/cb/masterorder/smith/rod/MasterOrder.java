package edu.cb.masterorder.smith.rod;

import java.util.List;
import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

public class MasterOrder extends AbstractMasterOrder {

	@Override
	public int getTotalBoxes() {
		return 0;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		MasterOrder mo = new MasterOrder();
		((List<CookieOrder>) mo.getTotalBoxesByVariety()).add(new CookieOrder("Chocolate Chip", 1));
		((List<CookieOrder>) mo.getTotalBoxesByVariety()).add(new CookieOrder("Shortbread", 5));
		((List<CookieOrder>) mo.getTotalBoxesByVariety()).add(new CookieOrder("Macaroon", 2));
		((List<CookieOrder>) mo.getTotalBoxesByVariety()).add(new CookieOrder("Chocolate Chip", 3));
		return null;
	}

	@Override
	public int removeVariety(String cookieVar) {
		MasterOrder snacks = new MasterOrder();
		snacks.removeVariety("Chocolate Chip");
		snacks.removeVariety("Shortbread");
		snacks.removeVariety("Macaroon");
		snacks.getOrders().add(new CookieOrder("Chocolate Chip", 1));
		snacks.getOrders().add(new CookieOrder("Shortbread", 5));
		snacks.getOrders().add(new CookieOrder("Macaroon", 2));
		snacks.getOrders().add(new CookieOrder("Chocolate Chip", 3));
		return 0;


	}

}
