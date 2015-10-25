package edu.phone.ball.daniel;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;
import java.util.Random;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {

	@Override
	public String generateRandomPhoneNumber() 
	{
		Random gen = new Random();
		String output = ("");
		int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, j = 0;
		a = (gen.nextInt(8));
		b = (gen.nextInt(8));
		c = (gen.nextInt(8));
		d = (gen.nextInt(8));
		e = (gen.nextInt(5));
		f = (gen.nextInt(3));
		g = (gen.nextInt(10));
		h = (gen.nextInt(10));
		i = (gen.nextInt(10));
		j = (gen.nextInt(10));
		
		
		output = ("" + a + b + c + "-" + d + e + f + "-" + g + h + i + j);
		return output;

	}

	public static void main(String[] args) 
	{

	}

}
