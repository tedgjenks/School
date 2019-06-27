package edu.cb.lunch.gabriel.mitchell;
import java.util.*;
import edu.jenks.dist.cb.lunch.*;

public class Trio extends AbstractTrio{
    public Trio(Sandwich sandwich, Salad salad, Drink drink){
        super(sandwich,salad,drink);
    }
    public String getName(){
        return getSandwich().getName()+"/"+ getSalad().getName() + "/" + getDrink().getName() + " Trio";
    }
    public double getPrice(){
        double sandPrice = getSandwich().getPrice();
        double salPrice = getSalad().getPrice();
        double drPrice = getDrink().getPrice();
        if (sandPrice <= salPrice && sandPrice <= drPrice){
            return drPrice + salPrice;
        } else if (salPrice <= sandPrice && salPrice <=drPrice){
            return drPrice + sandPrice;
        } else {
            return sandPrice + salPrice;
        }
}}