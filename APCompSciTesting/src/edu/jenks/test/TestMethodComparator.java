package edu.jenks.test;

import java.lang.reflect.Method;
import java.util.Comparator;

public class TestMethodComparator implements Comparator<Method> {
	
	private static final byte NUM_CHARS_BEFORE_ORDER = (byte)"test".length();
	private static final byte NUM_ORDER_DIGITS = 2;
	private static final byte END_INDEX_ORDER_DIGITS = (byte)(NUM_CHARS_BEFORE_ORDER + NUM_ORDER_DIGITS);

	@Override
	public int compare(Method arg0, Method arg1) {
		/*if(arg0 == null || arg1 == null)
			return 0;*/
		String arg0Name = arg0.getName(), arg1Name = arg1.getName();
		/*if(!arg0Name.startsWith("test") || !arg1Name.startsWith("test"))
			return 0;*/
		int arg0Order = methodOrdered(arg0Name) ? getOrder(arg0Name) : 100;
		int arg1Order = methodOrdered(arg1Name) ? getOrder(arg1Name) : 100;
		return arg0Order - arg1Order;
	}
	
	public boolean methodOrdered(String name) {
		return name.matches("^.+\\d{2}.+$");
	}
	
	public int getOrder(String methodName) {
		return Integer.parseInt(methodName.substring(NUM_CHARS_BEFORE_ORDER, END_INDEX_ORDER_DIGITS));
	}
}
