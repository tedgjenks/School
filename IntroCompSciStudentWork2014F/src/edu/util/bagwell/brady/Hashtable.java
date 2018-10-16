/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.util.bagwell.brady;
import edu.jenks.dist.util.Map;
import edu.jenks.dist.util.MapHelper;
import java.lang.reflect.Array;

/**
 *
 * @author bagwbr05
 * @param <K>
 * @param <V>
 */
public class Hashtable<K,V> implements Map<K,V>{
    
    LinkedList<ValuePair>[] table;
    int capacity;
    int size = 0;
    
    public class ValuePair {
        
        K key;
        V value;
        
        public ValuePair(K k, V v){
            key = k;
            value = v;
        }
        
        public V value(){
            return value;
        }
        
        public K key(){
            return key;
        }
        
        @Override
        public String toString() {
           return "<" + key + ", " + value + ">";
        }
    }
    
    public Hashtable(int initialCapacity){
        capacity = initialCapacity;
        table = new LinkedList[initialCapacity];
    }
    
    @Override
    public int getCapacity() {
        return capacity;
    }
    
    @Override
    public void clear() {
        table = new LinkedList[capacity];
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getArrayIndexFromKey(key);
        if(table[index] != null){
            for(int i = 0; i < table[index].size(); i++){
                if(table[index].get(i).key().equals(key)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(int i = 0; i < table.length; i++){
            if(table[i] != null){
                for(int j = 0; j < table[i].size(); j++){
                    if(table[i].get(j).value().equals(value)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if(containsKey(key)){ //Checks if key exists
            int index = getArrayIndexFromKey(key); //Gets index
            for(int i = 0; i < table[index].size(); i++){ //If index conflict
                if(table[index].get(i).key().equals(key)){
                    return table[index].get(i).value();
                }
            }
        }
        return null;
    }

    @Override
    public int getArrayIndexFromKey(Object key) {
        return Math.abs(MapHelper.hashFunction(key) % capacity);
    }

    @Override
    public K[] getKeys() { //learn casting
        if(isEmpty()){
            return null;
        }
        K key = null;
        for(int i = 0; i < table.length; i++){
            if(table[i] != null){
                key = table[i].get(0).key();
            }
        }
        K[] keyList = (K[])Array.newInstance(key.getClass(), size);
        int index = 0;
        for(int i = 0; i < table.length; i++){
            if(table[i] != null){
                for(int j = 0; j < table[i].size(); j++){
                    //System.out.println(table[i].get(j).key());
                    keyList[index] = table[i].get(j).key();
                    index++;
                }
            }
        }
        return keyList;
    }
    
    @Override
    public V[] getValues() { //learn casting
        if(isEmpty()){
            return null;
        }
        V value = null;
        for(int i = 0; i < table.length; i++){
            if(table[i] != null){
                value = table[i].get(0).value();
            }
        }
        V[] valueList = (V[])Array.newInstance(value.getClass(), size);
        int index = 0;
        for(int i = 0; i < table.length; i++){
            if(table[i] != null){
                for(int j = 0; j < table[i].size(); j++){
                    //System.out.println(table[i].get(j).key());
                    valueList[index] = table[i].get(j).value();
                    index++;
                }
            }
        }
        return valueList;
    }
    
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    
    @Override
    public V put(K key, V value) {
        int index = getArrayIndexFromKey(key); //Gets the index
        //System.out.println(index);
        if(table[index] == null){ //If there is no value at the index
            table[index] = new LinkedList();
            table[index].add(new ValuePair(key, value));
        }
        else if(table[index] != null && !containsKey(key)){ //If there is already a value at the index
            table[index].add(new ValuePair(key, value));
        }
        else if(containsKey(key)){ //If the value is already in the Map
            for(int i = 0; i < table[index].size(); i++){
                if(table[index].get(i).key().equals(key)){
                    V oldValue = table[index].get(i).value();
                    table[index].set(i, new ValuePair(key, value));
                    return oldValue;
                }
            }
        }
        size++;
        //System.out.println("Load: " + (double) size / capacity);
        if((double) size / capacity >= LOAD_FACTOR){ //If the load factor is exceeded
            LinkedList<ValuePair>[] oldTable = table;
            table = new LinkedList[capacity * 2];
            capacity *= 2;
            size = 0;
            for(int i = 0; i < oldTable.length; i++){
                if(oldTable[i] != null){
                    for(int j = 0; j < oldTable[i].size(); j++){
                        put(oldTable[i].get(j).key(), oldTable[i].get(j).value());
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public V remove(Object key) {
        if(containsKey(key)){
            int index = getArrayIndexFromKey(key);
            for(int i = 0; i < table[index].size(); i++){
                if(table[index].get(i).key().equals(key)){
                    V oldValue = table[index].get(i).value();
                    table[index].remove(i);
                    if(table[index].size() == 0){
                        table[index] = null;
                    }
                    size--;
                    return oldValue;
                }
            }
        }
        return null;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public String toString() {
        String printOut = "Hashtable(" + size + ") [";
        for(int i = 0; i < table.length; i++) {
            printOut += i + ": " + table[i] + "; ";
        }
        return printOut + "]";
    }
    
    public static void main(String args[]){
        Hashtable<String,String> table = new Hashtable(10);
        table.put("E1","One");
        table.put("E2","Two");
        table.put("E3","Three");
        table.put("E4","Four");
        table.put("E5","Five");
        table.put("E6","Six");
        table.put("E7","Seven");
        //String[] array = {"E3", "E4", "E5", "E6", "E7", "E1", "E2"};
        for(int i = 0; i < table.getKeys().length; i++){
            System.out.print(table.getKeys()[i] + " ");
        }
        System.out.println();
        for(int i = 0; i < table.getValues().length; i++){
            System.out.print(table.getValues()[i] + " ");
        }
        //System.out.println(table.containsValue("One"));
        //System.out.println(table.containsValue("Eight"));
        //System.out.println(table.getKeys()[0]);
        //System.out.println(table.remove("E2"));
        //System.out.println(table.containsKey("E2"));
        //System.out.println(table);
        //System.out.println("Before: " + table.get("E5") + " + Size: " + table.size());
        //table.put("E8","Eight");
        //System.out.println(table);
        //System.out.println("After: " + table.get("E5") + " + Size: " + table.size());
        //LinkedList list = new LinkedList();
    }
}
