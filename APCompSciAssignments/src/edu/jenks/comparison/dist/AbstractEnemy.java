/**
 * 
 */
package edu.jenks.comparison.dist;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractEnemy implements Comparable<AbstractEnemy> {

	private String name;
	private int rank;
	private AbstractWeapon weapon;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank >= 0 ? rank : 0;
	}
	public AbstractWeapon getWeapon() {
		return weapon;
	}
	public void setWeapon(AbstractWeapon weapon) {
		this.weapon = weapon;
	}
	
	@Override
	public abstract Object clone();

}
