package com.cramirez.customcollections.set.linkedhashset;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.list.linkedlist.LinkedList;
import com.cramirez.customcollections.set.hashset.HashSet;

public class LinkedHashSet<E> extends HashSet<E> {

    private LinkedList<E> sortedData;
    public LinkedHashSet() {
        super();
        sortedData = new LinkedList<>();
    }

    @Override
    public boolean add(E object) {
        boolean result = super.add(object);

        if(result)
            sortedData.add(object);

        return result;
    }

    @Override
    public void remove(E object) {
        Iterator<E> it = sortedData.iterator();

        for (int i = 0; i < sortedData.size(); i++) {
            E current = it.next();

            if(!current.equals(object))
                continue;

            sortedData.remove(i);
            super.remove(object);
            break;
        }
    }

    @Override
    public boolean contains(E object) {
        return super.contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return sortedData.iterator();
    }

    @Override
    public ReversedIterator<E> reversedIterator() {
        return sortedData.reversedIterator();
    }

    @Override
    public int size() {
        return sortedData.size();
    }
}
