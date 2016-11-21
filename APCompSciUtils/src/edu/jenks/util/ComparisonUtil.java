/**
 * 
 */
package edu.jenks.util;

/**
 * @author Ted Jenks
 *
 */
public class ComparisonUtil {

	public static boolean equalAllowNull(Object o1, Object o2) {
		return (o1 == null && o2 == null) || (o1 != null && o1.equals(o2));
	}
}
