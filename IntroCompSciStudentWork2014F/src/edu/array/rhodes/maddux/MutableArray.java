package edu.array.rhodes.maddux;

import edu.jenks.dist.array.List;

public class MutableArray implements List {

	private Object[] backArr;
	private int capacity;
	private int netAdds;

	public static void main(String[] args) {
		MutableArray test = new MutableArray(1);
		test.add(0, "1");
		test.add(1, "2");
		test.add(2, "3");
		test.add(3, "4");
		test.add(4, "5");
		System.out.println(test.size());
		test.add(5, "6");
		test.add(6, "7");
		test.yaBoi();
		test.remove(6);
		test.yaBoi();
		test.remove(0);
		test.yaBoi();
		test.remove(3);
		//test.set(5, "10");
		test.yaBoi();
		System.out.println("\n" + test.size());
	}

	public void yaBoi() {
		System.out.print("Array:  [");
		for(int i = 0 ; i < netAdds;i++) {
			System.out.print(backArr[i] + ", ");
		}
		System.out.print("]");
	}
	
	public MutableArray(int capacity) {
		backArr = new Object[capacity];
		this.capacity = capacity;
	}

	public boolean add(Object element) {
		add(netAdds, element);
		return true;
	}

	public void add(int index, Object element) throws IndexOutOfBoundsException {
		if (index < 0 || index > netAdds) {
			throw new IndexOutOfBoundsException();
		} else {
			if(netAdds == capacity) {
				Object[] temp = new Object[capacity*=2];
				for(int i = 0; i < netAdds; i++) {
					temp[i] = backArr[i];
				}
				backArr = temp;
			}
			for(int i = netAdds-1; i >= index; i--) {
				backArr[i+1] = backArr[i];
			}
			backArr[index] = element;
		}
		netAdds++;
	}

	public void clear() {
		netAdds = 0;
	}

	public Object get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= netAdds) {
			throw new IndexOutOfBoundsException();
		} else {
			return backArr[index];
		}
	}

	public Object[] getBackingArray() {
		return backArr;
	}

	public boolean isEmpty() {
		return netAdds == 0;
	}

	public Object remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= netAdds) {
			throw new IndexOutOfBoundsException();
		} else {
			netAdds--;
			for(int i = index; i < netAdds; i++) {
				backArr[i] = backArr[i+1];
			}
		}
		return backArr[index];
	}

	public Object set(int index, Object element) throws IndexOutOfBoundsException {
		if (index < 0 || index >= netAdds) {
			throw new IndexOutOfBoundsException();
		} else {
			backArr[index] = element;
		}
		return element;
	}

	public int size() {
		return netAdds;
	}

	public Object[] toArray() {
		Object[] toBReturned = new Object[netAdds];
		for (int i = 0; i < netAdds; i++) {
			toBReturned[i] = backArr[i];
		}
		return toBReturned;
	}
}
