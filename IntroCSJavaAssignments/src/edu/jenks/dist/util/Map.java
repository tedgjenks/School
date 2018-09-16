/**
 * 
 */
package edu.jenks.dist.util;

/**
 * @author Jenks
 */
public interface Map<K, V> {
	
	public static final float LOAD_FACTOR = .75F;
	
	/**
	 * @return the current capacity of the Map
	 */
	int getCapacity();
	
	/**
	 * Removes all of the mappings from this map. The map will be empty after this call returns.
	 */
	void clear();
	
	/**
	 * Returns true if this map contains a mapping for the specified key. More formally, returns true if and only if this map contains a mapping for a key such that at least one value is mapped to the key.
	 * 
	 * @param key - key whose presence in this map is to be tested
	 * @return true if this map contains a mapping for the specified key
	 */
	boolean containsKey(Object key);
	
	/**
	 * Returns true if this map maps one or more keys to the specified value.
	 * 
	 * @param value - value whose presence in this map is to be tested; value is not null
	 * @return true if this map maps one or more keys to the specified value
	 */
	boolean containsValue(Object value);
	
	/**
	 * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
	 */
	V get(Object key);
	
	/**
	 * Get the index of the array for this key.
	 * 
	 * @param key
	 * @return the index of the backing array for this key
	 */
	int getArrayIndexFromKey(Object key);
	
	/**
	 * @return all keys that map to at least one value.
	 */
	K[] getKeys();
	
	/**
	 * @return an array of all values in the map
	 */
	V[] getValues();
	
	/**
	 * Returns true if this map contains no key-value mappings.
	 * 
	 * @return true if this map contains no key-value mappings
	 */
	boolean isEmpty();
	
	/**
	 * Associates the specified value with the specified key in this map (optional operation). If the map previously contained a mapping for the key, the old value is replaced by the specified value. (A map m is said to contain a mapping for a key k if and only if m.containsKey(k) would return true.)
	 * 
	 * @param key - key with which the specified value is to be associated
	 * @param value - value to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no mapping for key.
	 */
	V put(K key, V value);
	
	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * @param key - key whose mapping is to be removed from the map
	 * @return the previous values associated with key, or null if there was no mapping for key.
	 */
	V remove(Object key);
	
	/**
	 * Returns the number of key-value mappings in this map. If the map contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 * 
	 * @return the number of key-value mappings in this map
	 */
	int size();
}
