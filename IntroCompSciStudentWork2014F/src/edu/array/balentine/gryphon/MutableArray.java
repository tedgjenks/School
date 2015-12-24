package edu.array.balentine.gryphon;

import edu.jenks.dist.array.List;

public class MutableArray implements List {
	Object[] backingArray;

	public MutableArray(){
	}

	public MutableArray(int capacity){
		backingArray=new Object[capacity];
	}

	@Override
	public boolean add(Object element) {
		return false;
	}

	@Override
	public void add(int index, Object element) throws IndexOutOfBoundsException {
	}

	@Override
	public void clear() {
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		return null;
	}

	@Override
	public Object[] getBackingArray() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		return null;
	}

	@Override
	public Object set(int index, Object element) throws IndexOutOfBoundsException {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Object[] toArray() {
		return this.toArray();
	}

	public static void main(String[] args) {
	}

}
