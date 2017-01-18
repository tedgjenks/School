package edu.jenks.dist.inheritance;

/**
 * <code>SinTaxable</code> are subject to a tax in addition to the standard tax rate.<br>
 * The final tax rate is calculated by adding the standard tax rate to the sin tax rate.<br>
 * Note that the sin tax can vary by item.
 * 
 * @author Ted Jenks
 */
public interface SinTaxable extends Barcoded {

	/**
	 * @return
	 */
	double getSinTaxRate();
	
	/**
	 * @param sinTaxRate
	 */
	void setSinTaxRate(double sinTaxRate);
}
