package com.cramirez.customcollections.set;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.list.linkedlist.LinkedList;

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
        int hash = calculateHash(object);

        if (data[hash] == null) {
            data[hash] = new LinkedList<>();
            data[hash].add(object);
            size++;
            return true;
        }

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
        int hash = calculateHash(object);

        data[hash] = null;

        size--;
    }

    @Override
    public boolean contains(E object) {
        int hash = calculateHash(object);
        Iterator<E> it;

        try {
            it = data[hash].iterator();
        } catch (NullPointerException np) {
            return false;
        }

        while(it.hasNext()) {
            E node = it.next();
            if(node == object)
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index;

            Iterator<E> it;

            {
                for(int i = 0; i < size; i++){
                    if(data[i] != null) {
                        it = data[i].iterator();
                        index = i;
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                if(it.hasNext())
                    return true;
                return (index + 1 < data.length) && (data[index + 1] != null);
            }

            @Override
            public E next() {
                if(it.hasNext()){
                    return it.next();
                }
                if(hasNext()){
                    it = data[++index].iterator();
                    return it.next();
                }
                return null;
            }
        };
    }

    @Override
    public ReversedIterator<E> reversedIterator() {
        return new ReversedIterator<E>() {
            int index;

            ReversedIterator<E> it;

            {
                for(int i = data.length - 1; i >= 0; i--){
                    if(data[i] != null) {
                        it = data[i].reversedIterator();
                        index = i;
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                if(it.hasNext())
                    return true;
                if(index == 0)
                    return false;
                return (index - 1 < data.length) && (data[index - 1] != null);
            }

            @Override
            public E next() {
                if(it.hasNext()){
                    return it.next();
                }
                if(hasNext()){
                    it = data[--index].reversedIterator();
                    return it.next();
                }
                return null;
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
                break;

            Iterator<E> it = current.iterator();

            while (it.hasNext()) {
                add(it.next());
            }
        }

        int hash = calculateHash(object);

        if(data[hash].size() == REARRANGE_LIMIT) {
            hash = rearrangeSet(object);
        }

        return hash;
    }

    private int calculateHash(E object) {
        int hash = object.hashCode();

        if (hash < 0)
            hash = -hash;

        return hash % data.length;
    }
}
