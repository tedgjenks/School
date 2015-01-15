package edu.cb.masterorder.fan.jijie;

import java.util.*;


import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;

public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalBoxes() {
		// TODO Auto-generated method stub
		ArrayList <Integer> box  = new ArrayList <Integer>();
		for(int index = 0; index < orders.size(); index++){
			return box.get(index);
		}
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

		for(int index = 0; index < orders.size(); index++){
			orders.remove(cookieVar);
			index -= 1;
			return orders.size();
		}
		return 0;
		
	}

}
