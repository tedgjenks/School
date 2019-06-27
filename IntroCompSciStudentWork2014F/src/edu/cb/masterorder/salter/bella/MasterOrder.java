package edu.cb.masterorder.salter.bella;
import java.util.*;
import edu.jenks.dist.cb.masterorder.*;
public class MasterOrder extends AbstractMasterOrder {
    public MasterOrder() {
    }
    public int getTotalBoxes() {
        int count = 0;
        for(int i = 0; i < getOrders().size(); i++) {
            count = count += getOrders().get(i).getNumBoxes();
        }
        return count;
    }
    public int removeVariety(String cookieVar) {
        int count = 0;
        for(int i = 0; i < getOrders().size(); i++) {
            if(getOrders().get(i).getVariety().equals(cookieVar)){
                count = count + getOrders().get(i).getNumBoxes();
                getOrders().remove(i);
            }
        }
        return count;
    }
    public Map<String, Integer> getTotalBoxesByVariety() {
        Map<String, Integer> map = new HashMap<>();
        for(CookieOrder order : getOrders()){
            if(map.containsKey(order.getVariety())){
                int numBoxes = map.get(order.getVariety());
                map.replace(order.getVariety(), numBoxes += order.getNumBoxes());
            }else{
                map.put(order.getVariety(), order.getNumBoxes());
            }
        }
        return map;
    }
}