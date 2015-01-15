package edu.cb.masterorder.gottlieb.matthew;

import java.util.*;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MasterOrder extends AbstractMasterOrder{
	
	
	@Override
	public int getTotalBoxes() {
		int total = 0;
		List<CookieOrder> cookieorder = getOrders();
		for(int i=0; i < cookieorder.size(); i++ ){
			total += this.orders.get(i).getNumBoxes();  
		}
		return total;
	}

	@Override
	public Map<String, Integer> getTotalBoxesByVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeVariety(String cookieVar) {
		int removetotal = 0;
		for(int i= orders.size()-1; i>0; i--){
			if(cookieVar.equals(this.orders.get(i).getVariety())){
				removetotal = this.orders.get(i).getNumBoxes();
				this.orders.remove(i);
			}
			
		}
		return removetotal;
	}

}
