package edu.cb.lunch.hines.jonathan;

import edu.jenks.dist.cb.lunch.AbstractTrio;
import edu.jenks.dist.cb.lunch.Drink;
import edu.jenks.dist.cb.lunch.MenuItem;
import edu.jenks.dist.cb.lunch.Salad;
import edu.jenks.dist.cb.lunch.Sandwich;

public class Trio extends AbstractTrio implements MenuItem 
{

	public Trio(Sandwich sandwich, Salad salad, Drink drink) 
	{
		super(sandwich, salad, drink);
	}

	public String getName()
	{
		if (getSandwich() != null && getSalad() != null && getDrink() != null)
		{
			String sandTemp = getSandwich().getName();
			String saldTemp = getSalad().getName();
			String drnkTemp = getDrink().getName();
			String returnString = (sandTemp + "/" + saldTemp + "/" + drnkTemp + " Trio");
			return returnString;
		}
		else
			return ("");
	}

	public double getPrice()
	{
		if (getSandwich() != null && getSalad() != null && getDrink() != null)
		{
			double sandPrice = getSandwich().getPrice();
			double saldPrice = getSalad().getPrice();
			double drnkPrice = getDrink().getPrice();
			
			double low = 0.0;
			if (sandPrice < saldPrice && sandPrice < drnkPrice)
			{
				low = sandPrice;
			}
			else
			if (saldPrice < sandPrice && saldPrice < drnkPrice)
			{
				low = saldPrice;
			}
			else
			{
				low = drnkPrice;
			}
			
			double temp = 0.0;
			temp = sandPrice + saldPrice + drnkPrice;
			temp -= low;
			return temp;
		}
		else 
			return 0.0;
	}

}
