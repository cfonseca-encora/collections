package collections.list.arraylist;

import collections.list.Iterator;
import collections.list.List;
import collections.list.ReversedIterator;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {

    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;
    private String dataList[] = {};

    public ArrayList() {
        dataList = new String[INITIAL_CAPACITY];
    }

    public ArrayList(int capacity) {
        dataList = new String[capacity];
    }

    @Override
    public void add(String data) {
        if (size == dataList.length)
            ensureCapacity();

        dataList[size] = data;
        size++;
    }

    @Override
    public void insert(int index, String data) {
        if (size <= index || index < 0)
            throw new ArrayIndexOutOfBoundsException();

        if (size == dataList.length)
            ensureCapacity();

        dataList[index] = data;
    }

    @Override
    public String getAt(int index) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();

        return dataList[index];
    }

    @Override
    public void setAt(int index, String data) {
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

    @Override
    public void removeAll() {
        dataList = new String[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size && dataList[index] != null;
            }

            @Override
            public String next() {
                if (!hasNext())
                    throw new NoSuchElementException("There is no such element next to this position");

                return dataList[index++];
            }
        };
    }

    public ReversedIterator reverseIterator() {
        return new ReversedIterator() {
            private int index = size - 1;

            @Override
            public boolean hasPrevious() {
                return index >= 0 && dataList[index] != null;
            }

            @Override
            public String previous() {
                if (!hasPrevious())
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

    String[] getDataArray() {
        return dataList;
    }

}
