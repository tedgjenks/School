/**
 * 
 */
package edu.jenks.dist.cb.lunch;

/**
 * @author Ted
 *
 */
public abstract class AbstractTrio implements MenuItem {
	private Sandwich sandwich;
	private Salad salad;
	private Drink drink;

	/**
	 * @param sandwich
	 * @param salad
	 * @param drink
	 */
	public AbstractTrio(Sandwich sandwich, Salad salad, Drink drink) {
		this.sandwich = sandwich;
		this.salad = salad;
		this.drink = drink;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.lunch.MenuItem#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.lunch.MenuItem#getPrice()
	 */
	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}	

	/**
	 * @return the sandwich
	 */
	public Sandwich getSandwich() {
		return sandwich;
	}

	/**
	 * @param sandwich the sandwich to set
	 */
	public void setSandwich(Sandwich sandwich) {
		this.sandwich = sandwich;
	}

	/**
	 * @return the salad
	 */
	public Salad getSalad() {
		return salad;
	}

	/**
	 * @param salad the salad to set
	 */
	public void setSalad(Salad salad) {
		this.salad = salad;
	}

	/**
	 * @return the drink
	 */
	public Drink getDrink() {
		return drink;
	}

	/**
	 * @param drink the drink to set
	 */
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
}
