package edu.cb.masterorder.gabriel.mitchell;
import edu.jenks.dist.cb.masterorder.*;
import java.util.*;
public class MasterOrder extends AbstractMasterOrder{
    public MasterOrder(){
    }
    public int getTotalBoxes(){
        int sum = 0;
        for (CookieOrder cookies : getOrders()){
            sum += cookies.getNumBoxes();
        }
        return sum;
    }
    public int removeVariety(String cookieVar){
        int removed = 0;
        for( int i = 0; i < getOrders().size(); i++){
            if(getOrders().get(i).getVariety().equals(cookieVar)){
                removed += getOrders().get(i).getNumBoxes();
                getOrders().remove(i);
            }
        }
        return removed;
    }
    public Map<String,Integer> getTotalBoxesByVariety(){
        HashMap<String,Integer> byVar = new HashMap<String, Integer>();
        for(int i = 0; i < getOrders().size(); i++){
            String variety = getOrders().get(i).getVariety();
            if(byVar.containsKey(variety)){
                byVar.replace(variety,byVar.get(variety) + getOrders().get(i).getNumBoxes());
            } else {
                byVar.put(getOrders().get(i).getVariety(), getOrders().get(i).getNumBoxes());
            }
        }
        return byVar;
    }
}
