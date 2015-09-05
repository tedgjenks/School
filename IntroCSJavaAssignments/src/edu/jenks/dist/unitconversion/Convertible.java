/**
 * 
 */
package edu.jenks.dist.unitconversion;

/**
 * Convertible is used to convert metric units of length.<br>
 * The following metric units are supported:<br>
 * - nm (nanometer)
 * - um (micrometer)
 * - mm (millimeter)
 * - cm (centimeter)
 * - dm (decimeter)
 * - dam (decameter)
 * - hm (hectometer)
 * - km (kilometer)
 * - Mm (megameter)
 * - Gm (gigameter)
 * 
 * @author Ted Jenks
 *
 */
public interface Convertible {
	
	/**
	 * <p>Convert <code>numberUnits</code> of <code>unit</code> to meters.</p>
	 * <b>Preconditions</b>:<br>
	 * - <code>unit</code> is a valid unit
	 * @param numberUnits
	 * @param unit
	 * @return
	 */
	double convertToMeters(double numberUnits, String unit);
	
	/**
	 * <p>Convert <code>numberMeters</code> of meters to <code>unit</code>.</p>
	 * <b>Preconditions</b>:<br>
	 * - <code>unit</code> is a valid unit
	 * @param numberMeters
	 * @param unit
	 * @return
	 */
	double convertFromMeters(double numberMeters, String unit);
	
	/**
	 * <p>Convert <code>numberStartingUnits</code> of <code>startingUnit</code> to <code>desiredUnit</code>.</p>
	 * <b>Preconditions</b>:<br>
	 * - <code>startingUnit</code> is a valid unit
	 * - <code>desiredUnit</code> is a valid unit
	 * @param numberStartingUnits
	 * @param startingUnit
	 * @param desiredUnit
	 * @return
	 */
	double convert(double numberStartingUnits, String startingUnit, String desiredUnit);
	
	/**
	 * <p>Convert <code>numberStartingUnits</code> of <code>startingUnit</code> to <code>desiredUnit</code>.</p>
	 * A String with the following format is returned:<br>
	 * <code>numberStartingUnits</code> <code>startingUnit</code> = returned_value <code>desiredUnit</code>.<br>
	 * If <code>startingUnit</code> or <code>desiredUnit</code> is not valid, return: Unit not supported.<br>
	 * @param numberStartingUnits
	 * @param startingUnit
	 * @param desiredUnit
	 * @return 
	 */
	String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit);
}
