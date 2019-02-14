package edu.array.gabriel.mitchell;
import edu.jenks.dist.array.*;
public class MutableArray implements List
{
    private Object[] arr;
    private int elemCount = 0;
    public static void main(String[] args)
    {
    }
    public MutableArray()
    {
        this(10);
    }
    public MutableArray(int capacity)
    {
        arr = new Object[capacity];
    }
    public boolean add(Object element)
    {
        add(elemCount,element);
        return true;
    }
    public void add(int index, Object element) throws IndexOutOfBoundsException
    {
        if(index<0 || index > size()){
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }
        if (elemCount+1<=arr.length){
            for (int i = arr.length - 2; i>=index;i--){
                Object temp = arr[i];
                arr[i+1]=temp;
            }
            arr[index] = element;
        }else{
            Object[] tempArr = new Object[arr.length*2];
            for (int i = 0; i<arr.length;i++){
                tempArr[i] = arr[i];
            }
            if (tempArr[index] != null){
                for (int i = arr.length+1; i>=index;i--){
                    Object temp = tempArr[i];
                    tempArr[i+1]=temp;
                }
                tempArr[index]=element;
            } else{
                tempArr[index] = element;
            }
            arr = tempArr;
        }
        elemCount++;
    }
    public void clear()
    {
        elemCount = 0;
    }
    public Object get(int index) throws IndexOutOfBoundsException
    {
        if(index<0 || index > size()){
            throw new IndexOutOfBoundsException("Index Out of Bounds");
       }
        return arr[index];
    }
    public boolean isEmpty()
    {
        if (elemCount == 0){
            return true;
        }
        return false;
    }
    public Object remove(int index) throws IndexOutOfBoundsException
    {
        if(index<0 || index >= size()){
            throw new IndexOutOfBoundsException("Index Out of Bounds");
       }
        Object prev = arr[index];
        for(int i = index; i<arr.length-1;i++){
            arr[i] = arr[i+1];
        }
        arr[arr.length - 1] = null;
        elemCount--;
        return prev;
    }
    public Object set(int index, Object element) throws IndexOutOfBoundsException
    {
        if(index<0 || index >= size()){
            throw new IndexOutOfBoundsException("Index Out of Bounds");
       }
        Object prev = arr[index];
        arr[index] = element;
        return prev;
    }
    public int size()
    {
        return elemCount;
    }
    public Object[] toArray()
    {
        Object[] clientArr = new Object[elemCount];
        for(int i=0; i < elemCount; i++){
            clientArr[i] = arr[i];
        }
        return clientArr;
    }
    public Object[] getBackingArray()
    {
        return arr;
    }
}