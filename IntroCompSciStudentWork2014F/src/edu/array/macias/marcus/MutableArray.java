package edu.array.macias.marcus;

import edu.jenks.dist.array.List;

public class MutableArray implements List {
	int capacity;
	int size;
	private Object[] backingArray;
	
	
	public MutableArray() {
		size = 0;
		backingArray = new Object[5];
		capacity = 5;
	}
	
	public MutableArray(int capacity) {
		size = 0;
		this.capacity = capacity;
		backingArray = new Object[capacity];
	}

	public boolean add(Object element) {
		add(size,element);
		return true;
	}

	public void add(int index, Object element) throws IndexOutOfBoundsException {
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		
		if(size == capacity) {
			Object[] newArray = new Object[capacity * 2];
			capacity *= 2;
			for(int i = 0 ; i < size ;i++) {
				newArray[i] = backingArray[i];
			}
			backingArray = newArray;
		}
		
		if(index == size ) {
			backingArray[index] = element;
			size++;
		}else {
			/*
			 * This code works however mr jenks made me redo it.
			 * Object[] temp = new Object[size+1];
			for(int i = 0, x = 0; i < size + 1;i++,x++) {
				if(i == index) {
					temp[i] = element;
					x--;
				}else {
					temp[i] = backingArray[x];
				}
			}
			for(int i = 0 ; i < temp.length;i++) {
				backingArray[i] = temp[i];
			}*/
			
			for(int i = size-1; i >= index ; i--) {
				backingArray[i+1] = backingArray[i];
				if(i == index ) {
					backingArray[i] = element;
				}
			}
			size++;
		}
		
	}

	public void clear() {
		for(int i = 0 ; i < size;i++) {
			backingArray[i] = null;
		}
		size = 0;
	}

	public Object get(int index) throws IndexOutOfBoundsException {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return backingArray[index];
	}

	public boolean isEmpty() {
		return size == 0;
	}
	public static void main(String[] args) {
		MutableArray run = new MutableArray(10);
		/*run.backingArray[0] = 1;
		run.backingArray[1] = 2;
		run.backingArray[2] = 3;
		run.backingArray[3] = 4;
		run.size = 4;
		run.remove(0);*/
		run.add(1);
		run.add(2);
		run.add(3);
		run.add(4);
		run.add(5);
		run.add(6);
		run.add(7);
		run.add(8);
		run.add(9);
		run.add(10);
		
		//System.out.println("Capacity:  " + run.capacity);
		
		
		
		Object[] client = run.toArray();
		test(client);
		
		System.out.println("");
		System.out.println("Size: " + run.size);
		System.out.println("Is Empty: " + run.isEmpty());
		run.add(0, 0);
		client = run.toArray();
		test(client);
		System.out.println("");
		System.out.println("Size: " + run.size);
		System.out.println("Removed:  " + run.remove(4));
		client = run.toArray();
		
		test(client);
		System.out.println("");
		System.out.println("Size: " + run.size);
	
		test(client);
		System.out.println("");
		run.remove(run.size -1);
		client = run.toArray();
		test(client);
		
	}
	
	static public void test(Object[] client) {
		for(int i = 0 ; i < client.length;i++) {
			if(i == 0 ) {
				System.out.print("Array: [");
			}
			
			System.out.print(client[i] );
			if(i == client.length - 1) {
				System.out.print("]");
			}else {
				System.out.print(", ");
			}
		}
	}
	public Object remove(int index) throws IndexOutOfBoundsException {
		if(index >= size || index < 0) {
			
			throw new IndexOutOfBoundsException();
		}
		Object answer = get(index);
		/*
		for(int i = index ; i < size -1 ;i++) {
			Object temp = backingArray[i+1];
			backingArray[i] = temp;
			
		}*/
		for(int i = index ; i < size - 1;i++) {
			backingArray[i] = backingArray[i+1];
		}
		size--;
		return answer;
	}

	public Object set(int index, Object element) throws IndexOutOfBoundsException {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Object temp = backingArray[index];
		backingArray[index] = element;
		return temp;
	}

	public int size() {
		
		return size;
	}

	public Object[] toArray() {
		Object[] returnThing = new Object[size];
		for(int i = 0 ; i < size; i++) {
			returnThing[i] = backingArray[i];
		}
		return returnThing;
	}
	
	public Object[] getBackingArray() {
		return backingArray;
	}

}
