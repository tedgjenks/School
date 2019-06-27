package edu.cb.masterorder.burroughs.trent;
import edu.jenks.dist.cb.masterorder.*;
import java.util.*;

public class MasterOrder extends AbstractMasterOrder{
    
    public MasterOrder(){
        super();
    }
    
    public int getTotalBoxes(){
        int sum = 0;
        for(int i = 0; i < getOrders().size(); i++){
            sum+= getOrders().get(i).getNumBoxes();
        }
        return sum;
    }
    
    public int removeVariety(String cookieVar){
        int rev = 0;
        for(int i = 0; i < getOrders().size(); i++){
            if(getOrders().get(i).getVariety() == cookieVar){
                rev += getOrders().get(i).getNumBoxes();
                getOrders().remove(i);
                i--;
            }
        }
        return rev;
    }
    
    public Map<String,Integer > getTotalBoxesByVariety(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < getOrders().size(); i++){
            if(map.containsKey(getOrders().get(i).getVariety())){
                continue;
            }
            map.put(getOrders().get(i).getVariety(), getVar(getOrders().get(i).getVariety()));
        }
        return map;
    }
    
    public int getVar(String cookieVar){
        int rev = 0;
        for(int i = 0; i < getOrders().size(); i++){
            if(getOrders().get(i).getVariety().equals (cookieVar)){
                rev += getOrders().get(i).getNumBoxes();
            }
        }
        return rev;
    }
}
