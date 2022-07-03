package com.cramirez.customcollections.set.hashset;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.list.linkedlist.LinkedList;
import com.cramirez.customcollections.set.Set;

public class HashSet<E> implements Set<E> {
    private static final int INITIAL_SIZE = 3;
    private static final float INCREMENT_PERCENTAGE = 0.3f;
    private static final int REARRANGE_LIMIT = 3;
    private LinkedList<E>[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public HashSet() {
        data = (LinkedList<E>[]) new LinkedList[INITIAL_SIZE];
    }

    @Override
    public boolean add(E object) {
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
    public void remove(E object) {
        int hash = calculateHashAndAssurePosition(object);

        if(data[hash] == null)
            return;

        Iterator<E> it = data[hash].iterator();
        int i = 0;
        while(it.hasNext()) {
            E current = it.next();
            if(object.equals(current)) {
                size--;
                data[hash].remove(i);
            }
            i++;
        }
    }

    @Override
    public boolean contains(E object) {
        int hash = calculateHashAndAssurePosition(object);
        Iterator<E> it;

        try {
            it = data[hash].iterator();
        } catch (NullPointerException np) {
            return false;
        }

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

            E next;

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
                if (elementCount == 0 || elementCount==size)
                    return it.hasNext();

                if (next != null)
                   return true;

                if (index >= data.length) //(erased "+1" after index in this line in case somethings fails )
                    return it.hasNext();

                return false;
            }

            @Override
            public E next() {

                if(elementCount == 0 || it.hasNext()) {

                    next = it.next();
                    if(index + 1 >= data.length && !it.hasNext()) {
                        E last = next;
                        next = null;
                        elementCount++;
                        index = data.length;
                        return last;
                    }
                    elementCount++;
                    return next;
                }

                for(int i = index + 1; i < data.length; i++) {
                    if(data[i] != null) {

                        elementCount++;
                        index = i;
                        it = data[index].iterator();
                        next = it.next();
                        return next;
                    }
                }

                index = data.length;
                next = null;
                return next;
            }
        };
    }

    @Override
    public ReversedIterator<E> reversedIterator() {
        return new ReversedIterator<E>() {
            int index;

            int elementCount;

            ReversedIterator<E> it;

            E next;

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
                if (elementCount == 0 || elementCount == size)
                    return it.hasNext();

                if (next != null)
                    return true;

                if (index >= 0)
                    return it.hasNext();

                return false;
            }

            @Override
            public E next() {
                if(elementCount == 0 || it.hasNext()) {
                    next = it.next();

                    if(index - 1 <= 0 && !it.hasNext()) {
                        E last = next;
                        next = null;
                        elementCount++;
                        index = data.length;
                        return last;
                    }

                    elementCount++;
                    return next;
                }

                for(int i = index - 1; i >= 0; i--) {
                    if(data[i] != null) {
                        elementCount++;
                        index = i;
                        it = data[index].reversedIterator();
                        next = it.next();
                        return next;
                    }
                }

                index = data.length;
                next = null;
                return next;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private int rearrangeSet(E object) {
        int incrementIndex = data.length + Math.round((data.length * INCREMENT_PERCENTAGE));

        LinkedList<E>[] dataBackup = data;
        size = 0;
        data = (LinkedList<E>[]) new LinkedList[incrementIndex];

        for (LinkedList<E> current : dataBackup) {
            if (current == null)
                continue;

            Iterator<E> it = current.iterator();

            while (it.hasNext()) {
                add(it.next());
            }
        }

        int hash = calculateHashAndAssurePosition(object);

        if(data[hash].size() == REARRANGE_LIMIT) {
            hash = rearrangeSet(object);
        }

        return hash;
    }

    private int calculateHashAndAssurePosition(E object) {
        int hash = object.hashCode();

        if (hash < 0)
            hash = -hash;

        hash = hash % data.length;

        if (data[hash] == null) {
            data[hash] = new LinkedList<>();
            data[hash].add(object);
            size++;
        }

        return hash;
    }
}
