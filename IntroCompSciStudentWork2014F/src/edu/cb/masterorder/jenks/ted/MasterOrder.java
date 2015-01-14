/**
 * 
 */
package edu.cb.masterorder.jenks.ted;

import edu.jenks.dist.cb.masterorder.AbstractMasterOrder;
import edu.jenks.dist.cb.masterorder.CookieOrder;

/**
 * @author JenksT
 *
 */
public class MasterOrder extends AbstractMasterOrder {

	/**
	 * 
	 */
	public MasterOrder() {
		super();
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.masterorder.AbstractMasterOrder#getTotalBoxes()
	 */
	@Override
	public int getTotalBoxes() {
		int totalBoxes = 0;
		for(CookieOrder cookieOrder : orders)
			totalBoxes += cookieOrder.getNumBoxes();
		return totalBoxes;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.masterorder.AbstractMasterOrder#removeVariety(java.lang.String)
	 */
	@Override
	public int removeVariety(String cookieVar) {
		int numberRemoved = 0;
		for(int index = orders.size() - 1; index >= 0; index--) {
			CookieOrder cookieOrder = orders.get(index);
			if(cookieVar.equals(cookieOrder.getVariety())) {
				orders.remove(index);
				numberRemoved += cookieOrder.getNumBoxes();
			}
		}
		return numberRemoved;
	}

}
