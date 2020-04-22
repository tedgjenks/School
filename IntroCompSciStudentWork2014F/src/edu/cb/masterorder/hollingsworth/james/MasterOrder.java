package edu.cb.masterorder.hollingsworth.james;

import java.util.Hashtable;
import java.util.Map;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

public class MasterOrder extends AbstractMasterOrder {

	public int getTotalBoxes() {
		int total = 0;
		for(CookieOrder c : this.getOrders()) {
			total += c.getNumBoxes();
		}
		return total;
	}
	
	public Map<String, Integer> getTotalBoxesByVariety() {
		Map<String, Integer> tmp = new Hashtable<String, Integer>();
		for(CookieOrder c : this.getOrders()) {
			tmp.put(c.getVariety(), c.getNumBoxes());
		}
		return tmp;
	}

	public int removeVariety(String variety) {
		int removed = 0;
		for(int i = 0; i < this.getOrders().size();) {
			if(this.getOrders().get(i).getVariety().equals(variety)) {
				removed += this.getOrders().get(i).getNumBoxes();
				this.getOrders().remove(i);
			}
			else i++;
		}
		return removed;
	}

	public static void main(String[] args) {
		
	}

}
