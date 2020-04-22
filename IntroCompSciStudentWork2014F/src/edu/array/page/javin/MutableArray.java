package edu.array.page.javin;
import edu.jenks.dist.array.*;
public class MutableArray extends java.lang.Object implements List{
	private int size = 0;
	private Object[] backArray;
	public static void main(String[] args) {
		MutableArray instance = new MutableArray(2);
		instance.add(1);
		instance.add(6);
		instance.add(null);
		instance.add(3);
		instance.add(1, 2);
		System.out.println(instance.toStringy());
		System.out.println(instance.size());
	}
	public MutableArray() {
		this(1);
	}
	public MutableArray(int capacity) {
		backArray = new Object[capacity];
	}
	@Override
	public boolean add(Object arg0) {
		increaseBack();
		backArray[size] = arg0;
		size++;
		return true;
	}

	@Override
	public void add(int arg0, Object arg1) throws IndexOutOfBoundsException {
		if(arg0 > size) {
			throw new IndexOutOfBoundsException();
		}else if(arg0 == size) {
			add(arg1);
		}else {
			increaseBack();
			for(int i = size; i > arg0; i--) {
				backArray[i] = backArray[i-1];
			}
			size++;
			backArray[arg0] = arg1;
		}
	}

	@Override
	public void clear() {
		backArray = new Object[backArray.length];
		size = 0;
	}

	@Override
	public Object get(int arg0) throws IndexOutOfBoundsException {
		if(arg0 >= size) throw new IndexOutOfBoundsException();
		return backArray[arg0];
	}

	@Override
	public Object[] getBackingArray() {
		// TODO Auto-generated method stub
		return backArray;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Object remove(int arg0) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(arg0 >= size) throw new IndexOutOfBoundsException();          
		Object badOne = backArray[arg0];
		for(int i = arg0; i < size - 1; i++) {
			backArray[i] = backArray[i+1];
		}
		size--;
		return badOne;
	}

	@Override
	public Object set(int arg0, Object arg1) throws IndexOutOfBoundsException {
		if(arg0 >= size) throw new IndexOutOfBoundsException();
		backArray[arg0] = arg1;
		return arg1;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] theArray = new Object[size];
		for(int i = 0; i < theArray.length; i++) {
			theArray[i] = backArray[i];
		}
		return theArray;
	}
	private boolean increaseBack() {
		if(size == backArray.length) {
			Object[] tempArray = backArray;
			backArray = new Object[size * 2];
			for(int i = 0; i < tempArray.length; i++) {
				backArray[i] = tempArray[i];
			}
			return true;
		}
		return false;
	}
	private String toStringy() {
		String fun = "{";
		String add;
		for(int i = 0; i < size; i++) {
			try {
				add = backArray[i].toString();
			}catch (NullPointerException npe) {
				add = "null";
			}
			fun += add + ", ";
		}
		return fun + "}";
	}
}
