package edu.cb.masterorder.creswell.jasmine;
import edu.jenks.dist.cb.masterorder.*;
import java.util.*;

public class MasterOrder extends AbstractMasterOrder
{
   public MasterOrder() {
       super();
   }
   public int getTotalBoxes() {
       int boxes=0;
       for ( CookieOrder box: getOrders()){
            boxes+= box.getNumBoxes();
        }
       return boxes;
   }
   public int removeVariety(String cookieVar) {
       int boxes=0;
       for (int i=0; i<getOrders().size(); i++) {
           if (getOrders().get(i).getVariety()== cookieVar) {
               boxes+=getOrders().get(i).getNumBoxes();
               getOrders().remove(i);
               
            } 
        }
        return boxes;
               
   }
   public Map<String,Integer> getTotalBoxesByVariety() {
       int numCookies;
       Map<String, Integer> cookies = new HashMap<String, Integer>();
       for (CookieOrder box: getOrders()) {
           if(cookies.containsKey(box.getVariety())) {
               numCookies=cookies.get(box.getVariety());
               cookies.replace(box.getVariety(),numCookies+box.getNumBoxes());
           } else{
               cookies.put(box.getVariety(), box.getNumBoxes());
           }   
       }
       return cookies;
   }
}
