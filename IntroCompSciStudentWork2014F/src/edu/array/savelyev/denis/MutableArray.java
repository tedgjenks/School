package edu.array.savelyev.denis;
import edu.jenks.dist.array.*;

public class MutableArray implements List{
	private int elementsUsed = 0;
	private Object[] backArray;
	
	public static void main(String[] args) {
		MutableArray test = new MutableArray(6);
		test.add("ok");
		test.add("ok");
		test.add("ok");
		test.add("ok");
		test.add("ok");
		test.add("ok");
		test.add("ok");
		test.add("ok");
		test.add("ok");
		test.add(1, "boom");
		//test.add("ok");
		//test.add("ok");
		//test.add(12);
		test.stringify();
		//test.get(2);
		//test.stringify();
	}
	
	private void stringify() { 
		System.out.print("[");
		for(int x = 0; x <= backArray.length - 1; x++) {
			System.out.print(backArray[x] + ", ");
		}
		System.out.print("]\n");
	}
	
	public MutableArray() {
		backArray = new Object[1];
	}
	
	public MutableArray(int capacity) {
		backArray = new Object[capacity];
	}
	
	private boolean checkIfFull() {
		int count = 0;
		for (int x = 0; x < backArray.length; x++) {
			if(backArray[x] != null) {
				count++;
			}
		}
		if(count == backArray.length) {
			return true;
		}
		return false;
	}
	public boolean add(Object element) {
		if(backArray != null && element != null) {
			if(checkIfFull()) {
				Object[] temp = new Object[backArray.length * 2];
				for(int x = 0; x < backArray.length; x++) {
					temp[x] = backArray[x];
				}
				backArray = temp;
				backArray[backArray.length / 2] = element;
			} else {
				backArray[elementsUsed] = element;
			}
			elementsUsed++;
			return true;
		}
		return false;
	}
//up until the index shift every element right and then insert the element thats supposed to be added
	public void add(int index, Object element) throws IndexOutOfBoundsException {
		//Object[] temp = backArray;
		if(element != null && backArray != null) {
			if (index >= backArray.length) {//if index is out of bounds
				throw new IndexOutOfBoundsException("bad");
			} else if(index < 0){
				throw new IndexOutOfBoundsException("bad");
			} else {
				 for (int x = backArray.length - 1; x >= index; x--) {
					 backArray[x] = backArray[x-1]; 
				 }
				 backArray[index] = element;
			}
		}
	}

	public void clear() {
		backArray = new MutableArray[backArray.length];
	}

	public Object get(int index) throws IndexOutOfBoundsException {
		if(index > backArray.length) {
			
		}
		return backArray[index];
	}

	public Object[] getBackingArray() {
		return null;
	}

	public boolean isEmpty() {
		for(int x = 0; x < backArray.length; x++) {
			if(backArray[x] != null) {
				return true;
			}
		}
		return false;
	}

	public Object remove(int index) throws IndexOutOfBoundsException {
		
		return null;
	}

	public Object set(int index, Object element) throws IndexOutOfBoundsException {
		if(index >= backArray.length) {
			
		} else {
			backArray[index] = element;
		}
		return null;
	}

	public int size() {
		return backArray.length;
	}

	public Object[] toArray() {
		return null;
	}

}
