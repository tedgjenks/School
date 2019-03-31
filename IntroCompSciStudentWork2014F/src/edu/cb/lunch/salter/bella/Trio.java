package edu.cb.lunch.salter.bella;
import edu.jenks.dist.cb.lunch.*;
public class Trio extends AbstractTrio implements MenuItem {
    public static void main(String[] args) {
        System.out.println("Begin test of Trio");
        // begin test of getName
        Trio testTrio = new Trio(new Sandwich("testSandwich", 1.00), new Salad("testSalad", 1.00), new Drink("testDrink", 1.50));
        assert testTrio.getName().equals("testSandwich/testSalad/testDrink Trio") : "Error: method getName returning " + testTrio.getName();
        
        System.out.println("End test of Trio with no errors");
    }
    public Trio(Sandwich sandwich, Salad salad, Drink drink) {
        super(sandwich, salad, drink);
    }
    public String getName() {
        return getSandwich().getName() + "/" + getSalad().getName() + "/" + getDrink().getName() + " Trio";
    }
    public double getPrice(){
        double sandwichPrice = getSandwich().getPrice();
        double saladPrice = getSalad().getPrice();
        double drinkPrice = getDrink().getPrice();
        if(sandwichPrice <= drinkPrice && sandwichPrice <= saladPrice) {
            return drinkPrice + saladPrice;
        }else if(drinkPrice <= sandwichPrice && drinkPrice <= saladPrice) {
            return sandwichPrice + saladPrice;
        } else {
            return sandwichPrice + drinkPrice;
        }
    }
}
