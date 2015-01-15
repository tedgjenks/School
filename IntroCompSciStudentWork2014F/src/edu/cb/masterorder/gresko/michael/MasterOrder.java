package edu.cb.masterorder.gresko.michael;

import java.util.List;
import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

public class MasterOrder extends AbstractMasterOrder {

	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTotalBoxes() {
		int total = 0;
		List<CookieOrder> orders = getOrders();
		for(int index = 0; index < orders.size(); index++) {
			int temp = orders.get(index).getNumBoxes();
			total = total + temp;
		}
		return total;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeVariety(String variety) {
		int totalRemoved = 0;
		List<CookieOrder> orders = getOrders();
		for(int index = 0; index < orders.size(); index++){
			String temp = orders.get(index).getVariety();
			if(temp.equals(variety)) {
				int removed = orders.get(index).getNumBoxes();
				totalRemoved = totalRemoved + removed;
				orders.remove(index);
				setOrders(orders);
			}
		}
		return totalRemoved;
	}

}
