package edu.barrons.salter.bella;
import edu.jenks.dist.barrons.tournament.*;
import java.util.*;
public class Tournament extends AbstractTournament
{
   
    public Tournament(Player[] slots)
    {
         super(slots);
    }
    public Player requestSlot(String playerName) {
        for(int i = 0; i < slots.length; i++) {
            if(slots[i]== null) {
                Player newP = new Player(playerName, i);
                slots[i] = newP;
                return newP;
            }
        }
        waitingList.add(playerName);
        return null;
    }
    public Player cancelAndReassignSlot(Player p) {
        int playerNum = p.getPlayerNumber();
        if(waitingList.size() == 0) {
            slots[playerNum] = null;
            return null;
        }
        Player newP = new Player(waitingList.get(0),playerNum);
        waitingList.remove(0);
        slots[playerNum] = newP;
        return newP;
    }
}
