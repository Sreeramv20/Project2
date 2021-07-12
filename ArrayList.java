package com.company;

public class ArrayList<T> implements List<T> {

    T [] arr;
    int  size;


    @SuppressWarnings("hiding")
    private class ListIterator<T> implements Iterator<T> {

        private int nextItem;

        public ListIterator() {
            nextItem = 0;
        }

        @Override
        public boolean hasNext() {
            if (nextItem < size)
                return true;
            return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) arr[nextItem++];
        }

    }


    @SuppressWarnings("unchecked")
    public ArrayList () {
        size = 0;
        arr = (T[]) new Object[10];
    }

    public Iterator<T> iterator() {
        return new ListIterator<T>();
    }

    private void growArray () {
        @SuppressWarnings("unchecked")
        T[] newarr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++)
            newarr[i] = arr[i];
        arr = newarr;
    }

    @Override
    public void add(T item) throws Exception {  // Best: O(1); Worst: O(1) -- "amortised"?
        if (size == arr.length)  // Array is full, so double the array
            growArray();
        arr[size++] = item;
    }

    @Override
    public void add(int pos, T item) throws Exception {  // Best: O(1); Worst: O(n)
        if (pos < 0 || pos > size-1 )
            throw new Exception("List index out of bounds");
        if (size == arr.length)
            growArray();
        for (int i = size; i > pos; i--) {
            arr[i] = arr[i-1];
        }
        arr[pos] = item;
        ++size;
    }

    @Override
    public T get(int pos) throws Exception {  // // O(1)
        if (pos < 0 || pos > size-1 )
            throw new Exception("List index out of bounds");
        return arr[pos];
    }

    @Override
    public T remove(int pos) throws Exception {  // Best O(1), worst O(n)
        if (pos < 0 || pos > size-1 )
            throw new Exception("List index out of bounds");
        T item = arr[pos];
        for (int i = pos; i < size-1; i++)
            arr[i] = arr[i+1];
        --size;
        return item;
    }

    @Override
    public int size() {  // Best/worst: O(1)
        return size;
    }

}
