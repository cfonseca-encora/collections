package com.cramirez.customcollections.set.hashset;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.list.linkedlist.LinkedList;
import com.cramirez.customcollections.set.Set;

import java.util.NoSuchElementException;

public class HashSet<E> implements Set<E> {
    protected static final int INITIAL_SIZE = 3;
    protected static final float INCREMENT_PERCENTAGE = 0.3f;
    protected static final int REARRANGE_LIMIT = 3;
    protected LinkedList<E>[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public HashSet() {
        data = (LinkedList<E>[]) new LinkedList[INITIAL_SIZE];
    }

    @Override
    public boolean add(E object) {
        return internalAdd(object);
    }

    private boolean internalAdd(E object) {
        int hash = calculateHashAndAssurePosition(object);

        if(data[hash].size() == REARRANGE_LIMIT) {
            hash = rearrangeSet(object);
        }

        Iterator<E> it = data[hash].iterator();

        while(it.hasNext()) {
            E node = it.next();
            if(node.equals(object))
                return false;
        }

        data[hash].add(object);
        size++;
        return true;
    }

    @Override
    public E remove(E object) {
        int hash = calculateHashAndAssurePosition(object);

        Iterator<E> it = data[hash].iterator();
        int i = 0;
        E dataDeleted = null;
        while(it.hasNext()) {
            E current = it.next();
            if(object.equals(current)) {
                size--;
                dataDeleted = data[hash].getAt(i);
                data[hash].remove(i);
            }
            i++;
        }
        return dataDeleted;
    }

    @Override
    public boolean contains(E object) {
        int hash = calculateHashAndAssurePosition(object);
        Iterator<E> it;

        it = data[hash].iterator();

        while(it.hasNext()) {
            E node = it.next();
            if(node.equals(object))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index;

            int elementCount;

            Iterator<E> it;

            {
                for(int i = 0; i < size; i++) {
                    if(data[i] != null) {
                        it = data[i].iterator();
                        index = i;
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return elementCount < size;
            }

            @Override
            public E next() {
                E value = null;
                try {
                    value = it.next();
                    elementCount++;
                } catch (NoSuchElementException nse) {
                    if(index + 1 == data.length)
                        return null;
                    for (int i = index + 1; i < data.length; i++) {
                        if(data[i] != null) {
                            it = data[i].iterator();
                            index = i;
                            value = (it.hasNext()) ? it.next() : null;
                            elementCount++;
                            break;
                        }
                    }
                }

                return value;
            }
        };
    }

    @Override
    public ReversedIterator<E> reversedIterator() {
        return new ReversedIterator<E>() {
            int index;

            int elementCount;

            ReversedIterator<E> it;

            {
                for(int i = data.length - 1; i >= 0; i--) {
                    if(data[i] != null) {
                        it = data[i].reversedIterator();
                        index = i;
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return elementCount < size;
            }

            @Override
            public E next() {
                E value = null;
                try {
                    value = it.next();
                    elementCount++;
                } catch (NoSuchElementException nse) {
                    if(index == 0)
                        return null;
                    for (int i = index - 1; i >= 0; i--) {
                        if(data[i] != null) {
                            it = data[i].reversedIterator();
                            index = i;
                            value = (it.hasNext()) ? it.next() : null;
                            elementCount++;
                            break;
                        }
                    }
                }

                return value;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    protected int rearrangeSet(E object) {
        int incrementIndex = data.length + Math.round((data.length * INCREMENT_PERCENTAGE));

        LinkedList<E>[] dataBackup = data;
        size = 0;
        data = (LinkedList<E>[]) new LinkedList[incrementIndex];

        for (LinkedList<E> current : dataBackup) {
            if (current == null)
                continue;

            Iterator<E> it = current.iterator();

            while (it.hasNext()) {
                this.internalAdd(it.next());
            }
        }

        return calculateHashAndAssurePosition(object);
    }

    protected int calculateHashAndAssurePosition(E object) {
        int hash = calculateHash(object);

        if (data[hash] == null) {
            data[hash] = new LinkedList<>();
        }

        return hash;
    }

    protected int calculateHash(E object) {
        int hash = object.hashCode();

        if (hash < 0)
            hash = -hash;

        return hash % data.length;
    }
}
