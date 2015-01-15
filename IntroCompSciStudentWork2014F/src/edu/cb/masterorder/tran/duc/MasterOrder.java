package edu.cb.masterorder.tran.duc;

import java.util.*;

import edu.jenks.dist.cb.masterorder.*;

public class MasterOrder extends AbstractMasterOrder{

	public MasterOrder() {
		
	}

	@Override
	public int getTotalBoxes() {
		int totalBoxes = 0;
		for(int index = 0; index < getOrders().size(); index++){
			int currentBoxAmount = getOrders().get(index).getNumBoxes();
			totalBoxes = totalBoxes + currentBoxAmount;
		}
		return totalBoxes;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		Map<String, Integer> varietyMap = new HashMap<String, Integer>();
		MasterOrder order = new MasterOrder();
		List<CookieOrder> copyList = new ArrayList<CookieOrder>(getOrders());
		order.setOrders(copyList);
		int loopIndex = order.getOrders().size();
		for(int index = 0; index < loopIndex; index++){
			if(order.getOrders().size() == 0){
				break;
			}
			String curKey = order.getOrders().get(0).getVariety();
			int curVal = order.removeVariety(curKey);
			varietyMap.put(curKey, curVal);
		}
		return varietyMap;
	}

	@Override
	public int removeVariety(String cookieVar) {
		int numRemoved = 0;
		for(int index = getOrders().size() - 1; index >= 0; index--){
			int currentBoxAmount = 0;
			if(getOrders().get(index).getVariety().equals(cookieVar)){
				currentBoxAmount = getOrders().get(index).getNumBoxes();
				getOrders().remove(index);
			}
			numRemoved = numRemoved + currentBoxAmount;
		}
		return numRemoved;
	}

}
