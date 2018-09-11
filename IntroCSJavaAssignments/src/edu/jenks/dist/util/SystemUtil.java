/**
 * 
 */
package edu.jenks.dist.util;

/**
 * @author Jenks
 *
 */
public final class SystemUtil {

	/**
	 * 
	 */
	private SystemUtil() {}
	
	/**
	 * @return the system-dependent line separator string.
	 */
	public static String lineSeparator() {
		return System.lineSeparator();
	}
	
	/**
	 * @return the current time in milliseconds.
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}

}
