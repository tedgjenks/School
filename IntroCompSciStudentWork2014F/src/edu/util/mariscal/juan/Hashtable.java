package edu.util.mariscal.juan;

import java.lang.reflect.Array;

import edu.jenks.dist.util.Map;
import edu.jenks.dist.util.MapHelper;
public class Hashtable<K,V> implements Map<K,V>{
	LinkedList<Pair>[] arr;
	
	public Hashtable(int initialCapacity) {
		arr=new LinkedList[initialCapacity];
		makeArr();
	}
	public void makeArr() {
		for(int i=0; i<getCapacity();i++) {
			arr[i]=new LinkedList<Pair>();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String, String> a = new Hashtable(5);
		a.put("one", "a");
		System.out.println(a);
		//System.out.println("\n"+a.put("one", "b"));
		a.put("two", "b");
		a.put("three", "c");
		System.out.println(a);
		
		System.out.println("should be true : "+a.containsValue("c"));
		System.out.println("should be false : "+a.containsValue("not here")+"\n");
		String[] karr = a.getKeys();
		System.out.println(a.arrToString(karr));
		System.out.println("should be c : "+a.get("three"));
		
		System.out.println("should be a : "+a.remove("three"));
		System.out.println(a);
		
		a.put("three", "c");
		a.put("four", "d");
		a.put("five", "e");
		a.put("six", "f");
		a.put("seven", "g");
		a.put("eight", "h");
		System.out.println(a);
		
		String[] varr = a.getValues();
		System.out.println(a.arrToString(varr));
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i=0; i<getCapacity();i++) {
			arr[i].clear();
		}
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		//boolean here=false;
		int index=getArrayIndexFromKey(key);
		LinkedList<Pair> temp = arr[index];
		for(int i=0; i<temp.size(); i++) {
			if(temp.get(i).getKey().equals(key)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		for(int j=0; j<getCapacity();j++) {
			LinkedList<Pair> temp = arr[j];
			for(int i=0; i<temp.size(); i++) {
				if(temp.get(i).getValue().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		V value=null;
		if(containsKey(key)) {
			int index=getArrayIndexFromKey(key);
			LinkedList<Pair> temp = arr[index];
			for(int i=0; i<temp.size(); i++) {
				if(temp.get(i).getKey().equals(key)) {
					value=temp.get(i).getValue();
				}
			}
		}
		return value;
	}

	@Override
	public int getArrayIndexFromKey(Object arg0) {
		// TODO Auto-generated method stub
		int a = MapHelper.hashFunction(arg0);
		return Math.abs(a%getCapacity());
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		
		return arr.length;
	}

	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
		K a = null;
		for(int i=0; i<getCapacity();i++) {
			for(int j=0; j<arr[i].size();j++) {
				Pair p = arr[i].get(j);
				a = p.getKey();
				break;
			}
		}
		Class c = a.getClass();
		K[] karr = (K[])Array.newInstance(c, size());
		int index=0;
		for(int i=0; i<getCapacity();i++) {
			LinkedList<Pair> temp = arr[i];
			for(int j=0; j<temp.size();j++) {
				K key = temp.get(j).getKey();
				karr[index]=key;
				index++;
			}
		}
		
		return karr;
	}

	@Override
	public V[] getValues() {
		// TODO Auto-generated method stub
		V a = null;
		for(int i=0; i<getCapacity();i++) {
			for(int j=0; j<arr[i].size();j++) {
				Pair p = arr[i].get(j);
				a = p.getValue();
				break;
			}
		}
		Class c = a.getClass();
		V[] varr = (V[])Array.newInstance(c, size());
		int index=0;
		for(int i=0; i<getCapacity();i++) {
			LinkedList<Pair> temp = arr[i];
			for(int j=0; j<temp.size();j++) {
				V value = temp.get(j).getValue();
				varr[index]=value;
				index++;
			}
		}
		
		return varr;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size()==0;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		V returnValue= null; 
		Pair p = new Pair(key, value);
		if(containsKey(key)) {
			int index = getArrayIndexFromKey(key);
			LinkedList<Pair> temp = arr[index];
			int tempIndex=0;
			for(int i=0; i<temp.size();i++) {
				Pair tempP=temp.get(i);
				if(tempP.getKey().equals(key)) {
					tempIndex=i;
					break;
				}	
			}
			returnValue= temp.get(tempIndex).getValue();
			temp.set(tempIndex, p);
		}else {
			checkLoadFactor();
			int index = getArrayIndexFromKey(key);
			LinkedList<Pair> temp = arr[index];
			temp.add(p);
		}
		
		return returnValue;
	}
	
	public void checkLoadFactor() {
		double loadFactor = (double)(size()+1)/getCapacity();
		System.out.println(loadFactor);
		if(loadFactor>=LOAD_FACTOR) {
			LinkedList<Pair>[] newArr = new LinkedList[getCapacity()*2];
			LinkedList<Pair>[] oldArr = arr;
			arr=newArr;
			makeArr();
			for(int i=0;i<oldArr.length;i++) {
				LinkedList<Pair> temp = oldArr[i];
				for(int j=0;j<temp.size();j++) {
					Pair p = temp.get(j);
					put(p.getKey(), p.getValue());
				}
			}
			
		}
	}
	
	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		V value=null;
		if(containsKey(key)) {
			int index=getArrayIndexFromKey(key);
			LinkedList<Pair> temp = arr[index];
			for(int i=0; i<temp.size(); i++) {
				if(temp.get(i).getKey().equals(key)) {
					value=temp.get(i).getValue();
					temp.remove(i);
					break;
				}
			}
		}
		return value;
	
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=0;i<arr.length;i++) {
			LinkedList temp = arr[i];
			int tempSize=temp.size();
			if(Integer.MAX_VALUE-tempSize>=sum) {
				sum+=temp.size();
			}
			else {
				return Integer.MAX_VALUE;
			}
			
		}
		return sum;
	}
	
	public class Pair{
		K key;
		V value;
		public Pair(K theKey, V theValue) {
			key=theKey;
			value=theValue;
		}
		
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public String toString() {
			return "("+key.toString()+" : "+value.toString()+")";
		}
	}
	public String toString() {
		String a = "";
		for(int i=0; i<getCapacity();i++) {
			a+="Index "+i+": "+arr[i].toString()+"\n";
		}
		return a;
	}
	public String arrToString(K[] karr) {
		String s = "[";
		for(int i=0; i<karr.length;i++) {
			s=s+karr[i].toString()+", ";
		}
		s+="]";
		return s;
		}
	
	
}
