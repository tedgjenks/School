/**
 * 
 */
package edu.jenks.dist.util;

/**
 * @author Jenks
 *
 */
public abstract class MapHelper {

	/**
	 * @param o - compute a hash value for this
	 * @return the hash value for the input
	 */
	public static final int hashFunction(Object o) {
		return o.hashCode();
	}
}
