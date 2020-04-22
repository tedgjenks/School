package edu.barrons.sweezy.kenneth;

import edu.jenks.dist.barrons.tournament.AbstractTournament;
import edu.jenks.dist.barrons.tournament.Player;

public class Tournament extends AbstractTournament {

	public Tournament(Player[] slots) {
		super(slots);
	}

	public Player cancelAndReassignSlot(Player arg0) {
		return null;
	}

	public Player requestSlot(String pName) {
		int pNumber = 0;
		Player[] tempArr = getSlots();
		for (int i = 0; i < tempArr.length; i++) {
			pNumber = i;
			if (tempArr[i] == null) {
				tempArr[i] = new Player(pName, pNumber);
				return tempArr[i];
			}
		}
		return new Player(pName, pNumber);
	}

	public static void main(String[] args) {

	}

}
