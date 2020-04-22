package edu.array.tran.don;
import edu.jenks.dist.array.*;

public class MutableArray implements List{
	int curCap;
	int curSize;
	Object[] backArr;
	//Object[] toomp;
	public static void main(String args[]) {
		MutableArray what = new MutableArray(1);
		what.add(0);
		what.add(0);
		System.out.println(what.size());
		what.add(2, 50);
		what.add(2, 50);
		what.add(2, 50);
		what.add(2, 50);
		what.add(2, 50);
		what.add(2, 50);
		what.add(2, 50);
		what.add(3, 1000);
		what.toStringg();
		//what.add(6, 50);
		/*
		what.add(0, 1);
		what.add(0, 4);
		what.add(what.size(), null);
		what.add(what.size(), 10);
		what.add(what.size(), 10);
		what.add(what.size(), 10);
		System.out.println(what.remove(what.size() - 1) + " REMOVE");
		System.out.println(what.get(6) + " GET");
		System.out.println(what.set(1, 5) + " SET");
		//System.out.println(what.remove(0) + " REMOVE");
		//what.add(20);
		//what.add(20);
		//what.add(20);
		System.out.println(what.size() + " SIIIIIIIIIZE");
		*/
		for(int i = 0; i < what.backArr.length; i++) {
			//System.out.print(what.backArr[i] + ", ");
		}
		//System.out.println("wo");
		//what.add(6, 999);
		what.toStringg();
	}
	public MutableArray() {
		curCap = 5;
		backArr = new Object[5];
	}
	
	public MutableArray(int capacity) {
		curCap = capacity;
		backArr = new Object[capacity];
	}

	public boolean add(Object arg0) {
		add(curSize, arg0);
		return false;
	}

	public void add(int index, Object arg1) throws IndexOutOfBoundsException {
		//System.out.println("asdfsadfsdfsdaf");
		if(index < 0 || index > curSize)
			throw new IndexOutOfBoundsException();
		//System.out.println("dick");
		if(curSize == curCap) {
			//System.out.println("dick");
			curCap *= 2;
			Object[] tamp = backArr;
			backArr = new Object[curCap];
			for(int i = 0; i < tamp.length; i++) {
				backArr[i] = tamp[i];
			}
		}
		curSize++;
		//System.out.println(curSize + " WDASLJ:LSD "+  backArr.length);
		for(int i = curSize-1; i > index; i--) {
			//System.out.println(curSize + backArr.length);
			backArr[i] = backArr[i - 1];
		}
		backArr[index] = arg1;
	}

	public void clear() {
		curSize = 0;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > curSize - 1) 
			throw new IndexOutOfBoundsException();
		return backArr[index];
	}

	@Override
	public Object[] getBackingArray() {
		return backArr;
	}

	@Override
	public boolean isEmpty() {
		if(curSize == 0) 
		return true;
		return false;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > curSize - 1) 
			throw new IndexOutOfBoundsException();
		curSize--;
		Object TAAAAAAMP = backArr[index];
		for(int i = index; i < curSize; i++) {
			backArr[i] = backArr[i + 1];
		}
		return TAAAAAAMP;
	}

	public Object set(int index, Object arg1) throws IndexOutOfBoundsException {
		if(index < 0 || index > curSize - 1) 
			throw new IndexOutOfBoundsException();
		Object temp = backArr[index];
		backArr[index] = arg1;
		return temp;
	}

	public int size() {
		return curSize;
	}

	public void toStringg() {
		for(int i = 0; i < curSize; i++) {
			System.out.print(backArr[i] + ", ");
		}
		System.out.println("");
	}
	public Object[] toArray() {
		Object[] temp = new Object[curSize];
		for(int i = 0; i < curSize; i++) {
			temp[i] = backArr[i];
		}
		return temp;
	}

}
