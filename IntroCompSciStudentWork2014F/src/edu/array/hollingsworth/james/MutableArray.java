package edu.array.hollingsworth.james;

import edu.jenks.dist.array.List;

public class MutableArray implements List {
	
	private Object[] arr;
	private int size = 0;
	
	public MutableArray() {
		arr = new Object[0];
	}
	
	public MutableArray(int capacity) {
		arr = new Object[capacity];
	}

	@Override
	public boolean add(Object obj) {
		if(size >= arr.length) {
			Object[] tmp = new Object[(arr.length == 0 ? 1: size * 2)];
			for(int i = 0; i < arr.length; i++)
				tmp[i] = arr[i];
			arr = tmp;
		}
		arr[size] = obj;
		size++;
		return true;
	}

	@Override
	public void add(int index, Object obj) throws IndexOutOfBoundsException {
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		if(size >= arr.length) {
			Object[] tmp = new Object[(arr.length == 0 ? 1: size * 2)];
			for(int i = 0; i < arr.length; i++)
				tmp[i] = arr[i];
			arr = tmp;
		}
		for(int i = arr.length - 1; i > index; i--)
			arr[i] = arr[i - 1];
		arr[index] = obj;
		size++;
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		else return arr[index];
	}

	@Override
	public Object[] getBackingArray() {
		return arr;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		else {
			Object tmp = arr[index];
			arr[index] = null;
			for(int i = index; i < arr.length - 1; i++)
				arr[i] = arr[i + 1];
			size--;
			return tmp;
		}
	}

	@Override
	public Object set(int index, Object obj) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		else {
			Object tmp = arr[index];
			arr[index] = obj;
			return tmp;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] tmp = new Object[size];
		for(int i = 0; i < size; i++) {
			tmp[i] = arr[i];
		}
		return tmp;
	}

}
