package edu.jenks.comparison;

import edu.jenks.comparison.dist.AbstractWeapon;

/**
 * @author Ted Jenks
 *
 */
public class Weapon extends AbstractWeapon {
	
	public Weapon(String name, int power) {
		super();
		setName(name);
		setPower(power);
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		String name = getName();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + getPower();
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @return true if name and power are the same, otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weapon other = (Weapon) obj;
		String name = getName(), otherName = other.getName();
		if (name == null) {
			if (otherName != null)
				return false;
		} else if (!name.equals(otherName))
			return false;
		if (getPower() != other.getPower())
			return false;
		return true;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * @return name [space] power
	 */
	@Override
	public String toString() {
		return getName() + " " + getPower();
	}
	
	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		return new Weapon(getName(), getPower());
	}
	
}
