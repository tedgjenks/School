package edu.array.jenkst.ted.test;

import java.util.Arrays;
import static java.lang.System.out;

import edu.array.jenks.ted.MutableArray;

public class MutableArrayTest {

	public static void main(String[] args) {
		out.println("Begin");
		MutableArray ma = new MutableArray(5);
		Object[] exp = {"1"};
		ma.add("1");
		Object[] act = ma.toArray();
		assert(Arrays.equals(exp, act));
		ma.add("2");
		ma.add("3");
		ma.add(1, "x");
		exp = new Object[]{"1", "x" , "2", "3"};
		act = ma.toArray();
		assert(Arrays.equals(exp, act));
		out.println("End");
	}

}
