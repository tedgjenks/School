package edu.barrons.burroughs.trent;
import edu.jenks.dist.barrons.tournament.*;

public class Tournament extends AbstractTournament
{
    
    public Tournament(Player[] slots){
        super(slots);
    }
    
    public Player requestSlot(String playerName){
        Player newPlayer;
        for(int i = 0; i < slots.length; i++){
            if(slots[i] == null){
                newPlayer = new Player(playerName, i);
                slots[i] = newPlayer;
                return newPlayer;
            }
        }
        waitingList.add(playerName);
        return null;
    }
    
    public Player cancelAndReassignSlot(Player p){
        for(int i = 0; i < slots.length; i ++){
            if(slots[i] != null && slots[i].getName().equals(p.getName())){
                if(waitingList.size() > 0){
                    Player newPlayer = new Player(waitingList.get(0), i);
                    slots[i] = newPlayer;
                    waitingList.remove(0);
                    return newPlayer;
                } else {
                    slots[i] = null;
                    return null;
                }
            }
        }
        return null;
    }
}