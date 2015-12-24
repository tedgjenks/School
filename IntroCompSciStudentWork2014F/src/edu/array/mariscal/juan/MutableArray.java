package edu.array.mariscal.juan;

import edu.jenks.dist.array.List;

public class MutableArray implements List {
	
	private Object [] ba;
	
	
	public MutableArray(int capacity){
		ba = new Object [capacity];
	}
	@Override
	public boolean add(Object ob) {
		// TODO Auto-generated method stub
		if(this.get(this.size()-1) != null){
			Object [] a = new Object[this.size()*2];
			for(int n = 0; n < this.size(); n++){
				a[n]=this.get(n);
			}
			this.setBackingArray(a);
			
		}
		int o = 0;
		for(int n = 0; n < this.getBackingArray().length; n++){
			if(this.get(n)==null && o ==0){
				this.set(n, ob);
				o++;
			}
		}
		return true;
	}

	@Override
	public void add(int i, Object ar) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(this.get(this.size()-1) != null){
			Object [] a = new Object[this.size()*2];
			for(int n = 0; n < this.size(); n++){
				a[n]=this.get(n);
			}
			this.setBackingArray(a);
		}
		Object temp = this.get(i);
		this.set(i, null);
		
		for(int n = i + 2; n < this.size(); n++){
			this.set(n, this.getBackingArray()[n-1]);
		}
		this.set(i, ar );
		this.set(i+1, temp);
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
		for(int n = 0; n < this.size(); n++){
			this.set(n, null);
		}
	
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return this.getBackingArray()[index];
	}

	@Override
	public Object[] getBackingArray() {
		// TODO Auto-generated method stub
		return ba;
	}
	public void setBackingArray(Object [] a){
		ba = a;
		
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for(int n = 0; n < this.size(); n++){
			if(this.get(n)==null){
				
			}
			else return false;
		}
		return true;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		Object a = this.get(index);
		this.set(index, null);
		for(int n = index; n < this.size() -1; n++){
			this.set(n, this.get(n+1) );
		}
		return a;
	}

	@Override
	public Object set(int index, Object obj) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		Object a = this.get(index);
		this.getBackingArray()[index] = obj;
		return a;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int count = 0;
		for(int n = 0; n < ba.length; n ++){
			if (this.get(n)!= null){
				count++;
			}
		}
		return count;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object [] ar = new Object[this.size()];
		
		for(int n = 0; n < this.size(); n++){
			ar[n]=this.get(n);
		}
		return ar;
	}

	

}
