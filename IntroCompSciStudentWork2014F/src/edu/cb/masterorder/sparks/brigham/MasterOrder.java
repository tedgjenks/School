package edu.cb.masterorder.sparks.brigham;
import java.util.*;
import edu.jenks.dist.cb.masterorder.*;
public class MasterOrder extends AbstractMasterOrder {
	private Map<String, Integer> goodiesMap;
	public MasterOrder() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getTotalBoxes() {
		// TODO Auto-generated method stub
		int total = 0;
		List<CookieOrder> goodies = getOrders();
		if(goodies.isEmpty()){
			return 0;
		}
		for(int i=0; i< goodies.size(); i++){
			CookieOrder cookie = goodies.get(i);
			total = total += cookie.getNumBoxes();
		}
		return total;
	}
	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		List<CookieOrder> goodies = getOrders();
		for(int i = 0; i<goodies.size();){
			String cookieVar = goodies.get(i).getVariety();
			int removed = removeVariety(cookieVar);
			if(removed == 0){
				i++;
			}else{
				goodies.add(new CookieOrder(cookieVar, removed));
			}
		}
		goodiesMap = null;
		for(int i= 0; i<goodies.size();){
			goodiesMap.put(goodies.get(i).getVariety(), goodies.get(i).getNumBoxes());
		}			
		return goodiesMap;
	}
	@Override
	public int removeVariety(String cookieVar) {
		// TODO Auto-generated method stub
		int boxesRemoved = 0;
		List<CookieOrder> goodies = getOrders();
		for(int i = 0; i<goodies.size();){
			if(cookieVar == goodies.get(i).getVariety()){
				boxesRemoved += goodies.get(i).getNumBoxes();
				goodies.remove(i);
			}else{
				i++;
			}
		}
		return boxesRemoved;
	}
}