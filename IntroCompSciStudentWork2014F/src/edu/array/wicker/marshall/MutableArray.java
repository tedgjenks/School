package edu.array.wicker.marshall;

import edu.jenks.dist.array.List;

public class MutableArray implements List {

	private int size = 0;
	private Object[] backingArray;

	public MutableArray() {
		backingArray = new Object[10];
	}

	public MutableArray(int capacity) {
		backingArray = new Object[capacity];
	}

	@Override
	public boolean add(Object object) {
		if (size == backingArray.length)
			doubleArraySize();
		backingArray[size] = object;
		size++;
		return true;
	}

	@Override
	public void add(int index, Object object) throws IndexOutOfBoundsException {
		if (index > size || index < 0){
			throw new IndexOutOfBoundsException("Size is " + size + ", attempted index was " + index);
		}
		else{
			if (size == backingArray.length)
				doubleArraySize();
			
			for (int copyIndex = size; copyIndex > index; copyIndex--)
				backingArray[copyIndex] = backingArray[copyIndex - 1];
			
			size++;
			backingArray[index] = object;
		}
	}

	@Override
	public void clear() {
		backingArray = new Object[backingArray.length];
		size = 0;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0 || size == 0)
			throw new IndexOutOfBoundsException("Size is " + size + ", attempted index was " + index);
		else
			return backingArray[index];
	}

	@Override
	public Object[] getBackingArray() {
		return backingArray;
	}

	@Override
	public boolean isEmpty() {
		for (Object e : backingArray)
			if (e != null)
				return false;
		return true;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0 || size == 0){
			throw new IndexOutOfBoundsException("Size is " + size + ", attempted index was " + index);
		}
		else {
			Object objectAt = backingArray[index];
			for (int copyIndex = index; copyIndex < size - 1; copyIndex++){
				backingArray[copyIndex] = backingArray[copyIndex + 1];
			}
			backingArray[size - 1] = null;
			size--;
			return objectAt;
		}
	}

	@Override
	public Object set(int index, Object object) throws IndexOutOfBoundsException {
		if (index >= size || index < 0 || size == 0){
			throw new IndexOutOfBoundsException("Size is " + size + ", attempted index was " + index);
		}
		else {
			Object positionAt = backingArray[index];
			backingArray[index] = object;
			return positionAt;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[size];
		for (int index = 0; index < size; index++)
			newArray[index] = backingArray[index];
		return newArray;
	}

	private void doubleArraySize(){
		Object[] newArray = new Object[backingArray.length * 2];
		for (int index = 0; index < size; index++)
			newArray[index] = backingArray[index];
		backingArray = newArray;
	}

}
