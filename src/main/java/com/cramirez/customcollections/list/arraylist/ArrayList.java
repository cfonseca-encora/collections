package com.cramirez.customcollections.list.arraylist;

import com.cramirez.customcollections.iterator.Iterator;
import collections.list.List;
import com.cramirez.customcollections.iterator.ReversedIterator;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;
    private T[] dataList;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        dataList = (T[])(new Object[INITIAL_CAPACITY]);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        dataList = (T[]) (new Object[capacity]);
    }

    @Override
    public void add(T data) {
        if (size == dataList.length)
            ensureCapacity();

        dataList[size] = data;
        size++;
    }

    @Override
    public void insert(int index, T data) {
        if (size <= index || index < 0)
            throw new ArrayIndexOutOfBoundsException();

        if (size == dataList.length)
            ensureCapacity();

        for (int i = index; i < size; i++) {
            T saveValue = dataList[i];
            dataList[i] = data;
            dataList[i + 1] = saveValue;
        }

        size++;
    }

    @Override
    public T getAt(int index) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();

        return dataList[index];
    }

    @Override
    public void setAt(int index, T data) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();

        dataList[index] = data;
    }

    @Override
    public void remove(int index) {
        if (index > size)
            throw new ArrayIndexOutOfBoundsException(MessageFormat.format("Index: {0}, Actual Size: {1}", index, size));

        dataList[index] = null;

        for (int i = index; i < size - 1; i++)
            dataList[i] = dataList[i + 1];

        size--;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void removeAll() {
        dataList = (T[]) Array.newInstance(Object.class, INITIAL_CAPACITY);
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size && dataList[index] != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException("There is no such element next to this position");

                return dataList[index++];
            }
        };
    }

    public ReversedIterator<T> reverseIterator() {
        return new ReversedIterator<T>() {
            private int index = size - 1;

            @Override
            public boolean hasNext() {
                return index >= 0 && dataList[index] != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException("There is no such element next to this position");

                return dataList[index--];
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    void ensureCapacity() {
        int increasedCapacity = dataList.length + 10;
        dataList = Arrays.copyOf(dataList, increasedCapacity);
    }

    public int capacity() {
        return dataList.length;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "size=" + size +
                ", dataList=" + Arrays.toString(dataList) +
                '}';
    }

    T[] getDataArray() {
        return dataList;
    }

}
