//TODO - use line separator
/**
 * 
 */
package edu.jenks.dist.student;

import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
public class Address {
	private String streetAddress, city, state;
	private int zipCode;
	
	/**
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zipCode
	 */
	public Address(String streetAddress, String city, String state, int zipCode) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null || !getClass().equals(arg0.getClass()))
			return false;
		Address argAddress = (Address)arg0;
		return StringUtil.equalAllowNull(streetAddress, argAddress.streetAddress) && 
				StringUtil.equalAllowNull(city, argAddress.city) &&
				StringUtil.equalAllowNull(state, argAddress.state) &&
				zipCode == argAddress.zipCode;
	}

	/**
	 * <code>streetAddress</code><br>
	 * <code>city</code>, <code>state</code> <code>zipCode</code>
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(30);
		sb.append(streetAddress).append("\n").append(city).append(", ").append(state);
		sb.append(" ").append(zipCode);
		return sb.toString();
	}
}
