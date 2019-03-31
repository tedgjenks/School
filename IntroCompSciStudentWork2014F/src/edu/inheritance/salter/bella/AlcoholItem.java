package edu.inheritance.salter.bella;
import edu.jenks.dist.inheritance.*;
public class AlcoholItem extends BarcodeItem implements SinTaxable {
    private double sinTaxRate;
    private boolean bulk;
    private double weight;
    private double price;
    public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate) {
       super(bulk,weight,price);
       this.bulk = bulk;
       this.weight = weight;
       this.price = price;
       this.sinTaxRate = sinTaxRate;
       
    }
    public double getTax(double baseTaxRate) {
        return (price * baseTaxRate) + (price * sinTaxRate);
    }
    public boolean initBuyable(ItemHandler itemHandler) {
        itemHandler.setCheckID(true);
        return true;
    }
    public double getSinTaxRate() {
        return sinTaxRate;
    }
    public void setSinTaxRate(double sinTaxR) {
        sinTaxRate = sinTaxR;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double newPrice) {
        price = newPrice;
    }
    public void setBulk(boolean isBulk) {
        bulk = isBulk;
    }
    public boolean isBulk() {
        return bulk;
    }
    public void setWeight(double newWeight) {
        weight = newWeight;
    }
    public double getWeight() {
        return weight;
    }
}
