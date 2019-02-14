package edu.array.burroughs.trent;
import edu.jenks.dist.array.*;
/**
 * Write a description of class MutableArray here.
 *
 * @author Trent
 * @version 1.1
 */
public class MutableArray implements List
{
    /**
     * This is for testing
     */
    private Object[] array;
    private int actSize = 0;
    private Object[] temp;
    
    public static void main(String[] args){
        MutableArray test = new MutableArray(5);
    }
    public String toString(){
        String elements = "";
        for(int i = 0; i < array.length; i++){
            elements += array[i];
        }
        return elements;
    }
    
    /**
     * This is the acutal code
     */
    
    public MutableArray(){
        this(10);
    }
    
    public MutableArray(int capacity){
        array = new Object[capacity];
    }
    
    public boolean add(Object element){
        add(actSize, element);
        return true;
    }
    
    public void add(int index,Object element){
        if(index > actSize || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if(actSize >= array.length){
            temp = new Object[array.length * 2];
            for(int i = 0; i < array.length; i ++){
                temp[i] = array[i];
            }
            array = temp;
        }
        for(int i = actSize; i > index; i --){
            array[i] = array[i - 1];
        }
        array[index] = element;
        actSize++;
    }
    
    public void clear(){
        actSize = 0;
    }
    
    public Object get(int index){
        if(index >= actSize || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Object val = 0; 
        if(index >= array.length || index < 0){
            throw new IndexOutOfBoundsException();
        }
        val = array[index];
        return val;
    }
    
    public boolean isEmpty(){
        if(actSize != 0){
            return false;
        }
        return true;
    }
    
    public Object remove(int index){
        if(index >= actSize || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Object obj = array[index];
        for(int i = index; i < array.length - 1 ; i++){
            array[i] = array[i + 1];
        }
        actSize--;
        return obj;
    }
    
    public Object set(int index, Object element){
        if(index >= actSize || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Object obj = array[index];
        array[index] = element;
        return obj;
    }
    
    public int size(){
        return actSize;
    }
    
    public Object[] toArray(){
        System.out.println("this is the act size: " + actSize);
        Object[] temp = new Object[actSize];
        for(int i = 0; i < actSize; i++){
            temp[i] = array[i];
        }
        System.out.println("this is the temp array: ");
        for(int o = 0; o < temp.length; o ++){
            System.out.println(temp[o]);
        }
        return temp;
    }
    
    public Object[] getBackingArray(){
        return array;
    }
}