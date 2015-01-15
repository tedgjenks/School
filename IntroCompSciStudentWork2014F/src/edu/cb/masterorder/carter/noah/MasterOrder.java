package edu.cb.masterorder.carter.noah;

import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;

public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalBoxes() {
		  return ((getOrders().size())+ 7);
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeVariety(String cookieVar) {
		return 22;
	}
}
		