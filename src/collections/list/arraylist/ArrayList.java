package collections.list.arraylist;

import collections.list.Iterator;
import collections.list.List;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {

    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;
    String dataList[] = {};

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
        return new ArrayListIterator();
    }

    @Override
    public int size() {
        return size;
    }

    void ensureCapacity() {
        int increasedCapacity = dataList.length + 10;
        dataList = Arrays.copyOf(dataList, increasedCapacity);
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "size=" + size +
                ", dataList=" + Arrays.toString(dataList) +
                '}';
    }

    class ArrayListIterator implements Iterator {
        private int index;
        private int size = dataList.length;


        @Override
        public boolean hasNext() {
            return size - index > 1;
        }
        @Override
        public String next() {
            if (!hasNext())
                throw new NoSuchElementException("There is no such element next to this position");

            return dataList[index++];
        }
    }
}
