package edu.jenks.comparison.dist;

public abstract class AbstractWeapon {

	private String name;
	private int power;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	
	@Override
	public abstract Object clone();
}
