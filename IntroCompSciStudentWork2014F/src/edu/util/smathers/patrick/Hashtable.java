package edu.util.smathers.patrick;
import java.lang.reflect.Array;

import edu.jenks.dist.util.*;
@SuppressWarnings("unchecked")
public class Hashtable<K, V> implements Map<K, V> {
    int itemCount = 0;
    LinkedList[] table;
    public Hashtable(int initialCapacity){ 
        table = new LinkedList[initialCapacity];
        
    }

    public int getCapacity(){ 
        return table.length;
    }

    public void clear(){
        table = new LinkedList[table.length];
        itemCount = 0;
    }

    public boolean containsKey(Object key){
        int index = getArrayIndexFromKey(key);
        LinkedList<Pair> temp = table[index];
        
        if(temp == null)//index has not been initialized
            return false;
        LinkedList<Pair>.Element tempElement = temp.first;
        if(tempElement == null)
            return false;
        while(!tempElement.data.key.equals(key)){
            if(tempElement.after == null)
                return false;
            tempElement = tempElement.after;
        }
        return true;
    }

    public boolean containsValue(Object value){
        V[] values = getValues();
        for(int i = 0; i < values.length; i++){
            if(values[i] == null ? value == null : values[i].equals(value))
                return true;
        }
        return false;
    }

    public V get(Object key){
        int index = getArrayIndexFromKey(key);
        if(table[index] == null || table[index].first == null)
            return null;
        LinkedList<Pair> temp = table[index];
        LinkedList<Pair>.Element tempElement = temp.first;
        while(!tempElement.data.key.equals(key)){
            if(tempElement.after == null)
                return null;
            tempElement = tempElement.after;
        }
        return tempElement.data.value;
    }

    public int getArrayIndexFromKey(Object key){ 
        return Math.abs(MapHelper.hashFunction(key) % table.length);
    }

    public K[] getKeys(){
        if(itemCount == 0)
            return null;
        Object[] keys = new Object[itemCount];
        int index = 0;
        for(int i = 0; i < table.length; i++){
            if(table[i] == null)
                continue;
            LinkedList<Pair>.Element element = table[i].first;
            while(element != null){
                //checks to see if its there
                for(int p = 0; keys[p] != null && p < keys.length; p++){
                    if(element.data.key.equals(keys[p]))
                        continue;
                }
                keys[index] = element.data.key;
                element = element.after;
                index++;
            }
        }
        Object[] returnArr =  (K[]) Array.newInstance(keys[0].getClass(), keys.length);
        int i = 0;
        for(Object key : keys){
            returnArr[i] = key;
            i++;
        }
        return (K[]) returnArr;
        //return null;
    }

    public V[] getValues(){
        if(itemCount == 0)
            return null;
        Object[] values = new Object[itemCount];
        int valuesIndex = 0;
        for(int i = 0; i <  table.length; i++){
            LinkedList<Pair> temp = table[i];
            if(temp == null)
                continue;
            LinkedList<Pair>.Element tempElement = temp.first;
            while(tempElement != null){
                values[valuesIndex] = tempElement.data.value;
                valuesIndex++;
                tempElement = tempElement.after;
            }
        }
        Object[] returnArr =  (K[]) Array.newInstance(values[0].getClass(), values.length);
        int i = 0;
        for(Object value : values){
            returnArr[i] = value;
            i++;
        }
        return (V[]) returnArr;
    }

    public boolean isEmpty(){
        return (itemCount == 0);
    }
    
    public V put(K key, V value){
        int index = getArrayIndexFromKey(key);
        if(containsKey(key)){
            LinkedList<Pair> temp = table[index];
            LinkedList<Pair>.Element tempElement = temp.first;
            while(!tempElement.data.key.equals(key)){
                tempElement = tempElement.after;
            }
            V returnV = tempElement.data.value;
            tempElement.data.value = value;
            return returnV;
        }
        //check load factor
        // double loadFactor = itemCount+1
        if(((double)(itemCount+1) / table.length) > LOAD_FACTOR){
            doubleHashtable();
            index = getArrayIndexFromKey(key);
        }
        if(table[index] == null)
            table[index] = new LinkedList<>();
        table[index].add(new Pair(key, value));
        itemCount++;
        return null;
    }

    public void doubleHashtable() {
        LinkedList[] newTable = new LinkedList[table.length * 2];
        for(int i = 0; i < table.length; i++){
            LinkedList<Pair> temp = table[i];
            if(temp == null)
                continue;
            LinkedList<Pair>.Element tempElement = temp.first;
            while(tempElement != null){
                int newIndex = Math.abs(MapHelper.hashFunction(tempElement.data.key) % newTable.length);
                if(newTable[newIndex] == null)
                    newTable[newIndex] = new LinkedList();
                newTable[newIndex].add(tempElement.data);
                tempElement = tempElement.after;
            }
        }
        table = newTable;
    }

    public V remove(Object key) {
        if(!containsKey(key))
            return null;
        int index = getArrayIndexFromKey(key);
        
        LinkedList<Pair>.Element tempElement = table[index].first;
        int i = 0;
        while(!tempElement.data.key.equals(key)){
            i++;
            tempElement = tempElement.after;
        }
        V value = tempElement.data.value;
        table[index].remove(i);
        itemCount--;
        return value;
    }

    public int size(){
        int count = 0;
        for(int i = 0; i < table.length; i++){
            if(table[i] == null)
                continue;
            LinkedList.Element temp = table[i].first;
            
            count += table[i].size();
        }
        return count;
        
    }

    public String toString(){
        String str = "Hashtable (" + table.length + ") ";
        for(int i = 0; i < table.length; i++){
            str += i + ": " +  (table[i] == null ? null + " " : table[i].toString());
        }
        return str;
    }
    public class Pair{
        K key;
        V value;
        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }
        public K returnKey(){
            return key;
        }
        public String toString(){
            return "<" + key + ", " + value + ">";
        }
    }
}