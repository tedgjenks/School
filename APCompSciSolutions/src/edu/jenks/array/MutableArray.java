/**
 * 
 */
package edu.jenks.array;

/**
 * @author JenksT
 *
 */
public class MutableArray implements edu.jenks.array.dist.List {
	
	private Object[] backingArray;
	private int size;
	
	public MutableArray() {
		this(5);
	}
	
	public MutableArray(int capacity) {
		if(capacity < 1)
			capacity = 1;
		backingArray = new Object[capacity];
	}

	@Override
	public boolean add(Object element) {
		add(size, element);
		return true;
	}

	@Override
	public void add(int index, Object element) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if(size == capacity())
			doubleCapacity();
		if(index != size) {
			for(int backingArrayIndex = size - 1; backingArrayIndex >= index; backingArrayIndex--)
				backingArray[backingArrayIndex + 1] = backingArray[backingArrayIndex];
		}
		backingArray[index] = element;
		size++;
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public Object get(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		return backingArray[index];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Object remove(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		Object removedElement = backingArray[index];
		while(index < size - 1)
			backingArray[index] = backingArray[++index];
		size--;
		return removedElement;
	}

	@Override
	public Object set(int index, Object element) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		Object replacedObject = backingArray[index];
		backingArray[index] = element;
		return replacedObject;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] arrayCopy = new Object[size];
		for(int index = size - 1; index >= 0; index--)
			arrayCopy[index] = backingArray[index];
		return arrayCopy;
	}
	
	private void doubleCapacity() {
		Object[] doubledBackingArray = new Object[capacity() * 2];
		for(int index = backingArray.length - 1; index >= 0; index--)
			doubledBackingArray[index] = backingArray[index];
		backingArray = doubledBackingArray;
	}
	
	private int capacity() {
		return backingArray.length;
	}

}
