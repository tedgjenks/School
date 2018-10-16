package edu.util.mathis.justin;

import java.lang.reflect.Array;

import edu.jenks.dist.util.Map;
import edu.jenks.dist.util.MapHelper;

public class Hashtable<K, V> implements Map<K, V> {

	LinkedList[] backarr = new LinkedList[16];
	public static void main(String[] args) {
		//System.out.println(Map.LOAD_FACTOR);
		Hashtable<Integer, Integer> a = new Hashtable<>(4);
		a.put(5, 2);
		//System.out.println(a);
		//System.out.println();
		//System.out.println(a.get(5));
		//System.out.println();
		a.put(6, 2);
		a.put(7, 2);
		a.put(3, 2);
		a.put(3, 5);
		//a.remove(7);
		
		System.out.println(a.toString());
		System.out.println(a.getKeys());
		
	}
	
	public Hashtable(int initleng) {
		// TODO Auto-generated constructor stub
		LinkedList[] a = new LinkedList[initleng];
		backarr = a;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return backarr.length;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i = 0; i < getCapacity(); i++) {
			backarr[i] = null;
		}
	}

	@Override
	public boolean containsKey(Object key) {
		int index = Math.abs(MapHelper.hashFunction(key)%getCapacity());
		if (backarr[index] == null) {
			return false;
		}
		for(int i = 0; i < backarr[index].size(); i++) {
			if(((KVPair)backarr[index].get(i)).getK().equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		for(int i = 0; i < backarr.length; i++) {
			if (backarr[i] != null) {
				for(int i2 = 0; i2 < backarr[i].size(); i2++) {
					V curv = ((KVPair)backarr[i].get(i2)).getV();
					if(curv.equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		for(int i = 0; i < backarr.length; i++) {
			if (backarr[i] != null) {
				for(int i2 = 0; i2 < backarr[i].size(); i2++) {
					K curk = ((KVPair)backarr[i].get(i2)).getK();
					V curv = ((KVPair)backarr[i].get(i2)).getV();
					if(curk.equals(key)) {
						return curv;
					}
				}
			}
		}
		return null;
	}

	@Override
	public K[] getKeys() {
		if(size() == 0) {
			return null;
		}
		Class a = null;
		for(int i = 0; i < backarr.length; i++) {
			if(backarr[i] != null && backarr[i].size() != 0) {
				a = ((KVPair) backarr[i].get(0)).getK().getClass();
			}
		}
		int count = 0;
		K[] arr = (K[]) Array.newInstance(a, size());
		for(int i = 0; i < backarr.length; i++) {
			if(backarr[i] != null) {
				for(int i2 = 0; i2 < backarr[i].size(); i2++) {
					arr[count] = ((KVPair) backarr[i].get(i2)).getK();
					count += 1;
				}
			}
		}
		return arr;
	}

	@Override
	public V[] getValues() {
		if(size() == 0) {
			return null;
		}
		Class a = null;
		for(int i = 0; i < backarr.length; i++) {
			if(backarr[i] != null && backarr[i].size() != 0) {
				a = ((KVPair) backarr[i].get(0)).getV().getClass();
			}
		}
		int count = 0;
		V[] arr = (V[]) Array.newInstance(a, size());
		for(int i = 0; i < backarr.length; i++) {
			if(backarr[i] != null) {
				for(int i2 = 0; i2 < backarr[i].size(); i2++) {
					arr[count] = ((KVPair) backarr[i].get(i2)).getV();
					count += 1;
				}
			}
		}
		return arr;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for(int i = 0; i < getCapacity(); i++) {
			if(backarr[i] != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public V put(K key, V value) {
		if (containsKey(key)) {
			int index = Math.abs(MapHelper.hashFunction(key)%getCapacity());
			//backarr[index].get(backarr[index].indexOf(key)); // WRONG
			for(int i = 0; i < backarr[index].size(); i++) {
				if(((KVPair)backarr[index].get(i)).getK().equals(key)) {
					V oldv = ((KVPair)backarr[index].get(i)).getV();
					((KVPair)backarr[index].get(i)).setV(value);
					return oldv;
				}
			}
		} else if ((double)(size()+1)/getCapacity() < LOAD_FACTOR) {
			int index = Math.abs(MapHelper.hashFunction(key)%getCapacity());
			if (backarr[index] == null) {
				backarr[index] = new LinkedList();
				backarr[index].add(new KVPair(key,value));
			} else {
				backarr[index].add(new KVPair(key,value));
			}
		} else {
			LinkedList[] oldbackarr = backarr;
			backarr = new LinkedList[getCapacity()*2];
			for(int i = 0; i < oldbackarr.length; i++) {
				if (oldbackarr[i] != null) {
					for(int i2 = 0; i2 < oldbackarr[i].size(); i2++) {
						K curk = ((KVPair)oldbackarr[i].get(i2)).getK();
						V curv = ((KVPair)oldbackarr[i].get(i2)).getV();
						put(curk, curv);
					}
				}
			}
			put(key, value);
		}
		return null;
	}

	@Override
	public V remove(Object key) {
		int index = Math.abs(MapHelper.hashFunction(key)%getCapacity());
		if(backarr[index] != null) {
			for (int i = 0; i < backarr[index].size(); i++) {
				if(((KVPair)backarr[index].get(i)).getK().equals(key)) {
					V oldv = ((KVPair)backarr[index].get(i)).getV();
					backarr[index].remove(i);
					return oldv;
				}
			}
		}
		return null;
	}

	@Override
	public int size() {
		int entries = 0;
		for(int i = 0; i < getCapacity(); i++) {
			if(backarr[i] != null) {
				entries += backarr[i].size();
			}
		}
		return entries;
	}
	
	public class KVPair {
		K kr = null;
		V vr = null;
		public KVPair(K k, V v) {
			kr = k;
			vr = v;
		}
		
		public K getK() {
			return kr;
		}
		public V getV() {
			return vr;
		}
		public void setK(K k) {
			kr = k;
		}
		public void setV(V v) {
			vr = v;
		}
		public String toString() {
			return "("+kr+", "+vr+")";
		}
	}

	@Override
	public int getArrayIndexFromKey(Object key) {
		// TODO Auto-generated method stub
		return Math.abs(MapHelper.hashFunction(key)%getCapacity());
	}
	public String toString() {
		String string = "";
		for(int i = 0; i < getCapacity(); i++) {
			if (i != getCapacity()-1) {	
				if (backarr[i] != null) {
					string = string+backarr[i].toString()+System.lineSeparator();
				} else {
					string = string+"null"+System.lineSeparator();
				}
			} else {
				if (backarr[i] != null) {
					string = string+backarr[i].toString();
				} else {
					string = string+"null";
				}
			}
		}
		return string;
	}

}
