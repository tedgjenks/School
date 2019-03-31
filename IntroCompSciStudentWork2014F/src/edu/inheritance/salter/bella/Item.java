package edu.inheritance.salter.bella;
import edu.jenks.dist.inheritance.*;
public abstract class Item implements Buyable
{
    private boolean bulkItem;
    private double weightItem;
    public static void main(String[] args) {
        SelfCheckout test = new SelfCheckout(0.07);
    }
    public Item(boolean bulk) {
        weightItem= this.getWeight();
        bulkItem = bulk;
    }
    public double getWeight() {
        return weightItem;
    }
    public boolean isBulk() {
        return bulkItem;
    }
    public void setWeight(double weight) {
        weightItem = weight;
    }
    public void setBulk(boolean bulk) {
        bulkItem = bulk;
    }
}
