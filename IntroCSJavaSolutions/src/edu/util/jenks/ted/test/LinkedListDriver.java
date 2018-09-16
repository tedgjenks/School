package edu.util.jenks.ted.test;

import edu.util.jenks.ted.LinkedList;

public class LinkedListDriver {

	public static void main(String[] args) {
		LinkedList<String> a = new LinkedList<>();
		System.out.println("Get Element: " + a.indexOf("Element"));
		a.add("Element 1");
		a.add("Element 2");
		a.add("Element 3");
		a.add(1, "Element 1.5");
		a.add(0, "Element 0.5");
		a.add(5, "Element 4");
		a.add(null);
		a.add("Element 2");
		System.out.println(a);
		/*System.out.println(a.get(0));
		System.out.println(a.get(1));
		System.out.println(a.get(2));
		System.out.println(a.get(3));*/
		System.out.println("Get Element 0.5: " + a.indexOf("Element 0.5"));
		System.out.println("Get first Element 2: " + a.indexOf("Element 2"));
		System.out.println("Get Element 4: " + a.indexOf("Element 4"));
		System.out.println("Get Element Not Here: " + a.indexOf("Element Not Here"));
		System.out.println("Get Element null: " + a.indexOf(null));
		System.out.println("Get last Element 2: " + a.lastIndexOf("Element 2"));
	}

}
