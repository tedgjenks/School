package edu.barrons.newbold.griffin;
import edu.jenks.dist.barrons.tournament.*;

public class Tournament extends AbstractTournament{
	private Player[] slots;
	
	public Tournament(Player[] slots){
		super(slots);
		this.slots = slots;
	}
	
	public Player requestSlot(String playerName){
		int defaultIndex = slots.length;
		for(int i = 0; i < slots.length; i++){
			if(slots[i] == null){
				Player insert = new Player(playerName, i);
				slots[i] = insert;
				return insert;
			}
		}
		waitingList.add(playerName);
		return null;
	}
	
	public Player cancelAndReassignSlot(Player p){
		for(int k = 0; k < slots.length; k++){
			if(slots[k] != null){
				if(slots[k].getName().equals(p.getName())){
					slots[k] = null;
					if(waitingList.isEmpty()){
						return null; 
					}else{
						Player newInsert = new Player(waitingList.get(0), k);
						slots[k] = newInsert;
						waitingList.remove(0);
						return newInsert;
					}
				}
			}
			
		}
		return null;
	}










}