package edu.cb.masterorder.smith.eli;

import java.util.Map;

import edu.jenks.dist.cb.masterorder.*;

public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalBoxes() {
		int totalBoxes = 0;
		for(int index = 0; index < getOrders().size(); index ++){
			int currentBoxes = getOrders().get(index).getNumBoxes();
			totalBoxes = totalBoxes + currentBoxes;
		}
		return totalBoxes;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeVariety(String cookieName) {
		int boxes = 0;
		for(int index = 0; index < getOrders().size(); index ++){
			CookieOrder currentObject = getOrders().get(index);
			String currentVariety = currentObject.getVariety();
			int currentBoxes = currentObject.getNumBoxes();
			if(cookieName.equals(currentVariety)){
				getOrders().remove(index);
				boxes = boxes + currentBoxes;
			}
		}
		return boxes;
	}

}
