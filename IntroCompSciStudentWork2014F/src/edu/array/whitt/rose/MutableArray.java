package edu.array.whitt.rose;

import edu.jenks.dist.array.List;

public class MutableArray implements List {
	private int CAPACITY;
	Object mut[];
	private int adds = 0;
	public static void main(String[] args) {
		MutableArray m = new MutableArray(10);
//		m.add(11, "hi");
		m.add("rose");
		m.add("whitt");
		m.add(1, "hi");
		System.out.println(m.mut[0]);
		System.out.println(m.mut[1]);
		System.out.println(m.adds);
	}
	public MutableArray() {
		
	}
	
	public MutableArray(int capacity) {
		mut = new Object[capacity];
		CAPACITY = capacity;
	}

	public boolean isFull(Object[] arr) {
		return adds == CAPACITY;
		
	}
	
	public boolean add(Object arg0) {
		// TODO Auto-generated method stub
		if (adds == CAPACITY) {
			CAPACITY *= 2;
			Object temp[] = new Object[CAPACITY];
			for (int i = 0; i < adds; i++) {
				temp[i] = mut[i];
			}
			temp[adds] = arg0;
			adds++;
			mut = temp;
		} else {
			mut[adds] = arg0;
			adds++;
		}
		return true;
	}

	public void add(int arg0, Object arg1) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (arg0 > adds) {
			throw new IndexOutOfBoundsException("Index " + arg0 + " is out of bounds!");
		}
		
		if (adds == CAPACITY) {
			CAPACITY *= 2;
			Object temp[] = new Object[CAPACITY];
			for (int i = 0; i < adds; i++) {
				temp[i] = mut[i];
			}
			for (int i = adds - 1; i >= arg0; i--)  {
				temp[i + 1] = temp[i];
			}
			temp[arg0] = arg1;
			mut = temp;
			adds++;
		} else {
			for (int i = adds - 1; i >= arg0; i--)  {
				mut[i + 1] = mut[i];
			}
			mut[arg0] = arg1;
			adds++;
		}
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		adds = 0;
	}

	public Object get(int arg0) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (arg0 >= adds || arg0 < 0) {
			throw new IndexOutOfBoundsException("Index " + arg0 + " is out of bounds!");
		}
		return mut[arg0];
	}

	public Object[] getBackingArray() {
		// TODO Auto-generated method stub
		return mut;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return adds == 0;
	}

	public Object remove(int arg0) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (arg0 >= adds || arg0 < 0) {
			throw new IndexOutOfBoundsException("Index " + arg0 + " is out of bounds!");
		}
//		Object temp[] = new Object[CAPACITY];
		Object orig = mut[arg0];
//		for (int i = 0; i < arg0; i++) {
//			temp[i] = mut[i];
//		}
//		adds--;
//		for (int k = arg0 + 1; k < adds; k++) {
//			temp[k] = mut[k + 1];
//		}
//		mut = temp;
		adds--;
		for (int i = arg0; i < adds; i++) {
			mut[i] = mut[i + 1];
		}
		return orig;
		
	}
	
	public Object set(int arg0, Object arg1) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (arg0 >= adds || arg0 < 0) {
			throw new IndexOutOfBoundsException("Index " + arg0 + " is out of bounds!");
		}
		Object orig = mut[arg0];
		mut[arg0] = arg1;
		return orig;
	}

	public int size() {
		// TODO Auto-generated method stub
		return adds;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		if (isFull(mut)) {
			return mut;
		}
		Object arr[] = new Object[size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = mut[i];
		}
		return arr;
	}
	
}
