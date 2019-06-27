package edu.cb.lunch.creswell.jasmine;
import edu.jenks.dist.cb.lunch.*;


public class Trio extends AbstractTrio
{
   private Sandwich sandy; 
   private Salad salady;
   private Drink slurp;
   
   public Trio(Sandwich sandwich, Salad salad, Drink drink)  {
       super(sandwich,salad,drink);
       sandy=sandwich;
       salady=salad;
       slurp=drink;
   }
   public String getName() {
       
       return sandy.getName()+"/"+salady.getName()+"/"+ slurp.getName() + " Trio";
   }
   public double getPrice() {
       double sw=sandy.getPrice();
       double sld=salady.getPrice();
       double d=slurp.getPrice();
       if (sw<sld&& sw<d) {
           return sld+d;
       } else if (sld <sw && sld <d) {
           return sw+d;
       } else {
           return sw+sld;
       }   
   }
    
}
