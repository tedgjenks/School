package edu.barrons.rhodes.maddux;

import edu.jenks.dist.barrons.tournament.AbstractTournament;
import edu.jenks.dist.barrons.tournament.Player;

public class Tournament extends AbstractTournament {

	public Tournament(Player[] slots) {
		super(slots);
	}

	public Player cancelAndReassignSlot(Player p) {
		Player[] slots2 = getSlots();
		if (getWaitingList().size() != 0) {
			Player toBeAdded = new Player(getWaitingList().get(0), p.getPlayerNumber());
			getWaitingList().remove(0);
			slots2[p.getPlayerNumber()] = toBeAdded;
			return toBeAdded;
		}else {
			slots2[p.getPlayerNumber()] = null;
		}
		return null;
	}

	public Player requestSlot(String playerName) {
		Player[] slots2 = getSlots();
		if (playerName != null) {
			for (int i = 0; i < slots2.length; i++) {
				if (slots2[i] == null) {
					Player toBeAdded = new Player(playerName, i);
					slots2[i] = toBeAdded;
					return toBeAdded;
				}
			}
			getWaitingList().add(playerName);
		}
		return null;
	}

}
