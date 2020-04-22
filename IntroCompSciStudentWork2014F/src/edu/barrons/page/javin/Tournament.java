package edu.barrons.page.javin;
import edu.jenks.dist.barrons.tournament.*;
public class Tournament extends AbstractTournament{

	public Tournament(Player[] slots) {
		super(slots);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Player cancelAndReassignSlot(Player arg0) {
		// TODO Auto-generated method stub
		int index = 0;
		for(int i =0 ; i < getSlots().length; i++) {
			if(getSlots()[i].equals(arg0)) {
				index = i;
				getSlots()[i] = null;
				break;
			}
		}
		if(getWaitingList().size() == 0) return null;
		Player p = new Player(getWaitingList().get(0), index);
		getSlots()[index] = p;
		return p;
	}

	@Override
	public Player requestSlot(String arg0) {
		
		for(int i = 0; i < getSlots().length; i++) {
			if(getSlots()[i] == null) {
				Player newMan = new Player(arg0, i);
				getSlots()[i] = newMan;
				return newMan;
			}
		}		
		getWaitingList().add(arg0);
		return null;
	}

}
