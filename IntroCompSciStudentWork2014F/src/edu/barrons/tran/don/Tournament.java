package edu.barrons.tran.don;
import edu.jenks.dist.barrons.tournament.*;

public class Tournament extends AbstractTournament{
	/*
	public static void main(String args[]) {
		Player[] p = new Player[] {new Player("a", 0), new Player("b", 1), new Player("c", 2) };
		System.out.println(p[0]);
		Tournament t = new Tournament(p);
		t.cancelAndReassignSlot(new Player("a", 0));
	}
	*/
	//Player [] what;
	public Tournament(Player[] slots) {
		super(slots);
		//what = slots;
		// TODO Auto-generated constructor stub
	}


	public Player cancelAndReassignSlot(Player arg0) {
		Player[] players = getSlots();
		Player p = null;
		for(int i = 0; i < getSlots().length; i++) {
			if(getSlots()[i] == null) {
				continue;
			}
			if(getSlots()[i].equals(arg0)) {
				getSlots()[i] = null;
				if(getWaitingList().size() == 0) {
					return null;
				}
				if(getWaitingList().size() > 0) {
					p = new Player(getWaitingList().get(0), i);
					getWaitingList().remove(0);
					getSlots()[i] = p;
					return p;
				}
			}
		}
		return null;
	}


	public Player requestSlot(String playerName) {
		Player[] players = getSlots();
		Player player = null;
		for(int i = 0; i < players.length; i++) {
			if(players[i] == null) {
				player = new Player(playerName, i);
				players[i] = player;
				return player;
			}
		}
		getWaitingList().add(playerName);
		return null;
	}



}
