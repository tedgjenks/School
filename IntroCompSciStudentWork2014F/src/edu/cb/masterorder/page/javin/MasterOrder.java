package edu.cb.masterorder.page.javin;
import java.util.*;

import edu.jenks.dist.cb.masterorder.*;
public class MasterOrder extends AbstractMasterOrder{

	@Override
	public int getTotalBoxes() {
		int bigNum = 0;
		for(CookieOrder thus : getOrders()) {
			bigNum += thus.getNumBoxes();
		}
		return bigNum;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		Map<String, Integer> theMap = new Hashtable<String, Integer>();
		for(CookieOrder thus : getOrders()) {
			if(theMap.isEmpty()) {
				theMap.put(thus.getVariety(), thus.getNumBoxes());
				continue;
			}
			if(theMap.containsKey(thus.getVariety())) {
				theMap.put(thus.getVariety(), theMap.get(thus.getVariety()) + thus.getNumBoxes());
			}else {
				theMap.put(thus.getVariety(), thus.getNumBoxes());
			}
		}
		return null;
	}

	@Override
	public int removeVariety(String var) {
		// TODO Auto-generated method ub
		int bigNum = 0;
		for(int i = 0; i < getOrders().size(); i++) {
			if(getOrders().get(i).getVariety().equals(var)){
				bigNum += getOrders().get(i).getNumBoxes();				
				getOrders().remove(i);
				i--;
			}
		}
		return bigNum;
	}

}
