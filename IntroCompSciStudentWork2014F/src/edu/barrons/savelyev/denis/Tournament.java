package edu.barrons.savelyev.denis;
import edu.jenks.dist.barrons.tournament.*;

public class Tournament extends AbstractTournament{
	
	public static void main(String[] args) {
		Player[] arr = new Player[5];
		Tournament test = new Tournament(arr);
		System.out.println(test.requestSlot("PLAYER0"));
		System.out.println(test.requestSlot("PLAYER1"));
		System.out.println(test.requestSlot("PLAYER2"));
		System.out.println(test.requestSlot("PLAYER3"));
		System.out.println(test.requestSlot("PLAYER4"));
		System.out.println(test.cancelAndReassignSlot(new Player("PLAYER2", 2)));
	}
	
	public Tournament(Player[] slots) {
		super(slots);
	}

	public Player cancelAndReassignSlot(Player p) {
		if(getWaitingList() != null) {
			if(!getWaitingList().isEmpty()) {
				String newPlayer = getWaitingList().get(0);
				slots[p.getPlayerNumber()] = new Player(newPlayer, p.getPlayerNumber());
				return new Player(p.getName(), p.getPlayerNumber());
			} else {
				slots[p.getPlayerNumber()] = null;
				return null;
			}
		}
		return null;
	}

	public Player requestSlot(String playerName) {
		for(int x = 0; x < slots.length; x++) {
			if(slots[x] == null) {
				slots[x] = new Player(playerName, x);
				return new Player(playerName, x);
			}
		}
		return null;
	}
	
	
}
