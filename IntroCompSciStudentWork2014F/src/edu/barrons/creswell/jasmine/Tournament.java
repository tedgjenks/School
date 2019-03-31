package edu.barrons.creswell.jasmine;
import edu.jenks.dist.barrons.tournament.*;
 
public class Tournament extends AbstractTournament 
{
    public Tournament(Player[] slots) {
        super(slots);
    }
    public Player requestSlot(String playerName) {
        Player newPlayer;
        for (int i=0; i< slots.length; i++) {
            if (slots[i]== null) {
                newPlayer= new Player(playerName, i);
                slots[i]= newPlayer;
                return newPlayer;
            }
        }
        waitingList.add(playerName);
        return null;
    }
    public Player cancelAndReassignSlot(Player p) {
        int playerIndex=0;
        for (int i=0; i< slots.length; i++) {
            if(slots[i]!=null) {
                if (slots[i].getName().equals(p.getName())) {
                    playerIndex=i;
                }
            }
        }
        if (waitingList.size()==0) {
            slots[playerIndex] = null;
            return null;
        } else {
            Player newPlayer = new Player(waitingList.get(0), playerIndex);
            slots[playerIndex] = newPlayer;
            waitingList.remove(0);
            return newPlayer;
        }
    }
    
}
