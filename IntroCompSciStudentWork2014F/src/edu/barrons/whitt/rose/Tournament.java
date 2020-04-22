package edu.barrons.whitt.rose;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.barrons.tournament.AbstractTournament;
import edu.jenks.dist.barrons.tournament.Player;

public class Tournament extends AbstractTournament {
	Player[] slot;
//	List<String> waitingList;
	
	public static void main(String[] args) {
		Tournament t = new Tournament(new Player[0]);
		t.waitingList.add("hi");
		t.requestSlot("Rose");
		System.out.println(t.getWaitingList());
	}
	
	public Tournament(Player[] slots) {
		super(slots);
		slot = slots;
	}
	
	public Player requestSlot(String playerName) {
		//If there are any empty slots (slots with no Player)
		int first = -1;
		for (int i = 0; i < slot.length; i++) {
			if (slot[i] == null) {
				if (first == -1) {
					first = i;
				}
			}
		}
		
		if (first == -1) {
			getWaitingList().add(playerName);
			return null;
		}
		
		//assign the player with the specified playerName to the first
		//empty slot.
		//Create and return the new Player.
		Player p = new Player(playerName, first);
		slot[first] = p;
		return p;
	}
	
	public Player cancelAndReassignSlot(Player p) {
		//find index
		int index = -1;
		for (int i = 0; i < slot.length; i++) {
			if (slot[i] != null) {
				if (slot[i].equals(p)) {
					index = i;
				}
			}
		}
	
		//remove
		slot[index] = null;
		
		if(getWaitingList().size() > 0) {
			Player rep = new Player(getWaitingList().get(0), index);
			getWaitingList().remove(0);
			slot[index] = rep;
			return rep;
		}
		return null;
	}
	
//	public List<String> getWaitingList() {
//		return waitingList;
//	}
}
