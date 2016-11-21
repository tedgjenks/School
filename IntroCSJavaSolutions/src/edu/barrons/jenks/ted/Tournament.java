/**
 * 
 */
package edu.barrons.jenks.ted;

import edu.jenks.dist.barrons.tournament.AbstractTournament;
import edu.jenks.dist.barrons.tournament.Player;

/**
 * @author Ted Jenks
 *
 */
public class Tournament extends AbstractTournament {

	/**
	 * @param slots
	 */
	public Tournament(Player[] slots) {
		super(slots);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.tournament.AbstractTournament#requestSlot(java.lang.String)
	 */
	@Override
	public Player requestSlot(String playerName) {
		Player newPlayer = null;
		for(int index = 0; newPlayer == null && index < slots.length; index++) {
			if(slots[index] == null) {
				newPlayer = new Player(playerName, index);
				slots[index] = newPlayer;
			}
		}
		
		if(newPlayer == null)
			waitingList.add(playerName);
		
		return newPlayer;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.tournament.AbstractTournament#cancelAndReassignSlot(edu.jenks.dist.barrons.tournament.Player)
	 */
	@Override
	public Player cancelAndReassignSlot(Player p) {
		int index = p.getPlayerNumber();
		Player waitingPlayer = waitingList.size() > 0 ? new Player(waitingList.remove(0), index) : null;
		slots[index] = waitingPlayer;
		return waitingPlayer;
	}

}
