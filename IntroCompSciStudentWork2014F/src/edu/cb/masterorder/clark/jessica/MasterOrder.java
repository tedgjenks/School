package edu.cb.masterorder.clark.jessica;

import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;

public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalBoxes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeVariety(String cookieVar) {
		// TODO Auto-generated method stub
		MasterOrder goodies = new MasterOrder();
		goodies.removeVariety("Chocolate Chip");
		goodies.removeVariety("Shortbread");
		goodies.removeVariety("Macaroon");
		goodies.removeVariety("Chocolate Chip");
		
		return 0;
		
	}

}
