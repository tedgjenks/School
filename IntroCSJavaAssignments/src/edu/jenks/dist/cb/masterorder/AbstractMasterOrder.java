/**
 * 
 */
package edu.jenks.dist.cb.masterorder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JenksT
 *
 */
public abstract class AbstractMasterOrder {
	
	protected List<CookieOrder> orders;

	/**
	 * <b>postcondition</b>: orders is not <code>null</code>
	 */
	public AbstractMasterOrder() {
		orders = new ArrayList<CookieOrder>();
	}
	
	/**
	 * @return the sum of the number of boxes of all of the cookie orders
	 */
	public abstract int getTotalBoxes();
	
	/**
	 * Removed all orders of variety <code>cookieVar</code>.<br /> 
	 * Removes all cookie orders from the master order that have the same variety
	 * of cookie as <code>cookieVar</code> and returns the total number of boxes
	 * that were removed. 
	 * @param cookieVar the variety of cookies to remove from the master order
	 * @return the total number of boxes of cookieVar in the cookie orders removed
	 */
	public abstract int removeVariety(String cookieVar);

	/**
	 * @return
	 */
	public List<CookieOrder> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 */
	public void setOrders(List<CookieOrder> orders) {
		this.orders = orders;
	}

}
