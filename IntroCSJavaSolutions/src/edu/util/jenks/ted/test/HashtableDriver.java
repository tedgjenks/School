package edu.util.jenks.ted.test;

import edu.util.jenks.ted.Hashtable;
import static java.lang.System.out;

import java.util.Arrays;

public class HashtableDriver {

	public static void main(String[] args) {
		Hashtable<Integer, String> h = new Hashtable<>(10);
		out.println(h.getCapacity());
		assert h.isEmpty();
		h.put(1, "1");
		h.put(2, "2");
		h.put(3,  "3");
		h.put(4, "4");
		h.put(44, "44");
		h.put(1, "1a");
		h.put(11, "11");
		h.put(11, "11");
		h.put(5, "5");
		h.put(6, "6");
		h.put(7, "7");
		h.put(21, "21");
		Integer[] keys = h.getKeys();
		String[] values = h.getValues();
		//h.clear();
		assert h.containsKey(2);
		assert !h.containsKey(100);
		assert h.containsValue("2");
		assert !h.containsValue("100");
		assert !h.isEmpty();
		out.println(h.getCapacity());
		out.println(h);
		out.println("key 1, val " + h.get(1));
		out.println("key 2, val " + h.get(2));
		out.println("key 100, val " + h.get(100));
		out.println("keys: " + Arrays.toString(h.getKeys()));
		out.println("values: " + Arrays.toString(h.getValues()));
		out.println(h.remove(44));
		out.println(h.remove(44));
		out.println(h.remove(2));
		out.println(h);
	}

}
