package edu.util.mathis.justin;
import java.util.NoSuchElementException;
import edu.jenks.dist.util.*;

public class LinkedList<E> implements AbstractLinkedList<E> {
	public Bucket begin = null;
	public Bucket end = null;
	public LinkedList() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 100; i++)
		for(int i2 = i; i2 < 20; i2++)
		System.out.println(20);
		
	}
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		if (begin == null) {
			begin = new Bucket(null,e,null);
			end = begin;
		}
		else {
			Bucket newEnd = new Bucket(end,e,null);
			end.setRight(newEnd);
			end = newEnd;
		}
		return true;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index > size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(begin == null) {
			begin = new Bucket (null,element,null);
			end = begin;
		}
		else if (index == size()){
			add(element);
		} else if (index == 0){
			Bucket newBucket = new Bucket(null,element,begin);
			begin.setLeft(newBucket);
			begin = newBucket;
		} else {
			Bucket currBuck = begin;
			for(int i = 0; i<=index; i++) {
				if(i == index) {
					Bucket prev = currBuck.getLeft();
					Bucket nBuck = new Bucket(prev, element, currBuck);
					prev.setRight(nBuck);
					currBuck.setLeft(nBuck);
					break;
				} else {
					currBuck = currBuck.getRight();
				}
			}
		}
		
	}

	@Override
	public void addFirst(E e) {
		add(0, e);
	}

	@Override
	public void addLast(E e) {
		add(e);
		
	}

	@Override
	public void clear() {
		begin = null;
		end = null;
	}

	@Override
	public boolean contains(Object o) {
		if (begin == null) {
			return false;
		}
		Bucket currBuck = begin;
		while(currBuck.getRight() != null) {
			if((o == null && currBuck.getInfo() == null) || currBuck.getInfo().equals(o)) {
				return true;
			}
			currBuck = currBuck.getRight();
		}
		if((o == null && currBuck.getInfo() == null) || currBuck.getInfo().equals(o)) {
			return true;
		}
		return false;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index >= size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Bucket currBuck = begin;
		for(int i = 0; i<=index; i++) {
			if(i == index) {
				return currBuck.getInfo();
			} else {
				currBuck = currBuck.getRight();
			}
		}
		return null;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return get(0);
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return get(size()-1);
	}

	@Override
	public int indexOf(Object o) {
		if(size() == 0) {
			return -1;
		}
		for(int i = 0; i < size(); i++) {
			if ((o == null && get(i) == null)) {
				return i;
			} else if (get(i) != null && get(i).equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int lastoccur = -1;
		for(int i = 0; i < size(); i++) {
			if ((o == null && get(i) == null)) {
				lastoccur = i;
			} else if (get(i) != null && get(i).equals(o)) {
				lastoccur = i;
			}
		}
		return lastoccur;
	}

	@Override
	public E remove() throws NoSuchElementException {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		Bucket newBegin = begin.getRight();
		begin.setRight(null);
		Bucket oldBegin = begin;
		begin = newBegin;
		return oldBegin.getInfo();
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index >= size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E info = get(index);
		Bucket currBuck = begin;
		if (size() == 1) {
			E inforet = get(0);
			clear();
			return inforet;
		}
		for(int i = 0; i<=index; i++) {
			if(i == index) {
				if(index == 0) {
					currBuck.getRight().setLeft(currBuck.getLeft());
					begin = currBuck.getRight();
				} else if (index == size()-1) {
					currBuck.getLeft().setRight(currBuck.getRight());
					end = currBuck.getLeft();
				} else {
					currBuck.getLeft().setRight(currBuck.getRight());
					currBuck.getRight().setLeft(currBuck.getLeft());
				}
				currBuck.setLeft(null);
				currBuck.setRight(null);
			} else {
				currBuck = currBuck.getRight();
			}
		}
		return info;
	}

	@Override
	public boolean remove(Object o) {
		if (indexOf(o) >= 0) {
			remove(indexOf(o));
			return true;
		}
		return false;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return remove(size()-1);
	}

	@Override
	public E set(int index, E element) throws IndexOutOfBoundsException {
		if(index >= size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E oldInfo = get(index);
		Bucket currBuck = begin;
		for(int i = 0; i<=index; i++) {
			if(i == index) {
				currBuck.setInfo(element);
			} else {
				currBuck = currBuck.getRight();
			}
		}
		return oldInfo;
	}

	@Override
	public int size() {
		if (begin == null) {
			return 0;
		}
		Bucket currBuck = begin;
		int count = 1;
		while(currBuck.getRight() != null) {
			currBuck = currBuck.getRight();
			count++;
		}
		return count;
	}
	
	public String toString() {
		String ret = "LinkedList("+size()+") [";
		for(int i = 0; i < size(); i++) {
			if (i != size()-1) {
				ret = ret+get(i)+", ";
			} else {
				ret = ret+get(i)+", ";
				ret = ret+"]";
			}
		}
		if(size() == 0) {
			ret = ret+"]";
		}
		return ret;
	}
	
	public class Bucket {
		public Bucket left = null;
		public E info = null;
		public Bucket right = null;
		
		public Bucket (Bucket Left, E Info, Bucket Right) {
			left = Left;
			info = Info; 
			right = Right;
		}
		
		public Bucket getLeft() {
			return left;
		}
		public E getInfo() {
			return info;
		}
		public Bucket getRight() {
			return right;
		}
		public void setLeft(Bucket b) {
			left = b;
		}
		public void setInfo(E e) {
			info = e;
		}
		public void setRight(Bucket b) {
			right = b;
		}
	}

}
