/**
 * 
 */
package edu.jenks.dist.barrons.tournament;

/**
 * @author Ted Jenks
 *
 */
public class Player {
	private String name;
	private int playerNumber;

	/**
	 * @param name
	 * @param playerNumber not directly tested
	 */
	public Player(String name, int playerNumber) {
		this.name = name;
		this.playerNumber = playerNumber;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	@Override
	public boolean equals(Object arg0) {
		Player argPlayer = (Player)arg0;
		return argPlayer != null && name.equals(argPlayer.getName()) && playerNumber == argPlayer.getPlayerNumber();
	}

	@Override
	public String toString() {
		return name + " at slot " + playerNumber;
	}
}
