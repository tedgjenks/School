/**
 * 
 */
package edu.jenks.dist.barrons.tournament;

import java.util.LinkedList;
import java.util.List;

/**
 * There are 100 available slots for players in the tournament, and the players are numbered 0, 1, 2, ..., 99.
 * @author Ted Jenks
 */
public abstract class AbstractTournament {
	
	/**
	 * The list of slots in the tournament.<br>
	 * Each element corresponds to a slot in the tournament.<br>
	 * If <code>slots[i]</code> is <code>null</code>, the slot is not yet taken;<br>
	 * otherwise it contains a reference to a <code>Player</code>.<br>
	 * For example, <code>slots[i].getPlayerNumber()</code> returns <code>i</code>.
	 */
	protected Player[] slots;
	
	/**
	 * The list of names of players who wish to participate in<br>
	 * the tournament, but cannot because all slots are taken.
	 */
	protected List<String> waitingList = new LinkedList<String>();

	/**
	 * <b>Precondition:</b> <code>slots.length</code> is 100.<br>
	 * <b>Postcondition:</b> <code>waitingList</code> is not null
	 * @param slots
	 */
	public AbstractTournament(Player[] slots) {
		this.slots = slots;
	}
	
	/**
	 * If there are any empty slots (slots with no <code>Player</code>)<br>
	 * assign the player with the specified <code>playerName</code> to the first<br>
	 * empty slot. Create and return the new <code>Player</code>.<br>
	 * If there are not available slots, add the player's name<br>
	 * to the end of the waiting list and return <code>null</code>.<br>
	 * <b>Precondition:</b> <code>playerName</code> is not <code>null</code>.
	 * @param playerName the name of the person requesting a slot
	 * @return the new <code>Player</code>
	 */
	public abstract Player requestSlot(String playerName);
	
	/**
	 * Release the slot for player <code>p</code>, thus removing that player<br>
	 * from the tournament. If there are any names in <code>waitingList</code>,<br>
	 * remove the first name and create a <code>Player</code> in the<br>
	 * canceled slot for the this person. Return the new <code>Player</code>.<br>
	 * If <code>waitingList</code> is empty, mark the slot specified by <code>p</code> as<br>
	 * empty and return <code>null</code>.<br>
	 * <b>Precondition:</b> <code>p</code> is a valid <code>Player</code> for some slot in this tournament.
	 * @param p the player who will be removed from the tournament
	 * @return the new <code>Player</code> placed in the canceled slot
	 */
	public abstract Player cancelAndReassignSlot(Player p);

	/**
	 * @return
	 */
	public Player[] getSlots() {
		return slots;
	}

	/**
	 * @return
	 */
	public List<String> getWaitingList() {
		return waitingList;
	}
}
