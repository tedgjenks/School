package edu.cb.lunch.burroughs.trent;
import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio
{
    private Sandwich sand;
    private Salad sal;
    private Drink dr;
    
    public Trio(Sandwich sandwich,Salad salad,Drink drink){
        super(sandwich, salad, drink);
        sand = sandwich;
        sal = salad;
        dr = drink;
    }
    
    public String getName(){
        return sand.getName() + "/" + sal.getName() + "/" + dr.getName() + " Trio";
    }
    
    public double getPrice(){
        if(sand.getPrice() > dr.getPrice() && sal.getPrice() > dr.getPrice())
            return sand.getPrice() + sal.getPrice();
        if(sand.getPrice() > sal.getPrice() && dr.getPrice() > sal.getPrice())
            return sand.getPrice() + dr.getPrice();
        return dr.getPrice() + sal.getPrice();
    }
}
