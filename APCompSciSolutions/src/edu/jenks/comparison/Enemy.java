package edu.jenks.comparison;

import edu.jenks.comparison.dist.AbstractEnemy;
import edu.jenks.comparison.dist.AbstractWeapon;

/**
 * @author Ted Jenks
 *
 */
public class Enemy extends AbstractEnemy {

	public Enemy(String name, int rank, AbstractWeapon weapon) {
		super();
		setName(name);
		setRank(rank >= 0 ? rank : 0);
		setWeapon(weapon);
	}

	/**
	 * @see java.lang.Object#clone()
	 * @return deep copy
	 */
	@Override
	public Object clone() {
		AbstractWeapon weapon = getWeapon();
		return new Enemy(getName(), getRank(), weapon != null ? (AbstractWeapon)weapon.clone() : null);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		String name = getName();
		int rank = getRank();
		AbstractWeapon weapon = getWeapon();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + rank;
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @return true if name, rank, and weapon are equal, otherwise false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		String name = getName();
		int rank = getRank();
		AbstractWeapon weapon = getWeapon();
		Enemy other = (Enemy) obj;
		String otherName = other.getName();
		if (name == null) {
			if (otherName != null)
				return false;
		} else if (!name.equals(otherName))
			return false;
		if (rank != other.getRank())
			return false;
		AbstractWeapon otherWeapon = other.getWeapon();
		if (weapon == null) {
			if (otherWeapon != null)
				return false;
		} else if (!weapon.equals(otherWeapon))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return name [space] rank "." [2 spaces] "Weapon:" [space] [weapon]
	 * </br>[weapon] is the String representation of Weapon if it is not null, otherwise "none"
	 */
	@Override
	public String toString() {
		AbstractWeapon weapon = getWeapon();
		return getName() + " " + getRank() + ".  Weapon: " + (weapon != null ? weapon.toString() : "none");
	}

	/**
	 * Higher ranks are ordered first.</br>
	 * If ranks are the same, use alphabetical ordering of name.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AbstractEnemy other) {
		int rank = getRank(), otherRank = other.getRank();
		String name = getName(), otherName = other.getName();
		if(rank != otherRank)
			return otherRank - rank;
		else if(name == null && otherName == null)
			return 0;
		else if(name != null)
			return otherName == null ? 1 : name.compareTo(otherName);
		else
			return -1;
	}
}
