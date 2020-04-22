package edu.barrons.macias.marcus;

import java.util.List;


import edu.jenks.dist.barrons.tournament.AbstractTournament;
import edu.jenks.dist.barrons.tournament.Player;

public class Tournament extends AbstractTournament {

	public Tournament(Player[] slots) {
		super(slots);
	}
	
	public Player requestSlot(String playerName) {
		Player[] slots = getSlots();;
		List<String> waitingList = getWaitingList();
		
		for(int i = 0 ; i < slots.length;i++) {
			Player cur = slots[i];
			if(cur == null) {
				Player newPlayer = new Player(playerName,i);
				slots[i] = newPlayer;
				return newPlayer;
			}
		}
		
		getWaitingList().add(playerName);
		return null;
	}

	public Player cancelAndReassignSlot(Player p) {
		Player[] slots = getSlots();;
		List<String> waitingList = getWaitingList();
		for(int i = 0 ; i < slots.length;i++) {
			if(slots[i] == null) {
				continue;
			}
			if(slots[i].equals(p)) {
				slots[i] = null;
				if(waitingList.size()!= 0) {
					String name = waitingList.get(0);
					getWaitingList().remove(0);
					slots[i] = new Player(name,i);
					return slots[i];
				}
			}
		}
		return null;
	}

}
