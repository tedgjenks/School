/**
 * 
 */
package edu.jenks.dist.cb.climbingclub;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ted
 *
 */
public abstract class AbstractClimbingClub {

	/** The list of climbs completed by members of the club. <br />
	 * Guaranteed not to be <code>null</code>. Contains only non-<code>null</code> references.
	 */
	protected List<AbstractClimbInfo> climbList = new ArrayList<AbstractClimbInfo>();

	/**
	 * 
	 */
	public AbstractClimbingClub() {}

	/** Adds a new climb with name <code>peakName</code> and time <code>climbTime</code> to the list of climbs.
	 * @param peakName the name of the mountain peak climbed
	 * @param climbTime the number of minutes taken to complete the climb
	 */
	public abstract void addClimb(String peakName, int climbTime);

	/** 
	 * @return the number of distinct names in the list of climbs 
	 */
	public abstract int distinctPeakNames();

	/**
	 * @return the climbList
	 */
	public List<AbstractClimbInfo> getClimbList() {
		return climbList;
	}

	/**
	 * @param climbList the climbList to set
	 */
	public void setClimbList(List<AbstractClimbInfo> climbList) {
		this.climbList = climbList;
	}
}
