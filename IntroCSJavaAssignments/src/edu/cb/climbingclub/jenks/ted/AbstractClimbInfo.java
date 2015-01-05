/**
 * 
 */
package edu.cb.climbingclub.jenks.ted;

/**
 * @author Ted
 *
 */
public abstract class AbstractClimbInfo implements Comparable<AbstractClimbInfo> {
	
	private String peakName;
	private int climbTime;

	/** Creates a <code>ClimbInfo</code> object with name <code>peakName</code> and time <code>climbTime</code>. 
	 * @param peakName the name of the mountain peak
	 * @param climbTime the number of minutes taken to complete the climb
	 */
	public AbstractClimbInfo(String peakName, int climbTime) {
		this.peakName = peakName;
		this.climbTime = climbTime;
	}
	
	/**
	 * @return the name of the mountain peak
	 */
	public String getPeakName() {
		return peakName;
	}

	/**
	 * @return the number of minutes taken to complete the climb
	 */
	public int getClimbTime() {
		return climbTime;
	}
}
