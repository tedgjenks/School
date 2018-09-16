package edu.util.mariscal.juan;

import java.util.NoSuchElementException;
import edu.jenks.dist.util.*;
public class LinkedList<E> implements AbstractLinkedList<E> {
	//public Bucket start= null;
	public Bucket end =new Bucket(null, null, null);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> s = new LinkedList<String>();
		
		System.out.println(s);
		s.add("first");
		s.add("fourth");
		s.add("fifth");
		s.add(1,"second");
		s.add(2, "third");
		s.addFirst("zero");
		s.addLast("sixth");
		System.out.println(s);
		System.out.println("get second: "+s.get(2));
		//System.out.println(s.get(7));
		System.out.println("get sixth: "+s.get(6));
		s.removeLast();
		System.out.println(s);
		//s.clear();
		System.out.println("index of fourth: "+ s.indexOf("fourth"));
		System.out.println("index of someting not in list: "+ s.indexOf("f"));
		s.add(6, "zero");
		System.out.println("should be 6 :"+s.lastIndexOf("zero"));
		System.out.println("should be zero: "+s.remove());
		System.out.println(s);
		//s.addFirst("zero");
		s.remove(2);
		s.remove(0);
		s.addFirst("zero");
		System.out.println(s);
		s.set(2, null);
		System.out.println(s);
		System.out.println(s.contains(null));
		s.clear();
		System.out.println(s.remove(null));
		
		/*s.add(0, "fourth");
		s.add(0, "third");
		s.add(0, "second");
		s.add(0, "first");
		*/s.add(0, "first");
		s.addFirst("zero");
		s.addLast("test");
		s.add(3, null);
		s.add(2,"second");
		System.out.println(s);
		//s.remove();
		s.remove(0);
		s.remove(3);
		//s.remove("test");
		System.out.println(s+"\n");
		
		
		s.add(0, "zero");
		s.add(3, "third");
		System.out.println(s);
		s.remove(0);
		s.remove(s.size()-1);
		s.remove("first");
		System.out.println(s);
		
		
		
	}
	public class Bucket {
		public Bucket bBefore=null;
		public E current = null;
		public Bucket bNext=null;
		public Bucket(Bucket before, E e, Bucket next) {
			bBefore = before;
			current = e;
			bNext = next;			
		}
	}
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		LinkedList<E>.Bucket a= this.new Bucket(end,e, null);
		end.bNext=a;
		end = a;
		return true;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(index<0||index>size())
			throw new IndexOutOfBoundsException();
		if(index==size())
			add(element);
		else {
			int diff=size()-index;
			Bucket tempBefore=end;
			Bucket tempNext;
			for(int i=0; i<diff; i++) {
				tempBefore = tempBefore.bBefore;
				//System.out.println(tempBefore.current);
			}
			//System.out.println("tempbefore is : "+tempBefore.current);
			
			tempNext = tempBefore.bNext;
			//System.out.println("tempnext is : "+tempNext.current);
			Bucket a = new Bucket(tempBefore, element, tempNext);
			tempBefore.bNext=a;
			if(tempNext!=null)
				tempNext.bBefore=a;
			if(index==size())
				add(element);
		}
	}
	@Override
	public void addFirst(E e) {
		// TODO Auto-generated method stub
		add(0, e);
	}

	@Override
	public void addLast(E e) {
		// TODO Auto-generated method stub
		add(e);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		end = new Bucket(null, null, null);
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return indexOf(o)>=0;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(index<0||index>=size())
			throw new IndexOutOfBoundsException();
		int diff = size()-index-1;
		Bucket temp = end;
		for(int i=0; i<diff;i++){
			temp= temp.bBefore;
		}
		return temp.current;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(size()==0)
			throw new NoSuchElementException();
		return get(0);
	}

	@Override
	public E getLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(size()==0)
			throw new NoSuchElementException();
		return end.current;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		int index=-1;
		Bucket temp = end;
		for(int i=0; i<size();i++) {
			if(o!=null) {
				if(o.equals(temp.current)) {
					index = size()-i-1;
				}
			}
			else {
				if(temp.current==o)
					index=size()-i-1;
			}
			temp=temp.bBefore;
		}
		return index;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		int index=-1;
		Bucket temp = end;
		for(int i=0; i<size();i++) {
			if(o.equals(temp.current)) {
				index = size()-i-1;
				break;
		}
			temp=temp.bBefore;
		}
		return index;
		}

	@Override
	public E remove() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(size()<=0)
			throw new NoSuchElementException();
		Bucket temp=end;
		for(int i=0; i<size()-1;i++) {
			temp=temp.bBefore;
			
		}
		if(temp.bNext!=null) {
			Bucket a = temp.bNext;
			a.bBefore=new Bucket(null, null, a);
		}
		else {
			clear();
		}
		return temp.current;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(index<0||index>=size())
			throw new IndexOutOfBoundsException();
		
		Bucket temp=end;
		if(index==size()-1)
			return removeLast();
		else {	
			//System.out.println("im here");
			int diff=size()-index-1;
			for(int i=0; i<diff;i++) {
				temp=temp.bBefore;
			}
			//System.out.println(temp.current);
			temp.bBefore.bNext=temp.bNext;
			temp.bNext.bBefore=temp.bBefore;
		}
		return temp.current;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		int index = indexOf(o);
		//System.out.println(index);
		if(index!=-1)
			remove(index);
		return index>=0;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(size()==0)
			throw new NoSuchElementException();
		Bucket temp = end;
		if(size()!=1) {
			end.bBefore.bNext=null;
			
			end = temp.bBefore; 
		}
		else {
			clear();
		}
		return temp.current;
	}

	@Override
	public E set(int index, E element) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(index>=size()||index<0)
			throw new IndexOutOfBoundsException();
		Bucket temp=end;
		int diff=size()-index;
		for(int i=0; i<diff-1;i++) {
			temp=temp.bBefore;
		}
		E past = temp.current;
		temp.current= element;
		return past;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		boolean isNull=false;
		int count =0;
		Bucket temp=end;
		while(!isNull) {
			//System.out.println("current bucket : "+temp.current);
			if(temp.bBefore!=null) {
				count++;
			}
			else {isNull=true; }
			temp=temp.bBefore;	
		}
		return count;
	}
	public String toString() {
		String a = "LinkedList("+size()+") [";
		String b = "";
		Bucket temp=end;
		for(int i=0; i<size();i++) {
			if(i==size()-1) {
				if(temp.current!=null)
					b = temp.current.toString()+b;
				else 
					b = "null" + b;
			}
			else {
				if(temp.current!=null)
					b = ", "+temp.current.toString()+b;
				else 
					b = ", null" + b;
			}	
			temp = temp.bBefore;
		}
		if(size()>0)
			a=a+b+", ]";
		else
			a+="]";
		return a;
	}
}
