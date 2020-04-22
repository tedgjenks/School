package edu.barrons.hollingsworth.james;

import edu.jenks.dist.barrons.tournament.AbstractTournament;
import edu.jenks.dist.barrons.tournament.Player;

public class Tournament extends AbstractTournament {

	public Tournament(Player[] slots) {
		super(slots);
	}
	
	public Tournament() {
		super(new Player[0]);
	}

	public Player cancelAndReassignSlot(Player p) {
		Player[] slots = getSlots();
		for(int i = 0; i < slots.length; i++) {
			if(slots[i].equals(p)) {
				slots[i] = null;
				if(getWaitingList().size() > 0) {
					Player player = new Player(getWaitingList().remove(0), i);
					slots[i] = player;
					return player;
				}
			}
		}
		return null;
	}

	public Player requestSlot(String playerName) {
		Player[] slots = getSlots();
		for(int i = 0; i < slots.length; i++) {
			if(slots[i] == null) {
				Player player = new Player(playerName, i);
				slots[i] = player;
				return player;
			}
		}
		this.getWaitingList().add(playerName);
		return null;
	}

	public static void main(String[] args) {
		Tournament t = new Tournament(new Player[1]);
		System.out.println(t.getSlots()[0]);
		t.requestSlot("James");
		System.out.println(t.getSlots()[0]);
		t.requestSlot("Chad");
		System.out.println(t.getSlots()[0]);
		t.cancelAndReassignSlot(t.getSlots()[0]);
		System.out.println(t.getSlots()[0]);
	}

}
