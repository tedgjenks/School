/**
 * 
 */
package edu.util.jenks.ted;

import edu.jenks.dist.util.*;
import java.lang.reflect.Array;

/**
 * Hashtable stores key-value pairs (K, V).  The backing data structure is an array of size capacity.  A hash value is determined from the key.  You must then evaluate the index of the array using hash value % array.length.  When the load factor exceeds the load factor specified in Map, the capacity should be doubled.
 * 
 * @author Jenks
 */
public class Hashtable<K, V> implements Map<K, V> {
	
	private float currentLoadFactor;
	private int size = 0;
	private AbstractLinkedList<Entry<K, V>>[] entries;
	
	/**
	 * @param initialCapacity - the initial capacity (length) of the backing array
	 */
	@SuppressWarnings("unchecked")
	public Hashtable(int initialCapacity) {
		entries = new AbstractLinkedList[initialCapacity];
		calculateLoadFactor();
	}

	@Override
	public int getCapacity() {
		return entries.length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		int length = entries.length;
		entries = new AbstractLinkedList[length]; 
		size = 0;
	}

	@Override
	public boolean containsKey(Object key) {
		AbstractLinkedList<Entry<K, V>> arrayValue = entries[getArrayIndexFromKey(key)];
		return arrayValue != null && arrayValue.contains(key);
	}

	@Override
	public boolean containsValue(Object value) {
		boolean containsValue = false;
		Entry<K, V>[] allEntries = getEntries();
		for(int index = allEntries.length - 1; index >= 0 && !containsValue; index--)
			containsValue = value == null ? allEntries[index].VALUE == null : value.equals(allEntries[index].VALUE);
		return containsValue;
	}

	@Override
	public V get(Object key) {
		V value = null;
		AbstractLinkedList<Entry<K, V>> arrayValue = entries[getArrayIndexFromKey(key)];
		if(arrayValue != null) {
			int index = arrayValue.indexOf(key);
			if(index >= 0)
				value = arrayValue.get(index).VALUE;
		}
		return value;
	}
	
	@Override
	public int getArrayIndexFromKey(Object key) {
		return Math.abs(MapHelper.hashFunction(key) % entries.length);
	}

	@SuppressWarnings("unchecked")
	@Override
	public K[] getKeys() {
		K[] keys = null;
		Entry<K, V>[] allEntries = getEntries();
		if(allEntries.length > 0) {
			keys = (K[])Array.newInstance(allEntries[0].KEY.getClass(), allEntries.length);
			for(int index = keys.length - 1; index >= 0; index--)
				keys[index] = allEntries[index].KEY;
		}
		return keys;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V[] getValues() {
		V[] values = null;
		Entry<K, V>[] allEntries = getEntries();
		if(allEntries.length > 0) {
			values = (V[])Array.newInstance(allEntries[0].VALUE.getClass(), allEntries.length);
			for(int index = values.length - 1; index >= 0; index--)
				values[index] = allEntries[index].VALUE;
		}
		return values;
	}
	
	private Entry<K, V>[] getEntries() {
		AbstractLinkedList<Entry<K, V>> entriesList = new LinkedList<Entry<K, V>>();
		for(int arrayIndex = entries.length - 1; arrayIndex >= 0; arrayIndex--) {
			AbstractLinkedList<Entry<K, V>> list = entries[arrayIndex];
			if(list != null) {
				for(int listIndex = list.size() - 1; listIndex >= 0; listIndex--)
					entriesList.add(list.get(listIndex));
			}
		}
		@SuppressWarnings("unchecked")
		Entry<K, V>[] entriesArray = (Entry<K, V>[])(new Entry[entriesList.size()]);
		for(int index = entriesArray.length - 1; index >= 0; index--)
			entriesArray[index] = entriesList.remove();
		return entriesArray;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		return put(key, value, true);
	}
	
	private V put(K key, V value, boolean checkLoadFactor) {
		V oldValue = null;
		boolean rehash = checkLoadFactor;
		int index = getArrayIndexFromKey(key);
		AbstractLinkedList<Entry<K, V>> list = entries[index];
		Entry<K, V> newEntry = new Entry<K, V>(key, value);
		if(list == null) {
			list = new LinkedList<Entry<K, V>>();
			entries[index] = list;
		} else {
			int listIndex = list.indexOf(newEntry);
			if(listIndex >= 0) {
				oldValue = list.remove(listIndex).VALUE;
				size--;
				rehash = false;
			}
		}
		list.add(newEntry);
		size++;
		if(rehash)
			rehash();
		return oldValue;
	}

	@Override
	public V remove(Object key) {
		V oldValue = null;
		int entriesIndex = getArrayIndexFromKey(key);
		AbstractLinkedList<Entry<K, V>> list = entries[entriesIndex];
		if(list != null) {
			int listIndex = list.indexOf(key);
			if(listIndex >= 0) {
				oldValue = list.remove(listIndex).VALUE;
				size--;
			}
		}
		return oldValue;
	}

	@Override
	public int size() {
		return size;
	}
	
	private static class Entry<K, V> {
		public final K KEY;
		public final V VALUE;
		public Entry(K key, V value) {
			KEY = key;
			VALUE = value;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			return obj.equals(KEY);
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(10);
			sb.append("<").append(KEY).append(", ").append(VALUE).append(">");
			return sb.toString();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void rehash() {
		calculateLoadFactor();
		if(currentLoadFactor > LOAD_FACTOR) {
			Entry<K, V>[] oldEntries = getEntries();
			entries = new AbstractLinkedList[entries.length * 2];
			size = 0;
			for(int oldEntriesIndex = oldEntries.length - 1; oldEntriesIndex >= 0; oldEntriesIndex--)
				put(oldEntries[oldEntriesIndex].KEY, oldEntries[oldEntriesIndex].VALUE, false);
			calculateLoadFactor();
		}
	}
	
	private void calculateLoadFactor() {
		currentLoadFactor = (float)size / entries.length;
	}

	/**
	 * @return Hashtable(num elements) 0: LinkedList; 1: LinkedList; ...
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(size * 10);
		sb.append("Hashtable (").append(size).append(") ");
		for(int index = 0; index < entries.length; index++)
			sb.append(index).append(": ").append(entries[index]).append("; ");
		return sb.toString();
	}
}
