package edu.barrons.gabriel.mitchell;
import edu.jenks.dist.barrons.tournament.*;
import java.util.*;
public class Tournament extends AbstractTournament{
    List<String> waitingList = new ArrayList<String>();
    public Tournament(Player[] slots){
        super(slots);
        waitingList = getWaitingList();
    }

    public Player requestSlot(String playername){
        for (int i = 0; i < slots.length; i++){
            if (slots[i] == null){
                Player newPlayer = new Player(playername, i);
                slots[i] = newPlayer;
                return newPlayer;
            }
        }
        waitingList.add(playername);
        return null;
    }

    public Player cancelAndReassignSlot(Player p){
        boolean done = false;
        int i;
        for(i = 0; i < slots.length; i++){ 
            if(slots[i].equals(p)){
                int tempInd = i;
                for(int x = 0; x < waitingList.size()-1; x++){
                    if (x+1 > waitingList.size() || x < 0){
                        throw new IndexOutOfBoundsException("Hello friend");
                    }
                    if(waitingList.get(x) != null){
                        slots[i] = new Player(waitingList.get(x),i);
                        waitingList.set(x,waitingList.get(x+1));
                        waitingList.remove(x+1);
                        done = true;
                        return slots[i];
                    }
                }
                if (!done){
                    slots[i] = null;
                    return slots[i];
                }
            }
        }
        return null;
    }
}