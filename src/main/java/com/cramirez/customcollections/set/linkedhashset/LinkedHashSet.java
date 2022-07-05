package com.cramirez.customcollections.set.linkedhashset;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.list.linkedlist.LinkedList;
import com.cramirez.customcollections.set.hashset.HashSet;

public class LinkedHashSet<E> extends HashSet<E> {

    private LinkedList<E> sortedData;

    private boolean arranging;
    public LinkedHashSet() {
        super();
        sortedData = new LinkedList<>();
    }

    @Override
    public boolean add(E object) {
        int hash = calculateHashAndAssurePosition(object);

        if(data[hash].size() == REARRANGE_LIMIT) {
            hash = this.rearrangeSet(object);
        }

        Iterator<E> it = super.data[hash].iterator();

        while(it.hasNext()) {
            E node = it.next();
            if(node.equals(object))
                return false;
        }

        super.data[hash].add(object);
        super.incrementSize();
        if(!arranging)
            this.sortedData.add(object);
        return true;
    }

    @Override
    public void remove(E object) {
        Iterator<E> it = sortedData.iterator();
        int index = 0;

        while(it.hasNext()) {
            E current = it.next();
            index++;

            if(!current.equals(object))
                continue;

            sortedData.remove(index);
            super.remove(object);
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

    @SuppressWarnings("unchecked")
    @Override
    protected int rearrangeSet(E object) {
        int incrementIndex = data.length + Math.round((data.length * INCREMENT_PERCENTAGE));

        LinkedList<E>[] dataBackup = data;
        super.restartSize();
        data = (LinkedList<E>[]) new LinkedList[incrementIndex];

        arranging = true;
        for (LinkedList<E> current : dataBackup) {
            if (current == null)
                continue;

            Iterator<E> it = current.iterator();

            while (it.hasNext()) {
                this.add(it.next());
            }
        }
        arranging = false;

        int hash = calculateHashAndAssurePosition(object);

        if(data[hash].size() == REARRANGE_LIMIT) {
            hash = rearrangeSet(object);
        }

        return hash;
    }
}
