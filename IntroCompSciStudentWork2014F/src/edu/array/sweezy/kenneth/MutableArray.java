package edu.array.sweezy.kenneth;
import edu.jenks.dist.array.*;

public class MutableArray implements List {
	private Object[] backArray = new Object[0];
	private int numElementsUsed = 0;

	public static void main(String[] args) {
		MutableArray testing = new MutableArray(10);
		testing.toStringTesting();
		testing.add(1);
		testing.add(2);
		testing.add(3);
		testing.add(4);
		testing.add(5);
		testing.toStringTesting();
		testing.set(69, "no u");
		testing.toStringTesting();
	}
	
	public MutableArray() {
		backArray = new Object[0];
	}
	
	public MutableArray(int capacity) {
		backArray = new Object[capacity];
	}

	public boolean add(Object objectToAdd) {
		if(objectToAdd != null && backArray != null) {
			if(numElementsUsed == backArray.length) {
				Object[] temp = new Object[backArray.length * 2];
				for(int i = 0; i < backArray.length; i++) {
					if(backArray[i] == null) {
						backArray[i] = objectToAdd;
						numElementsUsed++;
						return true;
					}
					temp[i] = backArray[i];
				}
				temp[numElementsUsed] = objectToAdd;
				backArray = temp;
				numElementsUsed++;
				return true;
			} else {
				backArray[numElementsUsed] = objectToAdd;
				numElementsUsed++;
				return true;
			}
		}
		return false;
	}

	public void add(int position, Object objectToAdd) throws IndexOutOfBoundsException {
		if(position < 0 || position > backArray.length) {
			throw new IndexOutOfBoundsException();
		} else {
			Object[] temp;
			if(numElementsUsed >= backArray.length) {
				temp = new Object[backArray.length * 2];
				for(int i = 0; i < numElementsUsed; i++) {
					temp[i] = backArray[i];
				}
				for(int i = numElementsUsed - 1; i  > position - 1; i--) {
					temp[i + 1] = temp[i];
				}
				temp[position] = objectToAdd;
				numElementsUsed++;
				backArray = temp;
			} else {
				for(int i = numElementsUsed - 1; i > position - 1; i--) {
					backArray[i + 1] = backArray[i];
				}
				backArray[position] = objectToAdd;
				numElementsUsed++;
			}
		}
	}
	
	public void clear() {
		backArray = new Object[backArray.length];
		numElementsUsed = 0;
	}

	public Object get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > backArray.length) {
			throw new IndexOutOfBoundsException();
		} else {
			return backArray[index];
		}
	}

	public Object[] getBackingArray() {
		return backArray;
	}

	public boolean isEmpty() {
		if(numElementsUsed == 0) {
			return true;
		} else {
			return false;
		}
	}

	public Object remove(int elementToRemove) throws IndexOutOfBoundsException {
		if(elementToRemove < 0 || elementToRemove >= backArray.length) {
			throw new IndexOutOfBoundsException();
		} else {
			Object temp = backArray[elementToRemove];
			for(int i = 0; i < numElementsUsed; i++) {
				if(i == elementToRemove) {
					backArray[i] = null;
					numElementsUsed--;
				} else {
					continue;
				}
			}
			for(int i = elementToRemove; i < numElementsUsed; i++) {
				backArray[i] = backArray[i + 1];
			}
			return temp;
		}
	}
	
	public Object set(int indexToSet, Object objectToSet) throws IndexOutOfBoundsException {
		if(indexToSet < 0 || indexToSet > backArray.length) {
			throw new IndexOutOfBoundsException();
		} else {
			Object temp = backArray[indexToSet];
			backArray[indexToSet] = objectToSet;
			return temp;
		}
	}

	public int size() {
		return numElementsUsed;
	}

	public Object[] toArray() {
		if(numElementsUsed == 0) {
			return new Object[] {0};
		} else if(numElementsUsed == 1) {
			return new Object[] {backArray[0]};
		}
		Object[] temp = new Object[numElementsUsed];
		for(int i = 0; i < numElementsUsed; i++) {
			temp[i] = backArray[i];
		}
		return temp;
	}
	
	private void toStringTesting() {
		System.out.print("[");
		for(int i = 0; i < backArray.length; i++) {
			System.out.print(backArray[i]);
			if(i != backArray.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.print("]");
		System.out.print(" With numElementsUsed " + size() + " And backArray Size " + backArray.length + "\n");
	}

}
