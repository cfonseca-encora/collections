package com.cramirez.customcollections.map.hashmap;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.list.linkedlist.LinkedList;
import com.cramirez.customcollections.map.Map;

import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V> {

    LinkedList<Entry<K,V>>[] data;

    private int size;
    private static final int INITIAL_SIZE = 3;
    private static final int REARRANGE_LIMIT = 3;
    private static final float INCREMENT_PERCENTAGE = 0.3f;


    @SuppressWarnings("unchecked")
    public HashMap() {
        data = (LinkedList<Entry<K,V>>[]) new LinkedList[INITIAL_SIZE];
    }

    @Override
    public V put(K key, V value) {
        return internalPut(key, value);
    }

    private V internalPut(K key, V value) {
        Entry<K, V> object = new Entry<>(key, value);

        int hash = calculateHashAndAssurePosition(object);

        if(data[hash].size() == REARRANGE_LIMIT) {
            hash = rearrangeSet(object);
        }

        Iterator<Entry<K, V>> it = data[hash].iterator();

        while(it.hasNext()) {
            Entry<K, V> entry = it.next();
            if(entry.equals(object))
                return null;
        }

        data[hash].add(object);
        size++;
        return object.getValue();
    }

    @Override
    public V get(K key) {
        Entry<K, V> object = new Entry<>(key);
        int hash = calculateHash(object);

        Iterator<Entry<K, V>> it = data[hash].iterator();

        while(it.hasNext()) {
            Entry<K, V> entry = it.next();
            if(object.equals(entry))
                return entry.getValue();
        }
        return null;
    }

    @Override
    public V remove(K key) {
        Entry<K, V> object = new Entry<>(key);
        int hash = calculateHash(object);

        if(data[hash] == null)
            return null;

        Iterator<Entry<K, V>> it = data[hash].iterator();
        int i = 0;
        while(it.hasNext()) {
            Entry<K, V> entry = it.next();
            if(object.equals(entry)) {
                data[hash].remove(i);
                size--;
                return entry.getValue();
            }
            i++;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            int index;

            int elementCount;

            Iterator<Entry<K, V>> it;

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
            public Entry<K, V> next() {
                Entry<K, V> value = null;
                try {
                    value = it.next();
                    elementCount++;
                } catch (NullPointerException | NoSuchElementException ex) {
                    if(index == data.length - 1)
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

    public ReversedIterator<Entry<K, V>> reversedIterator() {
        return new ReversedIterator<Entry<K, V>>() {
            int index;

            int elementCount;

            ReversedIterator<Entry<K, V>> it;

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
            public Entry<K, V> next() {
                Entry<K, V> value = null;
                try {
                    value = it.next();
                    elementCount++;
                } catch (NullPointerException | NoSuchElementException ex) {
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

    @SuppressWarnings("unchecked")
    private int rearrangeSet(Entry<K,V> object) {
        int incrementIndex = data.length + Math.round((data.length * INCREMENT_PERCENTAGE));

        LinkedList<Entry<K,V>>[] dataBackup = data;
        size = 0;
        data = (LinkedList<Entry<K,V>>[]) new LinkedList[incrementIndex];

        for (LinkedList<Entry<K,V>> current : dataBackup) {
            if (current == null)
                continue;

            Iterator<Entry<K,V>> it = current.iterator();

            while (it.hasNext()) {
                Entry<K, V> entry = it.next();
                this.internalPut(entry.getKey(), entry.getValue());
            }
        }

        return calculateHashAndAssurePosition(object);
    }

    private int calculateHashAndAssurePosition(Entry<K, V> object) {
        int hash = calculateHash(object);

        if (data[hash] == null) {
            data[hash] = new LinkedList<>();
        }

        return hash;
    }

    private int calculateHash(Entry<K, V> object) {
        int hash = object.hashCode();

        if (hash < 0)
            hash = -hash;

        return hash % data.length;
    }
}
